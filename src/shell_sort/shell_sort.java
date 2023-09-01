package shell_sort;

class Solution {
  /**
   * 希尔排序
   * 这个排序就是调整步长的插入排序，也可以认为是插入排序的小改进版本；改变不了时间复杂度，只是优化了常数时间
   * 
   * @param arr
   */
  public void shellSort(int[] arr) {
    if (arr == null || arr.length < 2)
      return;

    int[] step = { 5, 2, 1 };
    for (int s = 0; s < step.length; s++) {
      for (int i = step[s]; i < arr.length; i++) {
        for (int j = i - step[s]; j >= 0 && arr[j] > arr[j + step[s]]; j -= step[s]) {
          swap(arr, j, j + step[s]);
        }
      }
    }
  }

  private void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  // ================ TEST ================
  public void insertionSort(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    // 不只1个数
    for (int i = 1; i < arr.length; i++) { // 0 ~ i 做到有序
      for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
        swap(arr, j, j + 1);
      }
    }
  }

  public int[] generateRandomArray(int len, int maxValue) {
    int[] arr = new int[len];
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
    Solution solution = new Solution();
    int testTime = 500000, maxSize = 100, maxValue = 100;
    boolean succeed = true;
    for (int i = 0; i < testTime; i++) {
      int len = (int) (Math.random() * maxSize);
      int[] arr1 = solution.generateRandomArray(len, maxValue);
      int[] arr2 = solution.copyArray(arr1);
      solution.shellSort(arr1);
      solution.insertionSort(arr2);
      if (!solution.isEqual(arr1, arr2)) {
        succeed = false;
        solution.printArray(arr1);
        solution.printArray(arr2);
        break;
      }
    }
    System.out.println(succeed ? "功能测试通过！" : "功能测试不通过！");

    int len = 100000;
    System.out.println("数据样本长度 : " + len);
    int[] arr1 = solution.generateRandomArray(len, maxValue);
    int[] arr2 = solution.copyArray(arr1);
    long start = 0;
    long end = 0;
    start = System.currentTimeMillis();
    solution.shellSort(arr1);
    end = System.currentTimeMillis();
    System.out.println("希尔排序运行时间 : " + (end - start) + " 毫秒");
    start = System.currentTimeMillis();
    solution.insertionSort(arr2);
    end = System.currentTimeMillis();
    System.out.println("插入排序运行时间 : " + (end - start) + " 毫秒");
  }
}
