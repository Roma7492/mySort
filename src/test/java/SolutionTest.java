import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class SolutionTest {

  @Test
  @Ignore
  public void checkSimpleSolution() {
    Assert.assertEquals(2, Result.introTutorial(4, List.of(1, 2, 4)));
  }

  @Test
  public void checkInsertionSort() {
    List<Integer> arr = List.of(1, 3, 5, 9, 13, 22, 27, 35,  46, 51, 55, 83, 87, 23);

    Result.insertionSort1(14, arr);
  }

  @Test
  public void checkInsertionSort2() {
    List<Integer> arr = List.of(2, 3, 4, 5, 6, 7, 8, 9, 10, 1);

    Result.insertionSort1(10, arr);
  }

  @Test
  public void checkInsertionSortEntireArray() {
    List<Integer> arr = List.of(3, 4, 7, 5, 6, 2, 1);

    Result.insertionSort2(7, arr);
  }

  @Test
  public void checkInsertionSortEntireArray2() {
    List<Integer> arr = List.of(1, 4, 3, 5, 6, 2);

    Result.insertionSort2(6, arr);
  }

  @Test
  public void checkInsertionSortEntireArray3() {
    List<Integer> arr = List.of(8, 7, 6, 5, 4, 3, 2, 1);

    Result.insertionSort2(8, arr);
  }

  @Test
  public void checkStack() {
    List<String> ops = List.of("1 97", "2", "1 20", "2", "1 26", "1 20", "2", "3", "1 91", "3", "1 600", "1 520", "3");

    Assert.assertEquals(ops.stream().filter(o -> o.equals("3")).count(), Result.getMax(ops).size());
  }

  @Test
  public void checkCountSort() {
    List<Integer> ops = List.of(1, 3, 64, 1, 1);

    Result.countingSort(ops);
  }

}
