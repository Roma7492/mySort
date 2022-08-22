package dynamic;

public class Pair {
  int  left;
  int right;

  private Pair(int left, int right) {
    this.left = left;
    this.right = right;
  }

  public static Pair of(int left, int right) {
    return new Pair(left, right);
  }

  @Override
  public int hashCode() {
    return Integer.hashCode(right) + Integer.hashCode(left);
  }

  @Override
  public boolean equals(Object pair) {
    if (this == pair) {
      return true;
    }
    if (!(pair instanceof Pair)) {
      return false;
    }
    Pair in = (Pair) pair;
    return in.right == this.right && in.left == this.left;
  }

}
