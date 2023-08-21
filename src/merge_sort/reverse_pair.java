package merge_sort;

class Solution {
  public int ReverPairNumber(int[] arr) {
    if (arr == null || arr.length < 0)
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
    int i = help.length - 1, p1 = mid, p2 = r, res = 0;
    while (p1 >= l && p2 >= mid) {
      res += arr[p1] > arr[p2] ? (p2 - mid) : 0;
      help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
    }

    while (p1 >= l)
      help[i--] = arr[p1--];
    while (p2 > mid)
      help[i--] = arr[p2--];

    for (i = 0; i < help.length; i++)
      arr[l + i] = help[i];

    return res;
  }

  // ======== TEST ========
  public int comparator(int[] arr) {
    int ans = 0;
    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[i] > arr[j])
          ans++;
      }
    }
    return ans;
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
    Solution solution = new Solution();
    int testTime = 500000, maxSize = 100, maxValue = 100;
    System.out.println("Begin");
    for (int i = 0; i < testTime; i++) {
      int[] arr1 = solution.generateRandomArray(maxSize, maxValue);
      int[] arr2 = solution.copyArray(arr1);
      if (solution.ReverPairNumber(arr1) != solution.comparator(arr2)) {
        System.out.println("Oops!");
        solution.printArray(arr1);
        solution.printArray(arr2);
        break;
      }
    }
    System.out.println("Finish!");
  }
}
