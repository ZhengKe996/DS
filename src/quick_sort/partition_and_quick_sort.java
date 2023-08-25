package quick_sort;

class Solution {
  public void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public int partition(int[] arr, int l, int r) {
    if (l > r)
      return -1;
    if (l == r)
      return l;
    int lessEqual = l - 1, index = l;
    while (index < r) {
      if (arr[index] <= arr[r])
        swap(arr, index, ++lessEqual);

      index++;
    }
    swap(arr, ++lessEqual, r);
    return lessEqual;
  }

  public int[] netherlandsFlag(int[] arr, int L, int R) {
    if (L > R) { // L...R L>R
      return new int[] { -1, -1 };
    }
    if (L == R) {
      return new int[] { L, R };
    }
    int less = L - 1; // < 区 右边界
    int more = R; // > 区 左边界
    int index = L;
    while (index < more) { // 当前位置，不能和 >区的左边界撞上
      if (arr[index] == arr[R]) {
        index++;
      } else if (arr[index] < arr[R]) {
        swap(arr, index++, ++less);
      } else { // >
        swap(arr, index, --more);
      }
    }
    swap(arr, more, R); // <[R] =[R] >[R]
    return new int[] { less + 1, more };
  }

  public void QuickSort1(int[] arr) {
    if (arr == null || arr.length < 2)
      return;
    process1(arr, 0, arr.length - 1);
  }

  private void process1(int[] arr, int l, int r) {
    if (l >= r)
      return;
    int mid = partition(arr, l, r);
    process1(arr, l, mid - 1);
    process1(arr, mid + 1, r);
  }

  public void QuickSort2(int[] arr) {
    if (arr == null || arr.length < 2)
      return;
    process2(arr, 0, arr.length - 1);
  }

  // arr[L...R]排有序，快排 划分区间
  private void process2(int[] arr, int l, int r) {
    if (l >= r)
      return;
    int[] equalArea = netherlandsFlag(arr, l, r);
    process2(arr, l, equalArea[0] - 1);
    process2(arr, equalArea[1] + 1, r);
  }

  public void QuickSort3(int[] arr) {
    if (arr == null || arr.length < 2)
      return;
    process3(arr, 0, arr.length - 1);
  }

  private void process3(int[] arr, int l, int r) {
    if (l >= r)
      return;
    swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
    int[] equalArea = netherlandsFlag(arr, l, r);
    process3(arr, l, equalArea[0] - 1);
    process3(arr, equalArea[1] + 1, r);
  }

  // =============== TEST ===============
  public static int[] generateRandomArray(int maxSize, int maxValue) {
    int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
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
    Solution solution = new Solution();
    int testTime = 500000, maxSize = 100, maxValue = 100;
    boolean succeed = true;
    for (int i = 0; i < testTime; i++) {
      int[] arr1 = generateRandomArray(maxSize, maxValue);
      int[] arr2 = copyArray(arr1);
      int[] arr3 = copyArray(arr1);
      solution.QuickSort1(arr1);
      solution.QuickSort2(arr2);
      solution.QuickSort3(arr3);
      if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
        succeed = false;
        break;
      }
    }
    System.out.println(succeed ? "Nice!" : "Oops!");

  }
}
