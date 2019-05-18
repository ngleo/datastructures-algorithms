package datastructures;

public class UnionFind {
  private int size;
  // Number of elements in the set that int[x] belongs to
  private int[] sizeOfComponent;
  // The index of the root of int[x]
  private int[] roots;
  // Number of sets in the union find
  private int numOfComponents;

  public UnionFind(int size) {
    if (size <= 0) throw new IllegalArgumentException();

    this.size = size;
    numOfComponents = size;

    sizeOfComponent = new int[size];
    roots = new int[size];

    for (int i = 0; i < size; i++) {
      sizeOfComponent[i] = 1;
      roots[i] = i;
    }
  }

  public int size() { return size; }
  public int numOfComponents() { return numOfComponents; }

  /**
   * Find the root element of the element at given index
   */
  public int find(int index) {
    int root = index;

    while (root != roots[root]) {
       root = roots[root];
    }

    // Path compression: gives unify constant time
    while (index != root) {
      int swap = roots[index];
      roots[index] = root; // Update root
      sizeOfComponent[index] = sizeOfComponent[root]; // Update Size of components
      index = swap;
    }

    return root;
  }

  /**
   * Group two components by pointing root of component with fewer elements to the one with more
   */
  public void unify(int p, int q) {
    int root1 = find(p);
    int root2 = find(q);

    if (root1 == root2) return;;

    if (sizeOfComponent[root1] >= sizeOfComponent[root2]) {
      sizeOfComponent[root1] += sizeOfComponent[root2];
      roots[q] = p;
    } else {
      sizeOfComponent[root2] += sizeOfComponent[root1];
      roots[p] = q;
    }

    numOfComponents--;
  }

  /**
   * Main function
   */
  public static void main(String[] args) {
    UnionFind unionfind = new UnionFind(10);

    unionfind.unify(1, 3);
    unionfind.unify(3, 2);
    unionfind.find(2);

//
//    for (int i = 0 ; i < unionfind.size(); i++) {
//      unionfind.find(i);
//    }


    System.out.println("Roots:");
    for (int i = 0; i < unionfind.size(); i++) {
      System.out.print(unionfind.roots[i] + " ");
    }

    System.out.println("");
    System.out.println("Size of components:");
    for (int i = 0; i < unionfind.size(); i++) {
      System.out.print(unionfind.sizeOfComponent[i] + " ");
    }

  }
}
