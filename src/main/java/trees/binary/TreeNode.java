package trees.binary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class TreeNode <T> {
  private final T val;
  private TreeNode<T> left;
  private TreeNode<T> right;

  public TreeNode(T val) {
    this.val = val;
  }

  public T getVal() {
    return val;
  }

  public void setLeft(TreeNode<T> left) {
    this.left = left;
  }

  public void setRight(TreeNode<T> right) {
    this.right = right;
  }

  public TreeNode<T> getLeft() {
    return left;
  }
  public TreeNode<T> getRight() {
    return right;
  }

  public static int findMaxDistance(int[] blocks) {
    Integer maxSum = null;
    int start;
    int maxDistance = 0;
    int i;
    i = 0;
    while (i < blocks.length - 1) {
      while ((i - 1 >= 0) && (i < blocks.length) && (blocks[i - 1] == blocks[i]))
        i--;
      start = i;
      int downSum = blocks[i];
      while ((i + 1 < blocks.length) && (blocks[i + 1] <= blocks[i])) {
        downSum += blocks[++i];
      }
      int upSum = 0;
      while ((i + 1 < blocks.length) && (blocks[i + 1] >= blocks[i])) {
        upSum += blocks[++i];
      }
      int sum = upSum + downSum;
      if ((maxSum == null) || (sum > maxSum)) {
        maxSum = sum;
        maxDistance = i - start + 1;
      }
    }
    return maxDistance;
  }

  public static void main(String[] args) {
    int[] t = {2, 6, 8, 5};
    System.out.println(findMaxDistance(t));

    Queue<Character> ch = new PriorityQueue<>(Comparator.reverseOrder());
    ch.add('M');
    ch.add('L');
    ch.add('S');
    ch.add('L');

    System.out.println(ch.poll());
    System.out.println(ch.poll());
    System.out.println(ch.poll());
    System.out.println(ch.poll());

    Set<Character> chars = new TreeSet<>();

    chars.add('M');
    chars.add('L');
    chars.add('S');
    chars.add('L');


//    TreeNode<String> a = new TreeNode<>("a");
//    TreeNode<String> b = new TreeNode<>("b");
//    TreeNode<String> c = new TreeNode<>("c");
//    TreeNode<String> d = new TreeNode<>("d");
//    TreeNode<String> e = new TreeNode<>("e");
//    TreeNode<String> f = new TreeNode<>("f");
//
//    a.left = b;
//    a.right = c;
//    b.left = d;
//    b.right = e;
//    c.left = f;


//    depthFirstTraversalIterative(a);
//    List<trees.binary.TreeNode> res = depthFirstTraversalRecursive(a);
//    res.forEach(n -> System.out.println(n.val + ", "));
//    breadthFirstTraversalIterative(a);
//
//    System.out.println(depthFirstSearchRecursive(null , "t"));
//    System.out.println(breadthFirstTraversalSearch(a , "c"));
//
//    TreeNode<Integer> one = new TreeNode<>(5);
//    TreeNode<Integer> two = new TreeNode<>(11);
//    TreeNode<Integer> three = new TreeNode<>(3);
//    TreeNode<Integer> four = new TreeNode<>(4);
//    TreeNode<Integer> five = new TreeNode<>(2);
//    TreeNode<Integer> six = new TreeNode<>(1);
//    one.left = two;
//    one.right = three;
//    two.left = four;
//    two.right = five;
//    three.left = six;
//
//    System.out.println("Sum = " +   depthFirstSumRecursive(one));
//    System.out.println("Minimum = " +   findMinRecursive(one));
//    System.out.println("Max sum root ot leaf path = " +   findMaxRootToLeafSum(one));
  }

  public static void depthFirstTraversalIterative(TreeNode root) {
    if (root == null) {
      return;
    }
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      TreeNode curr = stack.pop();
      System.out.println(curr.val + ", ");
      if (curr.left != null)
        stack.push(curr.left);
      if (curr.right != null)
        stack.push(curr.right);
    }
  }

  public static List<TreeNode> depthFirstTraversalRecursive(TreeNode root) {
    if (root == null) {
      return Collections.emptyList();
    }
    List<TreeNode> lefts = depthFirstTraversalRecursive(root.left);
    List<TreeNode> rights = depthFirstTraversalRecursive(root.right);
    List<TreeNode> res = new ArrayList<>();
    res.add(root);
    res.addAll(lefts);
    res.addAll(rights);
    return res;
  }

  public static void breadthFirstTraversalIterative(TreeNode root) {
    if(root == null) {
      return;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      TreeNode curr = queue.poll();
      System.out.println(curr.val + " ");
      Optional.ofNullable(curr.left).ifPresent(queue::add);
      Optional.ofNullable(curr.right).ifPresent(queue::add);
    }
  }

  public static boolean breadthFirstTraversalSearch(TreeNode<String> root, String target) {
    if(root == null) {
      return false;
    }
    Queue<TreeNode<String>> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      TreeNode<String> curr = queue.poll();
      if (curr.val.equals(target)) {
        return true;
      }
      Optional.ofNullable(curr.left).ifPresent(queue::add);
      Optional.ofNullable(curr.right).ifPresent(queue::add);
    }
    return false;
  }

  public static boolean depthFirstSearchRecursive(TreeNode<String> root, String target) {
    if (root == null) {
      return false;
    }
    if (root.val.equals(target)) {
      return true;
    }
    return depthFirstSearchRecursive(root.left, target) || depthFirstSearchRecursive(root.right, target);
  }

  public static int depthFirstSumRecursive(TreeNode<Integer> root) {
    if (root == null) {
      return 0;
    }
    return root.val + depthFirstSumRecursive(root.left) + depthFirstSumRecursive(root.right);
  }

  public static int findMinRecursive(TreeNode<Integer> root) {
    if (root == null) {
      return Integer.MAX_VALUE;
    }
    return Math.min(root.val, Math.min(findMinRecursive(root.right), findMinRecursive(root.left)));
  }

  public static int findMaxRootToLeafSum(TreeNode<Integer> root) {
    if (root == null) {
      return Integer.MIN_VALUE;
    }
    if (root.left == null && root.right == null) {
      return root.val;
    }
    return root.val + Math.max(findMaxRootToLeafSum(root.right), findMaxRootToLeafSum(root.left));
  }
}
