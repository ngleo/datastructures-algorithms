import modules.CsvReader;

/**
 * Merge sort for integers
 */
public class MergeSort {
  /**
   * Algorithm to sort and combine two sorted parts of an array
   *
   * @param arr  array of integers to be sorted
   * @param p    index of first element of the range to be sorted
   * @param m    index of the middle element of the range to be sorted
   * @param q    index of last element of the range to be sorted
   */
  private void merge(int arr[], int p, int m, int q) {
    // Count elements in each part of array
    int n1 = m - p + 1;
    int n2 = q - m;

    // Create new arrays and copy in data
    int L[] = new int[n1];
    int R[] = new int[n2];

    for (int i = 0; i < n1; i++) {
      L[i] = arr[p + i];
    }

    for (int j = 0; j < n2; j++) {
      R[j] = arr[m + 1 + j];
    }

    // Compare and fill in new arrays
    int i = 0;
    int j = 0;
    int k = p;

    while (i < n1 && j < n2) {
      if (L[i] > R[j]) {
        arr[k] = R[j];
        j++;
      } else {
        arr[k] = L[i];
        i++;
      }

      k++;
    }

    while (i < n1) {
      arr[k] = L[i];
      i++;
      k++;
    }

    while (j < n2) {
      arr[k] = R[j];
      j++;
      k++;
    }
  }

  /**
   * Recursively divides an array, then sorts and merges it back together
   *
   * @param arr array of integers to be sorted
   * @param p   index of first element of the range to be sorted
   * @param q   index of last element of the range to be sorted
   */
  private void sort(int arr[], int p, int q) {
    if (p < q) {
      int m = (p + q) / 2;

      // Recursively call sort
      sort(arr, p, m);
      sort(arr, m + 1, q);

      merge(arr, p, m, q);
    }
  }

  /**
   * Format output from sort
   *
   * @param arr array of integers to be sorted
   */
  private void output(int arr[]) {
    long startTime = System.currentTimeMillis();
    sort(arr, 0, arr.length - 1);
    long endTime = System.currentTimeMillis();
    long elapsedTime = startTime - endTime;

    System.out.println("Array sorted in : " + elapsedTime + " ms");

    for (int i : arr) {
      System.out.print(i + " ");
    }

    System.out.println(" ");
  }

  /**
   * Main function
   */
  public static void main(String[] args) {
    int arr[]  = {2,6,4,7,3};
    int arr2[] = {5, 10, 20, 3, 5, 12, 43, 12, 12};
    int arr3[] = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

    CsvReader cr = new CsvReader();
    MergeSort ob = new MergeSort();

    try {
      cr.readFile("./resources/intList.csv");
    } catch(Exception e) {
      e.printStackTrace();
    }

    int arr4[] = new int[cr.intList.size()];
    for (int i = 0; i < cr.intList.size(); i++) {
      arr4[i] = cr.intList.get(i).intValue();
    }

    ob.output(arr);
    ob.output(arr2);
    ob.output(arr3);
    ob.output(arr4);
  }


}
