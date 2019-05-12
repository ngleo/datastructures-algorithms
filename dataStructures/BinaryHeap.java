package datastructures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Binary Heap implemented with Java ArrayList
 */

public class BinaryHeap<T extends Comparable<T>> {
  private List<T> heap = null;

  public BinaryHeap() { this(1); }

  public BinaryHeap(int size) {
    heap = new ArrayList<>(size);
  }

  public BinaryHeap(T[] list) {
    this(list.length);
    for (int i = 0; i < list.length; i++) {
      add(list[i]);
    }
  }

  public BinaryHeap(Collection<T> list) {
    this(list.size());
    for (T elem : list) add(elem);
  }

  // getters & setters
  public int size() { return heap.size(); }

  public boolean isEmpty() { return heap.isEmpty(); }

  public T get(int index) { return heap.get(index); }

  public void set(int index, T elem) { heap.set(index, elem); }

  /**
   * Return first value if heap not empty
   */
  public T peek() {
    if (heap.isEmpty()) { return null; }
    else {
      return heap.get(0);
    }
  }

  /**
   * Get rid of first element
   */
  public T poll() {
    return removeAt(0);
  }

  /**
   * Add element into heap
   */
  public void add(T elem) {
    if (elem == null) {
      throw new IllegalArgumentException();
    } else {
      heap.add(elem);
      swim(heap.size() - 1);
    }
  }

  /**
   * Remove element by searching for the first result that matches the argument
   */
  public T remove(T elem) {
    int index = heap.lastIndexOf(elem);
    if (index >= 0) {
      return removeAt(index);
    } else {
      return null;
    }
  }

  /**
   * Remove element at given index
   */
  public T removeAt(int index) {
    if (index < 0 || index >= heap.size()) {
      throw new ArrayIndexOutOfBoundsException("Please enter a valid index");
    }

    int lastIndex = heap.size() - 1;
    if (index < lastIndex) {
      swap(index, lastIndex);
      T removedData = heap.remove(lastIndex);
      swim(index);
      sink(index);
      return removedData;
    } else {
      return heap.remove(lastIndex);
    }
  }

  /**
   * Bubble an element up the heap by comparing parent and child nodes
   */
  private void swim(int index) {
    int parent = (index - 1)/2;

    while (index > 0 && less(index, parent)) {
      swap(index, parent);

      index = parent;
      parent = (index - 1)/2;
    }
  }

  /**
   * Bubble an element down the heap by comparing parent and child nodes
   */
  private void sink(int index) {
    while (true) {
      int leftChild = index * 2 + 1;
      int rightChild = index * 2 + 2;
      int smallest = leftChild;

      if (rightChild < heap.size() && less(rightChild, leftChild)) {
        smallest = rightChild;
      }

      if (leftChild < heap.size() && less(smallest, index)) {
        swap(smallest, index);
      } else {
        break;
      }
      index = smallest;
    }
  }

  /**
   * Swap the elements at given indices
   */
  private void swap(int index1, int index2) {
    T node1 = heap.get(index1);
    T node2 = heap.get(index2);

    heap.set(index1, node2);
    heap.set(index2, node1);
  }

  /**
   * Compare the variables of type T
   */
  private boolean less(int index1, int index2) {
    T node1 = heap.get(index1);
    T node2 = heap.get(index2);
    return node1.compareTo(node2) <= 0;
  }

  /**
   * Check the heap invariant
   */
  private void check() {
    // Check the Heap invariant
    int mistakes = 0;

    for (int i = 0 ; i < heap.size(); i++) {
//      System.out.println(heap.get(i));
      int leftChild = i * 2 + 1;
      int rightChild = i * 2 + 2;

      if (leftChild <= heap.size() - 1 && !less(i, leftChild)) {
          System.out.println("Left Child of index " + i + " is wrong");
          mistakes++;
        }

      if (rightChild <= heap.size() - 1 && !less(i, rightChild)) {
          System.out.println("Right Child of index " + i + " is wrong");
          mistakes++;
        }
      }

      if (mistakes == 0) {
        System.out.println("Heap is correct");
      } else {
        System.out.println("Heap has " + mistakes + " mistakes");
      }
    }

  /**
   * Main Function
   */
  public static void main(String[] args) {
    BinaryHeap<Integer> heap = new BinaryHeap();
    heap.add(13);
    heap.add(9);
    heap.add(1);
    heap.add(14);
    heap.add(1);
    heap.add(9);
    heap.add(3);
    heap.add(3);
    heap.add(5);
    heap.add(12);
    heap.add(7);
    heap.add(2);
    heap.add(5);
    heap.remove(14);
    heap.remove(10);
    heap.removeAt(5);
    heap.check();


    // Poll everything
    System.out.println("");
    System.out.println("Polling:");
    while(!heap.isEmpty()) {
      System.out.println("");
      System.out.println(heap.poll());
      heap.check();
    }

    Integer[] list = new Integer[] {37,34,13,5,7,3,43,7,23,54,12,1,51,24};
    BinaryHeap<Integer> heap2 = new BinaryHeap(list);
    heap2.check();
  }
}
