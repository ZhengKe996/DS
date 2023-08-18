package base_sort;

import java.util.Arrays;

class Solution {
  private void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  /**
   * 选择排序
   * 
   * @param arr
   */
  public void SelectSort(int[] arr) {
    if (arr == null || arr.length < 2)
      return;

    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[i] > arr[j]) {
          swap(arr, i, j);
        }
      }
    }
  }

  /**
   * 冒泡排序
   * 
   * @param arr
   */
  public void BubbleSort(int[] arr) {
    if (arr == null || arr.length < 2)
      return;

    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < i; j++) {
        if (arr[i] < arr[j]) {
          swap(arr, i, j);
        }
      }
    }
  }

  /**
   * 插入排序
   * 
   * @param arr
   */
  public void InsertSort(int[] arr) {
    if (arr == null || arr.length < 2)
      return;
    for (int i = 0; i < arr.length; i++) {
      for (int j = i - 1; j >= 0; j--) {
        if (arr[j + 1] < arr[j]) {
          swap(arr, j + 1, j);
        }
      }
    }
  }

  /**
   * 快速排序
   * 
   * @param arr
   */
  public void QuickSort(int[] arr) {
    if (arr == null || arr.length == 0)
      return;
    QuickSort(arr, 0, arr.length - 1);
  }

  private void QuickSort(int[] arr, int l, int r) {

    if (l >= r)
      return;

    int i = l - 1, j = r + 1, x = arr[l + ((r - l) >> 1)];
    while (i < j) {
      while (arr[++i] < x)
        ;
      while (arr[--j] > x)
        ;
      if (i < j)
        swap(arr, i, j);
    }

    QuickSort(arr, l, j);
    QuickSort(arr, j + 1, r);
  }

  /**
   * Test Sort
   * 
   * @param arr
   */
  public void comparator(int[] arr) {
    Arrays.sort(arr);
  }

  /**
   * Test 生成器
   * 
   * @param maxSize
   * @param maxValue
   * @return
   */
  public int[] generateRandomArray(int maxSize, int maxValue) {
    int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
    }
    return arr;
  }

  /**
   * Test CopyArray
   * 
   * @param arr
   * @return
   */
  public int[] copyArray(int[] arr) {
    if (arr == null)
      return null;

    int[] res = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      res[i] = arr[i];
    }
    return res;
  }

  /**
   * Test isEqual
   * 
   * @param arr1
   * @param arr2
   * @return
   */
  public boolean isEqual(int[] arr1, int[] arr2) {
    if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null))
      return false;

    if (arr1 == null && arr2 == null)
      return true;

    if (arr1.length != arr2.length)
      return false;

    for (int i = 0; i < arr1.length; i++)
      if (arr1[i] != arr2[i])
        return false;

    return true;

  }

  /**
   * Test PrintArray
   * 
   * @param arr
   */
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
    Solution solution = new Solution();

    int testTime = 500000;
    int maxSize = 100;
    int maxValue = 100;
    boolean succeed = true;

    for (int i = 0; i < testTime; i++) {
      int[] arr1 = solution.generateRandomArray(maxSize, maxValue);
      int[] arr2 = solution.copyArray(arr1);
      int[] arr3 = solution.copyArray(arr1);
      int[] arr4 = solution.copyArray(arr1);

      solution.BubbleSort(arr1);
      solution.SelectSort(arr2);
      solution.InsertSort(arr3);
      solution.QuickSort(arr4);

      if (!solution.isEqual(arr1, arr2)) {
        System.out.println("arr1, arr2:");
        succeed = false;
        solution.printArray(arr1);
        solution.printArray(arr2);
        break;
      }

      if (!solution.isEqual(arr2, arr3)) {
        System.out.println("arr2, arr3:");
        succeed = false;
        solution.printArray(arr2);
        solution.printArray(arr3);
        break;
      }

      if (!solution.isEqual(arr3, arr4)) {
        System.out.println("arr3, arr4:");
        succeed = false;
        solution.printArray(arr3);
        solution.printArray(arr4);
        break;
      }
    }

    System.out.println(succeed ? "Nice!" : "Fucking fucked!");
  }
}