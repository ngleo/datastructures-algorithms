package datastructures;

import java.util.LinkedList;

/**
 * Linked List implementation of a queue for a certain data type
 */

public class Queue<T> {
  private LinkedList<T> list = new LinkedList<>();

  public int size() { return list.size(); }
  public boolean isEmpty() { return list.isEmpty(); }
  public T get(int index) { return list.get(index); }
  public void set(int index, T elem) { list.set(index, elem); }

  /**
   * Add element to the end of queue
   */
  public void enqueue(T elem) {
      list.addLast(elem);
  }

  /**
   * Remove first element from queue
   */
  public T dequeue() {
    return(list.removeFirst());
  }

  public T peek() {
    return(list.peekFirst());
  }

  /**
   * Main Function
   */
  public static void main(String[] args) {
   Queue<Integer> queue = new Queue<>();
   queue.enqueue(6);
   queue.enqueue(4);
   queue.enqueue(3);
   queue.enqueue(2);
   queue.enqueue(1);
   queue.set(0, 5);

   while (queue.size() > 0) {
     System.out.println(queue.dequeue());
   }

   System.out.println(queue.size());
  }
}
