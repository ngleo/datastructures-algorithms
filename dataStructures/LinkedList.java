package dataStructures;

/**
 * Singly linked list that stores integers
 */
public class LinkedList {
  Node head;

  static class Node {
    int data;
    Node next;

    Node(int d) {
      data = d;
      next = null;
    }
  }

  private LinkedList insert(LinkedList list, int d) {
    Node newNode = new Node(d);
    newNode.next = null;

    if (list.head == null) {
      list.head = newNode;
    } else {
      Node last = list.head;
      while (last.next != null) {
        last = last.next;
      }
      last.next = newNode;
    }

    return list;
  }

  private void print() {
    Node last = this.head;


    if (last == null) {
      System.out.println("empty list");
    } else {
      while (last != null) {
        System.out.println(last.data);
        last = last.next;
      }
    }
  }

  public static void main(String[] args) {
    LinkedList list = new LinkedList();

    list.insert(list, 3);
    list.insert(list, 5);
    list.insert(list, 2);
    list.insert(list, 6);
    list.insert(list, 8);
    list.insert(list, 3);
    list.insert(list, 1);

    list.print();

    LinkedList list2 = new LinkedList();

    list2.print();
  }
}
