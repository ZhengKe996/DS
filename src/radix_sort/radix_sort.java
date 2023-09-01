package radix_sort;

import java.util.Arrays;

class Solution {

  /**
   * only for no-negative value
   * 
   * @param arr
   */
  public void radixSort(int[] arr) {
    if (arr == null || arr.length < 2)
      return;

    radixSort(arr, 0, arr.length - 1, maxbits(arr));
  }

  private int maxbits(int[] arr) {
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < arr.length; i++)
      max = Math.max(max, arr[i]);

    int res = 0;
    while (max != 0) {
      res++;
      max /= 10;
    }
    return res;
  }

  private int getDigit(int x, int d) {
    return ((x / ((int) Math.pow(10, d - 1))) % 10);
  }

  private void radixSort(int[] arr, int l, int r, int digit) {
    final int radix = 10;
    int i = 0, j = 0;
    int[] help = new int[r - l + 1];
    for (int d = 1; d <= digit; d++) {
      int[] count = new int[radix];
      for (i = l; i <= r; i++) {
        j = getDigit(arr[i], d);
        count[j]++;
      }
      for (i = 1; i < radix; i++)
        count[i] = count[i] + count[i - 1];

      for (i = r; i >= l; i--) {
        j = getDigit(arr[i], d);
        help[count[j] - 1] = arr[i];
        count[j]--;
      }
      for (i = l, j = 0; i <= r; i++, j++)
        arr[i] = help[j];
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
      solution.radixSort(arr1);
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
