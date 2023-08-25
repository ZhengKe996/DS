package merge_sort;

class Solution2 {

  /**
   * 小和问题
   * 
   * @param arr
   * @return
   */
  public int smallSum(int[] arr) {
    if (arr == null || arr.length < 2)
      return 0;

    return process(arr, 0, arr.length - 1);
  }

  private int process(int[] arr, int l, int r) {
    if (l == r)
      return 0;
    int mid = l + ((r - l) >> 1);
    return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
  }

  private int merge(int[] arr, int l, int mid, int r) {
    int[] help = new int[r - l + 1];
    int i = 0, p1 = l, p2 = mid + 1, res = 0;
    while (p1 <= mid && p2 <= r) {
      res += arr[p1] < arr[p2] ? arr[p1] * (r - p2 + 1) : 0;
      help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
    }

    while (p1 <= mid)
      help[i++] = arr[p1++];
    while (p2 <= r)
      help[i++] = arr[p2++];
    for (i = 0; i < help.length; i++)
      arr[l + i] = help[i];

    return res;
  }

  // ========== TEST ==========
  public int comparator(int[] arr) {
    if (arr == null || arr.length < 2)
      return 0;

    int res = 0;
    for (int i = 1; i < arr.length; i++) {
      for (int j = 0; j < i; j++) {
        res += arr[j] < arr[i] ? arr[j] : 0;
      }
    }
    return res;
  }

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
    Solution2 solution = new Solution2();
    int testTime = 500000, maxSize = 100, maxValue = 100;
    boolean succeed = true;
    for (int i = 0; i < testTime; i++) {
      int[] arr1 = solution.generateRandomArray(maxSize, maxValue);
      int[] arr2 = solution.copyArray(arr1);
      if (solution.smallSum(arr1) != solution.comparator(arr2)) {
        succeed = false;
        solution.printArray(arr1);
        solution.printArray(arr2);
        break;
      }
    }
    System.out.println(succeed ? "Nice!" : "Fucking fucked!");
  }
}
