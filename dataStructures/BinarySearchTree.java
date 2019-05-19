package datastructures;

import java.util.Iterator;

public class BinarySearchTree<T extends Comparable<T>> {
  private int nodeCount = 0;
  private Node root = null;

  private class Node {
    private T data;
    private Node left;
    private Node right;

    public Node(T elem, Node left, Node right) {
      data = elem;
      this.left = left;
      this.right =right;
    }
  }

  public boolean isEmpty() { return nodeCount == 0; }
  public int size() { return nodeCount; }

  /**
   * Add input into BST, returns true if the input is inserted
   */
  public boolean add(T elem) {
    if (contains(elem)) {
      return false;
    } else {
      root = add(root, elem);
      nodeCount++;
      return true;
    }
  }

  /**
   * Remove given element from BST
   */
  public boolean remove(T elem) {
    if (contains(elem)) {
      root = remove(root, elem);
      nodeCount--;
      return true;
    } else {
      return false;
    }
  }


  // Add elem into BST by recursively traverse down the input node
  private Node add(Node node, T elem) {
    if (node == null) {
      node = new Node(elem, null, null);
    } else {
      if (elem.compareTo(node.data) < 0) {
        node.left = add(node.left, elem);
      } else {
        node.right = add(node.right, elem);
      }
    }

    return node;
  }

  // Remove elem from BST by recursively traverse down the input node and adjust tree
  private Node remove(Node node, T elem) {
    if (node != null) return node;
    int diff = elem.compareTo(node.data);

    if (diff < 0) {
      node.left = remove(node.left, elem);
    } else if (diff > 0) {
      node.right = remove(node.right, elem);
    } else { // current node matches elem

      if (node.left == null) { // only has right child or no child
        Node rightNode = node.right;

        node.data = null;
        node = null;

        return rightNode;
      } else if (node.right == null) { // only has left child or no child
        Node leftNode = node.left;

        node.data = null;
        node = null;

        return leftNode;
      } else {
        Node temp = findMax(node.left);

        node.data = temp.data;

        node = remove(node.left, temp.data);
      }
    }

    return node;
  }

  // Find Max node in a path
  private Node findMax(Node node) {
    while(node.right != null)
      node = node.right;
    return node;
  }

  // Find Min node in a path
  private Node findMin(Node node) {
    while (node.left != null)
      node = node.left;
    return node;
  }

  // Check if input is contained in class root
  private boolean contains(T elem) {
    return(contains(root, elem));
  }

  // Recursively check if input elem is contained in given node and child nodes
  private boolean contains(Node node, T elem) {
    if (node == null) {
      return false;
    } else {
      if (elem.compareTo(node.data) < 0) {
        return(contains(node.left, elem));
      } else if (elem.compareTo(node.data) > 0) {
        return(contains(node.right, elem));
      } else {
        return true;
      }
    }
  }

  // Returns preorder iterator
  private java.util.Iterator<T> preOrderTraversal() {
    final int iteratorNodeCount = nodeCount;
    final java.util.Stack<Node> stack = new java.util.Stack<> ();
    stack.push(root);

    return new java.util.Iterator<T> () {
      @Override
      public boolean hasNext() {
        if (iteratorNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
        return root != null && !stack.isEmpty();
      }

      @Override
      public T next() {
        if (iteratorNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
        Node node = stack.pop();
        if (node.left != null) stack.push(node.left);
        if (node.right != null) stack.push(node.right);

        return node.data;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  //Returns inorder iterator
  private java.util.Iterator<T> inOrderTraversal() {
    final int iteratorNodeCount = nodeCount;
    final java.util.Stack<Node> stack = new java.util.Stack<> ();
    stack.push(root);

    return new java.util.Iterator<T> () {
      Node curr = root;
      @Override
      public boolean hasNext() {
        if (iteratorNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
        return root != null && !stack.isEmpty();
      }

      @Override
      public T next() {
        if (iteratorNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
        while (curr != null && curr.left != null) {
          stack.push(curr.left);
          curr = curr.left;
        }

        Node node = stack.pop();

        if (node.right != null) {
          stack.push(node.right);
          curr = node.right;
        }

        return node.data;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  /**
   * Main function
   */
  public static void main(String[] args) {
    BinarySearchTree<Integer> bst1 = new BinarySearchTree();
    bst1.add(5);
    bst1.add(5);
    bst1.add(3);
    bst1.add(2);
    bst1.add(7);
    bst1.add(4);
    bst1.add(11);
    bst1.add(8);
    bst1.add(6);
    bst1.add(1);

    System.out.println("nodeCount: " + bst1.nodeCount);
    Iterator<Integer> bstInOrder1 = bst1.inOrderTraversal();

    while (bstInOrder1.hasNext()) {
      System.out.print(bstInOrder1.next() + " ");
      System.out.println(bstInOrder1.hasNext());
    }
  }

}
