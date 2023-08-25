package merge_sort;

class Solution4 {
  public int ReversePairs(int[] arr) {
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
    int ans = 0, windowR = mid + 1;
    for (int i = l; i <= mid; i++) {
      while (windowR <= r && (long) arr[i] > (long) arr[windowR] * 2) {
        windowR++;
      }
      ans += windowR - mid - 1;
    }

    int[] help = new int[r - l + 1];
    int i = 0, p1 = l, p2 = mid + 1;
    while (p1 <= mid && p2 <= r) {
      help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
    }
    while (p1 <= mid) {
      help[i++] = arr[p1++];
    }
    while (p2 <= r) {
      help[i++] = arr[p2++];
    }
    for (i = 0; i < help.length; i++) {
      arr[l + i] = help[i];
    }
    return ans;
  }

  // =============== TEST ===============
  public int comparator(int[] arr) {
    int ans = 0;
    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[i] > (arr[j] << 1))
          ans++;
      }
    }
    return ans;
  }

  public static int[] generateRandomArray(int maxSize, int maxValue) {
    int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
    }
    return arr;
  }

  public static int[] copyArray(int[] arr) {
    if (arr == null) {
      return null;
    }
    int[] res = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      res[i] = arr[i];
    }
    return res;
  }

  public static boolean isEqual(int[] arr1, int[] arr2) {
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

  public static void printArray(int[] arr) {
    if (arr == null) {
      return;
    }
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Solution4 solution = new Solution4();
    int testTime = 500000, maxSize = 100, maxValue = 100;
    System.out.println("测试开始");
    for (int i = 0; i < testTime; i++) {
      int[] arr1 = generateRandomArray(maxSize, maxValue);
      int[] arr2 = copyArray(arr1);
      if (solution.ReversePairs(arr1) != solution.comparator(arr2)) {
        System.out.println("Oops!");
        printArray(arr1);
        printArray(arr2);
        break;
      }
    }
    System.out.println("测试结束");
  }
}