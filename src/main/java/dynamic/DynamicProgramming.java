package dynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DynamicProgramming {

  private static final Map<Integer, Long> memoMapFib = new HashMap<>();
  private static final Map<Pair, Long> memoMapTravel = new HashMap<>();

  public static void main(String[] args) {

//    List<Integer> sa = new ArrayList<>();
//    sa.add(4);
//    sa.add(2);
//    sa.add(5);
//    sa = sa.stream().sorted().collect(Collectors.toList());
//
//
//    System.out.println("Fib of 50: " + memoFib(50));
//
//    System.out.println("How many paths of [18, 18] matrix: " + gridTraveller(18, 18));

    int[][] arr = {
        {7, 1, 4, 5, 6},
        {2, 5, 1, 6, 4},
        {1, 5, 4, 3, 2},
        {1, 2, 7, 3, 4}
    };
    largestMagicSquare(arr);
  }

  public static List<Integer> footballScores(List<Integer> teamA, List<Integer> teamB) {
    List<Integer> res = new ArrayList<>();

    List<Integer> teamASorted = teamA.stream().sorted().collect(Collectors.toList());
    for (Integer a : teamB) {

    }

    return res;
  }

  public static long memoFib(int n) {
    if (memoMapFib.containsKey(n)) {
      return memoMapFib.get(n);
    }
    if (n <= 2) {
      return 2;
    }
    memoMapFib.put(n, memoFib(n - 1) + memoFib(n - 2));
    return memoMapFib.get(n);

  }

  public static long gridTraveller(int rows, int col) {
    final Pair key = Pair.of(rows, col);
    if (memoMapTravel.containsKey(key)) {
      return memoMapTravel.get(key);
    }

    if (rows == 1 && col == 1) {
      return 1;
    }
    if (rows == 0 || col == 0) {
      return 0;
    }
    memoMapTravel.put(key, gridTraveller(rows - 1, col) + gridTraveller(rows, col - 1));
    return memoMapTravel.get(key);
  }

  public static int largestMagicSquare(int[][] grid) {
    int max = 1;
    int m = grid.length;
    int n = grid[0].length;
    int[][] row = new int[m][n]; // summ in rows
    int[][] col = new int[m][n]; // summ in columns
    int[][] pri = new int[m][n]; // summ in primary diag
    int[][] sec = new int[m][n]; // summ in secondary diag

    for(int i = 0; i < m; ++i) {
      pri[i][0] = row[i][0] = grid[i][0];
      sec[i][n - 1] = grid[i][n - 1];
    }

    //initialize cached sums
    for(int i = 0; i < n; ++i)
      sec[0][i] = pri[0][i] = col[0][i] = grid[0][i];

    for(int i = 0; i < m; ++i)
      for(int j = 1; j < n; ++j)
        row[i][j] = row[i][j - 1] + grid[i][j];

    for(int i = 1; i < m; ++i)
      for(int j = 0; j < n; ++j)
        col[i][j] = col[i - 1][j] + grid[i][j];

    for(int i = 1; i < m; ++i)
      for(int j = 1; j < n; ++j)
        pri[i][j] = pri[i - 1][j - 1] + grid[i][j];

    for(int i = 1; i < m; ++i)
      for(int j = n - 2; j >= 0; --j)
        sec[i][j] = sec[i - 1][j + 1] + grid[i][j];

     for(int i = 0; i < m; ++i) {
         for(int j = 0; j < n; ++j)
             System.out.print(sec[i][j] + " ");
         System.out.println();
     }

     //main logic
    for(int i = 0; i < m; ++i) {
      for(int j = 0; j < n; ++j) {
        int right = j, down = i;

        while(right < n && down < m) {
          boolean flag = true;
          int rowSum = j > 0 ? row[i][right] - row[i][j - 1] : row[i][right];
          int colSum = i > 0 ? col[down][j] - col[i - 1][j] : col[down][j];

          for(int k = j + 1; k <= right; ++k) {
            int curColSum = i > 0 ? col[down][k] - col[i - 1][k] : col[down][k];

            if(curColSum != colSum) {
              flag = false;

              break;
            }
          }

          if(flag) {
            for(int k = i + 1; k <= down; ++k) {
              int curRowSum = j > 0 ? row[k][right] - row[k][j - 1] : row[k][right];

              if(curRowSum != rowSum) {
                flag = false;

                break;
              }
            }
          }

          if(flag) {
            int dia1 = j > 0 && i > 0 ? pri[down][right] - pri[i - 1][j - 1] : pri[down][right];
            int dia2 = i > 0 && right < n - 1 ? sec[down][j] - sec[i - 1][right + 1] : sec[down][j];

            if(dia1 != dia2 || rowSum != colSum || dia1 != rowSum)
              flag = false;
          }

          if(flag) {
            max = Math.max(down - i + 1, max);
          }

          ++right;
          ++down;
        }
      }
    }

    return max;
  }

}
