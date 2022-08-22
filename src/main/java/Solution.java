import java.beans.IntrospectionException;
import java.io.*;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class MyList {
  Node head;
}

class Node {
  int value;
  Node next;
}

class Result {

  /*
   * Complete the 'introTutorial' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   *  1. INTEGER V
   *  2. INTEGER_ARRAY arr
   */
  public List<Integer> findAnagrams(String s, String p) {
    Set<Character> characters = p.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());
    return List.of();
  }

  public static void main(String[] args) throws IOException {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    MyList list = new MyList();
    int n = Integer.parseInt(bufferedReader.readLine());
    for (int i = 0; i < n; i++) {
      int val = Integer.parseInt(bufferedReader.readLine());
      if (list.head == null) {
        Node node = new Node();
        node.value = val;
        list.head = node;

      } else {
        Node curr = list.head;
        while (curr.next != null) {
          curr = curr.next;
        }
        Node node = new Node();
        node.value = val;
        curr.next = node;
      }
      System.out.println(val);
    }
  }

  public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
    // Write your code here
    List<List<Integer>> arr = new ArrayList<>(n);
    List<Integer> res = new ArrayList<>();
    IntStream.range(0, n).forEach(i -> arr.add(new ArrayList<Integer>()));

    int lastAnswer = 0;

    for (List<Integer> q : queries) {

        if (q.get(0) == 1) {
          int idx = (q.get(1) ^ lastAnswer) % n;
          arr.get(idx).add(q.get(2));
        } else {
          int idx = (q.get(1) ^ lastAnswer) % n;
          List<Integer> curr = arr.get(idx);
          lastAnswer = curr.get(q.get(2) % curr.size());
          res.add(lastAnswer);
        }
    }

    return res;

  }

  public static List<Integer> matchingStrings(List<String> strings, List<String> queries) {
    // Write your code here
    Map<String, Long> stringCount = strings.stream()
        .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
    return queries.stream()
        .map(q ->  Optional.ofNullable(stringCount.get(q)).orElse(0L).intValue())
        .collect(Collectors.toList());
  }


  public static List<Integer> rotateLeft(int d, List<Integer> arr) {
    // Write your code here
    List<Integer> buf = arr.stream().limit(d).collect(Collectors.toList());
    arr = arr.stream().skip(d).collect(Collectors.toList());
    arr.addAll(buf);
    return arr;
  }

  public static String superReducedString(String s) {
    for (int i = 1; i < s.length(); i++) {
      if (s.charAt(i) == s.charAt(i - 1)) {
        s = s.substring(0, i - 1) + s.substring(i + 1);
        i = 0;
      }
    }

    for (Character c : s.toCharArray()) {
      if (c >= 'A' && c <= 'Z') {

      }
    }
    if (s.length() == 0) {
      return "Empty String";
    }
    return s;
  }


  public static String timeConversion(String s) {
    StringBuilder sb = new StringBuilder();
    if (s.endsWith("AM")) {
      if (s.startsWith("12")) {
        String[] parts = s.split(":");


        return sb.append("00")
            .append(":")
            .append(parts[1])
            .append(":")
            .append(parts[2].substring(0, parts[2].length() - 2))
            .toString();
      } else {
        return s.substring(0, s.length() - 2);
      }
    } else {
      String[] parts = s.split(":");
      int hours = Integer.parseInt(parts[0]);
      hours += 12;
      return sb.append(hours <= 23 ? hours : "12")
          .append(":")
          .append(parts[1])
          .append(":")
          .append(parts[2].substring(0, parts[2].length() - 2))
          .toString();
    }
  }

  public static int introTutorial(int V, List<Integer> arr) {
    return arr.indexOf(V);
  }

  public static void insertionSort1(int n, List<Integer> arr) {
    Object [] intArr = arr.toArray();
    boolean needStop = false;
    int right = arr.get(n - 1);
    for (int i = n - 2; i >= 0; i--) {
      int curr = arr.get(i);
      if (right < curr) {
        intArr[i + 1] = curr;
      } else {
        intArr[i + 1] = right;
        needStop = true;
      }
      Arrays.stream(intArr).forEach(o -> System.out.print(o + " "));
      System.out.println("");
      if (needStop) {
        break;
      }
    }
    if (!needStop) {
      intArr[0] = right;
      Arrays.stream(intArr).forEach(o -> System.out.print(o + " "));
      System.out.println("");
    }
  }

  public static void insertionSort2(int n, List<Integer> arr) {
    //entire array
    List<Integer> mutableArray = new ArrayList<>(arr);
    //iterate over arr from left to right
    for (int i = 1; i < n; i++) {
      int k = i;
      int curr = mutableArray.get(i);
      boolean wasIncorrect = false;
      // check if part of arr is sorted, otherwise swap unsorted element
      while (k > 0) {
        if (curr > mutableArray.get(k - 1)) {
          if (wasIncorrect) {
            //move all elements to the right
            for (int j = i; j > k; j--) {
              int toInsert = mutableArray.get(j - 1);
              mutableArray.set(j, toInsert);
            }
            //insert current element
            mutableArray.set(k, curr);
            wasIncorrect = false;
          }
        }

        if (curr < mutableArray.get(k - 1)) {
          // this is incorrect, continue search for place and mar as incorrect
          wasIncorrect = true;
        }
        k--;
      }
      // corner case when it sorted backwards
      if (wasIncorrect) {
        for (int j = i; j > 0; j--) {
          int toInsert = mutableArray.get(j - 1);
          mutableArray.set(j, toInsert);
        }
        mutableArray.set(0, curr);
      }
      mutableArray.forEach(o -> System.out.print(o + " "));
      System.out.println("");
    }
  }


  /**
   *
   * 1 x  -Push the element x into the stack.
   * 2    -Delete the element present at the top of the stack.
   * 3    -Print the maximum element in the stack.
   */
  public static List<Integer> getMax(List<String> operations) {
    Stack<StackNode> stack = new Stack<>();
    List<Integer> res = new ArrayList<>();
    int max = 0;
    for (String s : operations) {
      String [] parts = s.split(" ");
      if (Integer.parseInt(parts[0]) == 1) {
        int val = Integer.parseInt(parts[1]);
        max = Math.max(max, val);
        stack.push(new StackNode(val, max));
      } else if (Integer.parseInt(parts[0]) == 2) {
        StackNode sn = stack.pop();
        if (sn == null || stack.isEmpty()) {
          max = 0;
        } else {
          max = stack.peek().currMax;
        }
      } else if (Integer.parseInt(parts[0]) == 3) {
        res.add(max);
      }
    }
    return res;
  }

  public static List<Integer> countingSort(List<Integer> arr) {
    // Write your code here
    int[] res = new int[100];

    for (Integer i : arr) {
      int c = res[i];
      res[i] = ++c;
    }

    return Arrays.stream(res).boxed().collect(Collectors.toList());

  }

}

