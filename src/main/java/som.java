import trees.binary.TreeNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class som {
  public static void main(String[] args) {
    int a = 13;
    int b  = 16;

    System.out.printf("GCD of %d and %d is %d%n", a, b, gcd(a, b));

    System.out.println("Pairs count = " + findDivisiblePairs(List.of(1, 3 ,2, 6, 1, 2), 3));

    System.out.println("MAX of Birds: " + birds(List.of(1, 1, 2, 3, 2)));

    System.out.println(dayOfProgrammer(1984));

    //electronicShop();
   // catsAndMouse();
    System.out.println(pickingNumbers(List.of(98, 3, 99, 1, 97, 2)));

    System.out.println(hurdleRace(7, List.of(2, 5, 4, 5, 2)));

    System.out.println(designerPdfViewer(List.of(1, 3, 1, 3, 1, 4, 1, 3, 2, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5), "abc"));
    System.out.println(angryProfessor(3, List.of(-1, -3, 4, 2)));
    System.out.println(angryProfessor(2, List.of(0, -1, 2, 1)));

    System.out.println(beautifulDays(20, 23, 6));
    System.out.println(viralAdvertising(5));
    System.out.println(saveThePrisoner(4, 4, 2));
    System.out.println(saveThePrisoner(7, 19, 2));
    System.out.println(saveThePrisoner(3, 7, 3));
    System.out.println(saveThePrisoner(352926151, 380324688, 94730870));

    List<Integer> arr = new ArrayList<>(List.of(3, 4, 5));
    List<Integer> res = circularArrayRotation(arr, 2, List.of(0, 1, 2));
    System.out.println(res);

    System.out.println(diagonalDifference(List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(9, 8, 9))));
    System.out.println(permutationEquation(List.of(4, 3, 5, 1, 2)));

   // jumpingCloudsRev2();
    System.out.println("found digits: " + findDigits(124));
    System.out.println("found digits: " + findDigits(10));

    System.out.println(squares(3, 9));
//    System.out.println(squares(17, 24));
//    System.out.println(squares(24, 49));
    System.out.println(squares(488907300, 943628573));
    System.out.println(libraryFine(9, 7, 2015, 6, 6, 2015));

    System.out.println(cutTheSticks(List.of(5, 4, 4, 2, 2, 8)));
    System.out.println(cutTheSticks(List.of(1, 2, 3, 4, 3, 3, 2, 1)));
    System.out.println(equalizeArray(List.of(3, 3, 2, 1, 3)));
    System.out.println(equalizeArray(List.of(1, 2, 3, 1, 2, 3, 3, 3)));
  }



  public static int gcd(int a, int b) {

    while (b > 0) {
      int temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }

  public static int findDivisiblePairs(List<Integer> arr, int k) {
    int[] buckets = new int[k];
    int complement;
    int count = 0;
    for (int i = 0; i < arr.size(); i++) {
      int curr = arr.get(i);
      curr = curr % k;
      if (curr == 0) {
        complement = 0;
      } else {
        complement = k - curr;
      }
      count += buckets[complement];
      buckets[curr]++;
    }
    return count;
  }




  public static int gcd1(int a, int b) {
    while (b > 0) {
      int temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }

  public static int birds(List<Integer> arr) {
    Map<Integer, Integer> map = new TreeMap<>();
    int max = Integer.MIN_VALUE;
    int res = 0;

    arr.forEach(a -> {
      if (map.containsKey(a)) {
        map.put(a, map.get(a) + 1);
      } else {
        map.put(a, 1);
      }
    });
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      if (entry.getValue() > max) {
        max = entry.getValue();
        res = entry.getKey();
      }
    }
    return res;
  }

  public static String dayOfProgrammer(int year) {
    int fixedSum = 215;
    int february;

    String calType = "";
    int progDay = 256;
    if (year < 1918) {
      calType = "Jul";
    } else if (year > 1918) {
      calType = "Gr";
    }

    if (calType.equals("Jul")) {
      february = year % 4 == 0 ? 29 : 28;
    } else if (calType.equals("Gr")) {
      february = (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) ? 29 : 28;
    } else {
      february = 15;
    }

    progDay = progDay - fixedSum - february;
    return progDay + ".09." + year;
  }

  public static void electronicShop() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    try {
      String[] first = reader.readLine().split(" ");
      int budget = Integer.parseInt(first[0]);
      int n = Integer.parseInt(first[1]);
      int m = Integer.parseInt(first[2]);
      int max = -1;
      List<Integer> keyboards = Arrays.stream(reader.readLine().split(" "))
          .map(Integer::parseInt)
          .sorted(Comparator.reverseOrder())
          .collect(Collectors.toList());
      List<Integer> usbDrives = Arrays.stream(reader.readLine().split(" "))
          .map(Integer::parseInt)
          .sorted()
          .collect(Collectors.toList());
      int i = 0;
      int j = 0;
      while (i < n) {
        while (j < m) {
          int currSum = keyboards.get(i) + usbDrives.get(j);
          if (currSum > budget) {
            //System.out.println(currSum);
            break;
          }
          if (currSum > max) {
            max = currSum;
          }
          j++;
        }
        i++;
      }
      System.out.println(max);

    } catch (IOException e) {
      System.out.println("IO exception caught");
    }
  }

  public static void catsAndMouse() {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    try {
      int q = Integer.parseInt(reader.readLine());
      for (int i = 0; i < q; i++) {
        String[] query = reader.readLine().split(" ");
        int catA = Integer.parseInt(query[0]);
        int catB = Integer.parseInt(query[1]);
        int mouseC = Integer.parseInt(query[2]);
        if (Math.abs(catA - mouseC) == Math.abs(catB - mouseC)) {
          System.out.println("Mouse C");
        } else if (Math.abs(catA - mouseC) > Math.abs(catB - mouseC)) {
          System.out.println("Cat B");
        } else {
          System.out.println("Cat A");
        }
      }
    } catch (IOException e) {
      System.out.println("IO exception caught");
    }
  }

  public static int pickingNumbers(List<Integer> a) {
    int maxSize = Integer.MIN_VALUE;
    int [] countingSort = new int[101];
    for (Integer i : a) {
        countingSort[i]++;
    }
    for (int i = 0; i < 100; i++) {
      if (countingSort[i] == 0) continue;
      if (countingSort[i] + countingSort[i + 1] > maxSize) {
        maxSize = countingSort[i] + countingSort[i + 1];
      }
    }
    return maxSize;
  }

  public static int hurdleRace(int k, List<Integer> height) {
    // Write your code here
    List<Integer> sorted = height.stream()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());
    return Math.max(sorted.get(0) - k, 0);
  }

  public static int designerPdfViewer(List<Integer> h, String word) {
    // Write your code here
    int maxHeight = Integer.MIN_VALUE;
    for (int i = 0; i < word.length(); i++) {
      System.out.println(Character.getNumericValue(word.charAt(i)));
      if (maxHeight < h.get(Character.getNumericValue(word.charAt(i)) - 10)) {
        maxHeight = h.get(Character.getNumericValue(word.charAt(i)) - 10);
      }
    }
    return maxHeight * word.length();

  }


  public static String angryProfessor(int k, List<Integer> a) {
    // Write your code here
    long onTimeCnt = a.stream().filter(e -> e <= 0).count();
    return onTimeCnt < k ? "YES" : "NO";
  }

  public static int beautifulDays(int i, int j, int k) {

    int counter = 0;
    for (int r = i; r <= j; r++) {
      String rString = Integer.toString(r);
      StringBuilder sb = new StringBuilder();

      for(int l = rString.length() - 1 ; l >= 0; l--) {
        sb.append(rString.charAt(l));
      }
      int rReversed = Integer.parseInt(sb.toString());

      if (Math.abs(r - rReversed) % k == 0) {
        counter++;
      }
    }

    return counter;
  }

  public static int viralAdvertising(int n) {
    // Write your code here
    int people = 0;
    int shared = 5;
    for (int i = 1; i <= n; i++) {
      people += shared / 2;
      shared = (shared / 2) * 3;
    }
    return people;
  }

  public static int saveThePrisoner(int n, int m, int s) {
    // n - total number of prisoner
    // m - number of treats
    // s - start prisoner number
//    if (n == m) {
//      return s - 1;
//    } else if (m > n) {
//      int leftover = m - (m / n) * n;
//      return s + leftover - 1;
//    } else {
//      return s + m - 1;
//    }
    int curr = (m + s) % n;
    int curr1 = ((m - 1) + (s - 1)) % n;

    return ((m - 1) + (s - 1)) % n + 1;

  }

  public static int gcdRec(int a, int b) {
    if (b == 0) {
      return a;
    }
    return gcdRec(b, a % b);

  }

  public static List<Integer> circularArrayRotation(List<Integer> a, int k, List<Integer> queries) {

    int[] res = new int[a.size()];

    for(int i=0; i < a.size(); i++) {
      res[(i + k) % a.size()] = a.get(i);
    }
    return queries.stream().map(q -> res[q]).collect(Collectors.toList());

  }

  public static int diagonalDifference(List<List<Integer>> arr) {
    int sumLeftDiag = 0;
    int sumRightDiag = 0;

    for (int i = 0; i < arr.size(); i++) {
      sumLeftDiag += arr.get(i).get(i);
      sumRightDiag += arr.get(i).get((arr.size() - 1) - i);
    }

    return Math.abs(sumLeftDiag - sumRightDiag);

  }

  /**
   * Explanation 0
   * Given the values of , , and , we calculate and print the following values for each  from  to :
   * , so we print the value of  on a new line.
   * , so we print the value of  on a new line.
   * , so we print the value of  on a new line.
   */
  public static List<Integer> permutationEquation(List<Integer> p) {
    // Write your code here
    // [2, 3, 1]
    // x= 1 ~ p(3) (-- index of 1 is 3) = p(p(2))
    // x = 2 ~ p(1) = p(p(3))
    // x = 3 ~ p(2) = p(p(1))
    // thus result arr is y = [2, 3 , 1]

    //4 3 5 1 2
    // x = 1 ~ p(4) = p(p(1))
    // x = 2 ~ p(5) = p(p(3))
    // x = 3 ~ p(2) = p(p(5))
    // x = 4 ~ p(1) = p(p(4))
    // x = 5 ~ p(3) = p(p(2))
    //result y = [1, 3, 5, 4, 2]

    int[] res = new int[p.size() + 1];

    for (int i = 0; i < p.size(); i++) {
      res[p.get(i)] = i + 1;
    }

    return Arrays.stream(res)
        .skip(1)
        .map(i -> res[i])
        .boxed()
        .collect(Collectors.toList());

  }

  public static void jumpingCloudsRev2() {
    int power = 100;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    try {
      String[] first = reader.readLine().split(" ");
      int n = Integer.parseInt(first[0]);
      int k = Integer.parseInt(first[1]);
      int[] clouds = new int[n];
      String[] second = reader.readLine().split(" ");

      for (int i = 0; i < n; i++) {
        clouds[i] = Integer.parseInt(second[i]);
      }
      int j = 0;
      int pointer = -1;//(j + k) % n;
      while (pointer != 0) {
        pointer = (j + k) % n;
        j = pointer;
        power -= clouds[j] * 2 + 1;
      }
      System.out.println(power);
    }catch (IOException e) {
      System.out.println("Error");
    }
  }

  public static int findDigits(int n) {
    return (int) Integer.toString(n).chars()
        .map(c -> Character.digit(c, 10))
        .peek(ch -> System.out.println("Digit: " + ch))
        .filter(i -> i != 0 && n % i == 0)
        .peek(System.out::println)
        .count();
  }

  public static int squares(int a, int b) {
//    return (int) IntStream.range(a, b + 1)
//        .parallel()
//        .filter(i -> StrictMath.sqrt(i) % 1 == 0)
//        .count();
//    System.out.println(" b: " + b);
//    System.out.println(" a: " + a);
//    System.out.println("sqrt b: " + Math.sqrt(b));
//    System.out.println("sqrt a: " + Math.sqrt(a));
//    System.out.println("sqrt b rounded: " + Math.round(Math.sqrt(b)));
//    System.out.println("sqrt a rounded: " + Math.round(Math.sqrt(a)));
//    System.out.println("sqrt b ceil: " + Math.ceil(Math.sqrt(b)));
//    System.out.println("sqrt a ceil: " + Math.ceil(Math.sqrt(a)));
//    System.out.println("sqrt b floor: " + Math.floor(Math.sqrt(b)));
//    System.out.println("sqrt a floor: " + Math.floor(Math.sqrt(a)));

    return (int) Math.floor(Math.sqrt(b)) - (int) Math.ceil(Math.sqrt(a)) + 1;
  }

  public static int libraryFine(int d1, int m1, int y1, int d2, int m2, int y2) {
    // Write your code here
    LocalDate returnDate = LocalDate.of(y1, m1, d1);
    LocalDate dueDate = LocalDate.of(y2, m2, d2);
    if (returnDate.isBefore(dueDate) || returnDate.isEqual(dueDate)) {
      return 0;
    } else {
      if (y1 > y2) {
        return 10000;
      }
      if (m1 > m2) {
        return (m1 - m2) * 500;
      }
      return (d1 - d2) * 15;
    }
  }

  public static List<Integer> cutTheSticks(List<Integer> arr) {

    List<Integer> res = new ArrayList<>();

    arr = arr.stream().sorted().collect(Collectors.toList());
    while (!arr.isEmpty()) {
      res.add(arr.size());
      int currMin = arr.get(0);
      while (true) {
        if (currMin == arr.get(0)) {
          arr.remove(0);
          if (arr.isEmpty()) {
            break;
          }
        } else {
          break;
        }
      }
    }
    return res;

  }


  public static int equalizeArray(List<Integer> arr) {

    int [] sizes = new int[100];

    for (int i = 1; i <= arr.size(); i++) {
      sizes[arr.get(i - 1)]++;
    }
    int max = Integer.MIN_VALUE;
    for (int size : sizes) {
      if (max < size) {
        max = size;
      }
    }
    return arr.size() - max;

  }



}


