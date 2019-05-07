package dataStructures;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * List implementation of a stack for a certain data type
 */

public class Stack<T> {
  private ArrayList<T> list = new ArrayList<>();

  public int size() { return list.size(); }
  public boolean isempty() { return list.isEmpty(); }
  public T get(int index) { return list.get(index); }
  /**
   * Add element to top of stack
   */
  public void push(T elem) {
    list.add(elem);
  }

  /**
   * Remove element from top of stack
   */
  public void pop() {
    if (list.isEmpty()) throw new EmptyStackException();
    // TODO return the popped element?
    list.remove(list.size() - 1);
  }

  /**
   * Return value at the top without changing it
   */
  public T peek() {
    if (list.isEmpty()) throw new EmptyStackException();
    return list.get(list.size() - 1);
  }

  /**
   * Main Function
   */
  public static void main(String[] args) {
    // String
    Stack<String> stack = new Stack<>();
    stack.push("My");
    stack.push("name");
    stack.push("is");
    stack.push("Mr.");
    stack.push("Bond");

    if (stack.peek() == "Bond") {
      stack.pop();
    }

    for (int i = 0; i < stack.size(); i++) {
      System.out.println(stack.get(i));
    }

  }
}