class StackNode {
  int value;
  int currMax;

  public StackNode(int value, int currMax) {
    this.value = value;
    this.currMax = currMax;
  }

}

public class Solution {

  public static void main(String[] args) throws IOException {

    List<String> strings = List.of("aba", "baba", "aba", "xzxb");
    List<String> queries = List.of("aba", "xzxb", "ey");
    Result.matchingStrings(strings, queries).forEach(System.out::println);

//    List<List<Integer>> queries = new ArrayList<>();
//    queries.add(List.of(1, 0, 5));
//    queries.add(List.of(1, 1, 7));
//    queries.add(List.of(1, 0, 3));
//    queries.add(List.of(2, 1, 0));
//    queries.add(List.of(2, 1, 1));
//    List<Integer> res =  Result.dynamicArray(2, queries);
//    res.forEach(System.out::println);
 //   BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

  //  int n = Integer.parseInt(bufferedReader.readLine().trim());

//    List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
//        .map(Integer::parseInt)
//        .collect(toList());

 //   Result.insertionSort1(n, arr);

//    List<Integer> arr1 = List.of(256741038, 623958417, 467905213, 714532089, 938071625);
//    List<Integer> candles = List.of(4, 4, 3, 1, 2);
//
//    System.out.println(Result.rotateLeft(2, candles));
//    System.out.println(candles);
//    long max1 = arr1.stream().sorted().collect(Collectors.toList()).stream().skip(1).map(i -> (long) i).reduce(
//        Long::sum).get();
//    long min = arr1.stream().sorted().collect(Collectors.toList()).stream().limit(4).reduce(Integer::sum).get();
//    System.out.println(max1 + " " + min);
//    int max = candles.stream().max(
//      Integer::compare).get();
//    int cntMax = 0;
//    for (Integer i : candles) {
//      if (i.equals(max)) {
//        cntMax++;
//      }
//    }
//    System.out.println(max);
//    System.out.println(cntMax);
//    System.out.println(Result.timeConversion("12:05:45PM"));
//    System.out.println(Result.timeConversion("12:05:45AM"));
//    System.out.println(Result.timeConversion("12:45:54PM"));


  }
}

