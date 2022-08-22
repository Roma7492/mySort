package recursrion;

import trees.binary.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Recursion {
  public static void main(String[] args) {
    System.out.println(reverseString("Roma"));
    System.out.println("Is Kayak palindrome?  " + isPalindrome("kayak"));
    System.out.println("Is Roma palindrome?  " + isPalindrome("roma"));
    System.out.println("Binary of decimal 233  " + decimalToBinary(233, ""));
    System.out.println("Sum of naturals " + sumOfNaturalNumbers(10));

    Integer[] A = {3, 6, 7 , 9, 10};
    System.out.println("Index of 6 is: " + binarySearch(A, 0, A.length - 1, 6));
    System.out.println("Fib of 8 : " + naiveFib(8));

    int[] B = {30, 6, -1 , 9, 10, 5, 77, -6};

    mergeSort(B, 0 , B.length - 1);
    System.out.println("Sorted array: {" + Arrays.stream(B)
        .boxed()
        .map(i -> Integer.toString(i))
        .collect(Collectors.joining(", "))
        + "}");

    ListNode one = new ListNode(1);
    ListNode two = new ListNode(3);
    ListNode three = new ListNode(5);
    ListNode four = new ListNode(7);
    one.next = two;
    two.next = three;
    three.next = four;
  //  ListNode head = reverseList(one);
//    System.out.println("New Head : " + head.val);


    ListNode five = new ListNode(2);
    ListNode six = new ListNode(4);
    ListNode seven = new ListNode(6);
    ListNode eight = new ListNode(8);
    five.next = six;
    six.next = seven;
    seven.next = eight;
    ListNode head = mergeSortedLists(one, five);
    System.out.println("New Head : " + head.val);


    TreeNode<Integer> one_t = new TreeNode<>(10);
    TreeNode<Integer> two_t = new TreeNode<>(5);
    TreeNode<Integer> three_t = new TreeNode<>(12);
    TreeNode<Integer> four_t = new TreeNode<>(4);
    TreeNode<Integer> five_t = new TreeNode<>(6);
    TreeNode<Integer> six_t = new TreeNode<>(15);
    one_t.setLeft(two_t);
    one_t.setRight(three_t);
    two_t.setLeft(four_t);
    two_t.setRight(five_t);
    three_t.setRight(six_t);
   // insertNode(one_t, 11);

    printLeafNodes(one_t);

    System.out.println("Fib of 8 : " + fib(8));
  }

  public static String reverseString(String input) {
    if (input.equals("")) {
      return "";
    }
    return reverseString(input.substring(1)) + input.charAt(0);
  }

  public static boolean isPalindrome(String test) {
    if (test.length() == 0 || test.length() == 1) {
      return true;
    }
    if (test.charAt(0) == test.charAt(test.length() - 1)) {
      return isPalindrome(test.substring(1, test.length() - 1));
    }
    return false;
  }

  public static Integer sumOfNaturalNumbers(Integer lim) {
    if (lim == 1) {
      return lim;
    }
    return lim + sumOfNaturalNumbers(lim - 1);
  }

  public static String decimalToBinary(Integer decimal, String res) {
    if (decimal == 0) {
      return res;
    }
    res = decimal % 2 + res;
    return decimalToBinary(decimal / 2, res);
  }

  public static int binarySearch(Integer[] A, int left, int right, int target) {
    if (left > right) {
      return -1;
    }

    int mid = (right + left) / 2;

    if (A[mid] == target) {
      return mid;
    }

    if (target > A[mid]) {
      return binarySearch(A, mid + 1, right, target);
    } else {
      return binarySearch(A, left, mid - 1, target);
    }
  }

  public static int naiveFib(int target) {
    if (target == 0 || target == 1) {
      return target;
    }
    return naiveFib(target - 1) + naiveFib(target - 2);
  }

  private static HashMap<Integer, Integer> cache = new HashMap<>();
  static {
    cache.put(0, 0);
    cache.put(1, 1);
  }

  public static int fib(int target) {
    if (cache.containsKey(target)) {
      return cache.get(target);
    }
    int result = fib(target - 1) + fib(target - 2);
    cache.put(target, result);
    return result;
  }

  public static void mergeSort(int[] data, int start, int end) {
    if (start < end) {
      int mid = (start + end) / 2;
      mergeSort(data, start, mid);
      mergeSort(data, mid + 1, end);
      merge(data, start, end, mid);
    }
  }

  private static void merge(int[] data, int start, int end, int mid) {
    int[] temp = new int[end - start + 1];

    int i = start, j = mid + 1, k = 0;

    while (i <= mid && j <= end) {
      if (data[i] <= data[j]) {
        temp[k++] = data[i++];
      } else {
        temp[k++] = data[j++];
      }
    }

    while (i <= mid) {
      temp[k++] = data[i++];
    }

    while (j <= end) {
      temp[k++] = data[j++];
    }

    for (i = start; i <= end; i++) {
      data[i] = temp[i - start];
    }
  }

  public static ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode p = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return p;
  }

  static class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
      this.val = val;
    }
  }

  public static ListNode mergeSortedLists(ListNode A, ListNode B) {
    if (A == null) return B;
    if (B == null) return A;

    if (A.val < B.val) {
      A.next = mergeSortedLists(A.next, B);
      return A;
    } else {
      B.next = mergeSortedLists(A, B.next);
      return B;
    }
  }

  public static TreeNode<Integer> insertNode(TreeNode<Integer> root, int val) {
    if (root == null) {
      return new TreeNode<>(val);
    }
    if (val < root.getVal()) {
      root.setLeft(insertNode(root.getLeft(), val));
    } else {
      root.setRight(insertNode(root.getRight(), val));
    }
    return root;
  }

  public static void printLeafNodes(TreeNode<Integer> root) {
    if (root == null) return;

    if (root.getLeft() == null && root.getRight() == null) {
      System.out.println("Leaf value: " + root.getVal());
      return;
    }

    printLeafNodes(root.getLeft());
    printLeafNodes(root.getRight());

  }

  public static boolean graphSearch(GraphNode<Integer> root, int target, Set<GraphNode<Integer>> visited) {
    if (root == null) {
      return false;
    }

    if (root.val == target) {
      return true;
    }

    for (GraphNode<Integer> neighbour : root.neighbours) {
      if (visited.contains(neighbour)) continue;
      visited.add(neighbour);
      boolean isFound = graphSearch(neighbour, target, visited);
      if (isFound) return true;
    }
    return false;
  }
}
