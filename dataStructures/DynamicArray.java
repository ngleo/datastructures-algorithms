package dataStructures;

/**
 * Dynamic Array that stores integers
 */

public class DynamicArray {

  private int[] arr;
  private int len = 0;
  private int capacity = 0;

  public DynamicArray() { this(16); }

  public DynamicArray(int cap) {
    if (cap < 0) throw new IllegalArgumentException(("Illegal capacity"));
    capacity = cap;
    arr = new int[capacity];
  }

  public int size() { return len; }
  public boolean isempty() { return len == 0; }

  public int get(int index) { return arr[index]; }
  public void set(int index, int data) { arr[index] = data; }

  public void clear() {
    arr = new int[16];  // point it to new array since int cannot be null?
    len = 0;
    capacity = 16;
  }

  /**
   * Add an element into array
   */
  public void add(int data) {
    if (len == capacity) {
      capacity *= 2;
      int[] newArray = new int[ capacity ];

      for (int i = 0; i < len; i++) {
        newArray[i] = arr[i];
      }

      arr = newArray;
    }
    arr[len++] = data;
  }

  /**
   * Remove element at index
   */
  public void remove(int index) {
    if (index < 0 || index >= len) throw new IndexOutOfBoundsException();
    len -= 1;
    capacity = len;
    int[] newArray = new int[capacity];

    int i = 0;
    while (i < index) {
      newArray[i] = arr[i];
      i++;
    }
    while (i < len) {
      newArray[i] = arr[i + 1];
      i++;
    }

    arr = newArray;
  }

  /**
   * Main function
   */
  public static void main(String[] args) {
    DynamicArray array = new DynamicArray(5);
    array.add(1);
    array.add(2);
    array.add(3);
    array.add(4);
    array.add(5);
    array.add(6);
    array.add(7);

    System.out.println("");
    array.remove(3);
    array.remove(3);

    for (int i = 0; i < array.size(); i++) {
      System.out.print(array.get(i) + " ");
    }

    System.out.println(" ");
    System.out.println("Size: " + array.size());
    System.out.println("Capacity: " + array.capacity);

    array.clear();
    System.out.println(" ");
    System.out.println("Size: " + array.size());
    System.out.println("Capacity: " + array.capacity);

  }


}
