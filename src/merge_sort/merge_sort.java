package merge_sort;

class Solution {

  /**
   * 归并排序：递归
   * 
   * @param arr
   */
  public void mergeSort1(int[] arr) {
    if (arr == null || arr.length < 2)
      return;
    process(arr, 0, arr.length - 1);
  }

  /**
   * 递归子过程
   * 
   * @param arr
   * @param l
   * @param r
   */
  private void process(int[] arr, int l, int r) {
    if (l == r)
      return;
    int mid = l + ((r - l) >> 1);
    process(arr, l, mid);
    process(arr, mid + 1, r);
    merge(arr, l, mid, r);
  }

  /**
   * 将给定区间数组进行排序(使用额外数组)
   * 
   * @param arr
   * @param l
   * @param mid
   * @param r
   */
  private void merge(int[] arr, int l, int mid, int r) {
    int[] help = new int[r - l + 1];
    int i = 0, p1 = l, p2 = mid + 1; // p1 [l~mid] p2 [mid+1~r]
    while (p1 <= mid && p2 <= r) {
      help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
    }

    // 要么p1越界、p2越界的情况
    while (p1 <= mid)
      help[i++] = arr[p1++];

    while (p2 <= r)
      help[i++] = arr[p2++];

    for (i = 0; i < help.length; i++)
      arr[l + i] = help[i];
  }

  /**
   * 归并排序（迭代）
   * 
   * @param arr
   */
  public void mergeSort2(int[] arr) {
    if (arr == null || arr.length < 2)
      return;
    int N = arr.length, mergeSize = 1;// mergeSize 步长
    while (mergeSize < N) {
      int l = 0;
      while (l < N) {
        if (mergeSize >= N - l) // 步长越界的情况
          break;

        int mid = l + mergeSize - 1;
        int r = mid + Math.min(mergeSize, N - mid - 1);
        merge(arr, l, mid, r);
        l = r + 1;
      }
      if (mergeSize > N / 2) // 防止溢出
        break;

      mergeSize <<= 1;
    }
  }

  // ==================== TEST ====================

  public int[] generateRandomArray(int maxSize, int maxValue) {
    int[] arr = new int[(int) ((maxValue + 1) * Math.random())];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
    }
    return arr;
  }

  public int[] copyArray(int[] arr) {
    if (arr == null)
      return null;
    int[] res = new int[arr.length];

    for (int i = 0; i < arr.length; i++)
      res[i] = arr[i];
    return res;
  }

  public boolean isEqual(int[] arr1, int[] arr2) {
    if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null))
      return false;
    if (arr1 == null && arr2 == null)
      return true;
    if (arr1.length != arr2.length)
      return false;

    for (int i = 0; i < arr1.length; i++) {
      if (arr1[i] != arr2[i])
        return false;
    }

    return true;
  }

  public void printArray(int[] arr) {
    if (arr == null)
      return;

    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int testTime = 500000, maxSize = 100, maxValue = 100;

    System.out.println("Begin");
    for (int i = 0; i < testTime; i++) {
      int[] arr1 = solution.generateRandomArray(maxSize, maxValue);
      int[] arr2 = solution.copyArray(arr1);
      solution.mergeSort1(arr1);
      solution.mergeSort2(arr2);

      if (!solution.isEqual(arr1, arr2)) {
        System.out.println("Error!");
        solution.printArray(arr1);
        solution.printArray(arr2);
        break;
      }
    }
    System.out.println("Finish!");
  }
}
