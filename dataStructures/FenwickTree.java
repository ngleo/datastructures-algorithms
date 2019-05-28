package datastructures;

public class FenwickTree {
  private int size;
  private int[] tree;

  public FenwickTree(int[] values) {
    if (values == null) throw new IllegalArgumentException("Input is null");

    size = values.length;
    tree = values.clone();

    for (int i = 0; i < size; i++) {
      int parent = i + Integer.lowestOneBit(i);
      if (parent < size) {
        tree[parent] += tree[i];
      }
    }
  }

  public int size() { return size; }

  /**
   * Return the sum of range 1 - index
   */
  public int sum(int index) {
    return prefixSum(index);
  }

  /**
   * Find the sum of the range of int between left and right
   */
  public int sum(int left, int right) {
    if (left > right) throw new IllegalArgumentException("Left has to be smaller than right");
    return prefixSum(right) - prefixSum(left);
  }

  // Return sum of range 1 - index
  private int prefixSum(int index) {
    int sum = 0;

    while (index != 0) {
      sum += tree[index];
      index -= Integer.lowestOneBit(index);
    }
    return sum;
  }

  public static void main(String args[]) {
    int[] array = new int[] { 3, 5, 2, 9, 12, 4, 6, 1, 15, 28, 0, 10};
    FenwickTree tree = new FenwickTree(array);

    for (int i = 0; i < tree.size(); i++) {
      System.out.println(tree.sum(i));
    }

    System.out.println(tree.sum(5, 10));
    System.out.println(tree.sum(10, 7));
  }
}
