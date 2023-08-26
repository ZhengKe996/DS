package heap;

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution2 {
  /**
   * 堆排序 时间复杂度 O(N*logN)
   * 
   * @param arr
   */
  public void heapSort1(int[] arr) {
    if (arr == null || arr.length < 2)
      return;

    for (int i = 0; i < arr.length; i++) {
      insert(arr, i);
    }

    int heapSize = arr.length;
    swap(arr, 0, --heapSize);
    while (heapSize > 0) {
      heapify(arr, 0, heapSize);
      swap(arr, 0, --heapSize);
    }
  }

  /**
   * 堆排序 时间复杂度O(N)
   * 
   * @param arr
   */
  public void heapSort2(int[] arr) {
    if (arr == null || arr.length < 2)
      return;

    for (int i = arr.length - 1; i >= 0; i--)
      heapify(arr, i, arr.length);

    int heapSize = arr.length;
    swap(arr, 0, --heapSize);
    while (heapSize > 0) {
      heapify(arr, 0, heapSize);
      swap(arr, 0, --heapSize);
    }
  }

  private void insert(int[] arr, int index) {
    while (arr[index] > arr[(index - 1) / 2]) {
      swap(arr, index, (index - 1) / 2);
      index = (index - 1) / 2;
    }
  }

  private void heapify(int[] arr, int index, int heapSize) {
    int l = index * 2 + 1;
    while (l < heapSize) {
      int largest = l + 1 < heapSize && arr[l + 1] > arr[l] ? l + 1 : l;
      largest = arr[largest] > arr[index] ? largest : index;
      if (largest == index)
        break;

      swap(arr, largest, index);
      index = largest;
      l = index * 2 + 1;
    }
  }

  private void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  // ============== TEST ==============

  public void comparator(int[] arr) {
    Arrays.sort(arr);
  }

  public int[] generateRandomArray(int maxSize, int maxValue) {
    int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
    }
    return arr;
  }

  public int[] copyArray(int[] arr) {
    if (arr == null) {
      return null;
    }
    int[] res = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      res[i] = arr[i];
    }
    return res;
  }

  public boolean isEqual(int[] arr1, int[] arr2) {
    if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
      return false;
    }
    if (arr1 == null && arr2 == null) {
      return true;
    }
    if (arr1.length != arr2.length) {
      return false;
    }
    for (int i = 0; i < arr1.length; i++) {
      if (arr1[i] != arr2[i]) {
        return false;
      }
    }
    return true;
  }

  public void printArray(int[] arr) {
    if (arr == null) {
      return;
    }
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Solution2 solution = new Solution2();
    // 默认小根堆
    PriorityQueue<Integer> heap = new PriorityQueue<>();
    heap.add(6);
    heap.add(8);
    heap.add(0);
    heap.add(2);
    heap.add(9);
    heap.add(1);

    while (!heap.isEmpty()) {
      System.out.print(heap.poll());
    }
    System.out.println();

    int testTime = 500000, maxSize = 100, maxValue = 100;
    boolean succeed = true;
    for (int i = 0; i < testTime; i++) {
      int[] arr1 = solution.generateRandomArray(maxSize, maxValue);
      int[] arr2 = solution.copyArray(arr1);
      int[] arr3 = solution.copyArray(arr1);
      solution.heapSort1(arr1);
      solution.heapSort2(arr2);
      solution.comparator(arr3);
      if (!solution.isEqual(arr1, arr2) || !solution.isEqual(arr2, arr3)) {
        succeed = false;
        break;
      }
    }
    System.out.println(succeed ? "Nice!" : "Fucking fucked!");

  }

}