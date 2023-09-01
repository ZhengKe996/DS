package count_sort;

import java.util.Arrays;

class Solution {
  /**
   * only for 0~200 value
   * 
   * @param arr
   */
  public void countingSort(int[] arr) {
    if (arr == null || arr.length < 2)
      return;

    int max = Integer.MIN_VALUE;

    for (int i = 0; i < arr.length; i++)
      max = Math.max(max, arr[i]); // 第一步 找到数组中的最大值

    int[] bucket = new int[max + 1]; // 第二步 新建桶 存储 arr中数出现的次数
    for (int i = 0; i < arr.length; i++)
      bucket[arr[i]]++;

    int i = 0;
    for (int j = 0; j < bucket.length; j++) { // 第三步 依次将 bucket中的数据倒出
      while (bucket[j]-- > 0)
        arr[i++] = j;
    }
  }

  // ==================== TEST ====================
  public void comparator(int[] arr) {
    Arrays.sort(arr);
  }

  public int[] generateRandomArray(int maxSize, int maxValue) {
    int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) ((maxValue + 1) * Math.random());
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
    Solution solution = new Solution();
    int testTime = 500000, maxSize = 100, maxValue = 150;
    boolean succeed = true;

    for (int i = 0; i < testTime; i++) {
      int[] arr1 = solution.generateRandomArray(maxSize, maxValue);
      int[] arr2 = solution.copyArray(arr1);
      solution.countingSort(arr1);
      solution.comparator(arr2);
      if (!solution.isEqual(arr1, arr2)) {
        succeed = false;
        solution.printArray(arr1);
        solution.printArray(arr2);
        break;
      }
    }
    System.out.println(succeed ? "Nice!" : "Fucking fucked!");
  }
}
