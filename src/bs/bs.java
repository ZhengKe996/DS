package bs;

import java.util.Arrays;

class Solution {
  /**
   * 二分存在
   * 
   * @param arr
   * @param num
   * @return
   */
  public boolean BSExist(int[] arr, int num) {
    if (arr == null || arr.length == 0)
      return false;
    int l = 0, r = arr.length - 1;

    while (l < r) {
      int mid = l + ((r - l) >> 1);

      if (arr[mid] == num)
        return true;
      else if (arr[mid] > num)
        r = mid - 1;
      else
        l = mid + 1;
    }
    return arr[l] == num;
  }

  /**
   * 二分查找
   * 
   * @param arr
   * @param num
   * @return
   */
  public int BSSearch(int[] arr, int num) {
    // if (arr == null || arr.length == 0)
    // return -1;
    int l = 0, r = arr.length - 1;
    while (l <= r) {
      int mid = l + ((r - l) >> 1);
      if (arr[mid] == num)
        return mid;
      else if (arr[mid] < num)
        l = mid + 1;
      else
        r = mid - 1;
    }
    return -1;
  }

  /**
   * 在arr上，找满足>=value的最左位置
   * 
   * @param arr
   * @param value
   * @return
   */
  public int BSNearLeft(int[] arr, int value) {
    int l = 0, r = arr.length - 1;
    int index = -1;
    while (l <= r) {
      int mid = l + ((r - l) >> 1);
      if (arr[mid] >= value) {
        index = mid;
        r = mid - 1;
      } else {
        l = mid + 1;
      }
    }
    return index;
  }

  /**
   * 在arr上，找满足<=value的最右位置
   * 
   * @param arr
   * @param value
   * @return
   */
  public int BSNearRight(int[] arr, int value) {
    int l = 0, r = arr.length - 1, index = -1;

    while (l <= r) {
      int mid = l + ((r - l) >> 1);
      if (arr[mid] <= value) {
        index = mid;
        l = mid + 1;
      } else {
        r = mid - 1;
      }
    }
    return index;
  }

  /**
   * 找局部最小（非有序）
   * 
   * @param arr
   * @return
   */
  public int getLessIndex(int[] arr) {
    if (arr == null || arr.length == 0) {
      return -1;
    }
    if (arr.length == 1 || arr[0] < arr[1]) {
      return 0;
    }
    if (arr[arr.length - 1] < arr[arr.length - 2]) {
      return arr.length - 1;
    }

    int l = 1, r = arr.length - 2;
    while (l < r) {
      int mid = l + ((r - l) >> 1);
      if (arr[mid] > arr[mid - 1]) {
        r = mid - 1;
      } else if (arr[mid] > arr[mid + 1]) {
        l = mid + 1;
      } else {
        return mid;
      }
    }
    return l;
  }

  /**
   * 二分存在 测试
   * 
   * @param arr
   * @param num
   * @return
   */
  public boolean TestBSExist(int[] arr, int num) {
    for (int cur : arr) {
      if (cur == num)
        return true;
    }
    return false;
  }

  /**
   * 随机生成数组
   * 
   * @param maxSize
   * @param maxValue
   * @return
   */
  public int[] GenerateRandomArray(int maxSize, int maxValue) {
    int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
    }
    return arr;
  }

  /**
   * 二分查找(TEST)
   * 
   * @param arr
   * @param num
   * @return
   */
  public int TestBSSearch(int[] arr, int num) {
    if (arr == null || arr.length == 0)
      return -1;

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == num)
        return i;
    }
    return -1;
  }

  /**
   * 在arr上，找满足>=value的最左位置(TEST)
   * 
   * @param arr
   * @param value
   * @return
   */
  public int TestBSNearLeft(int[] arr, int value) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] >= value) {
        return i;
      }
    }
    return -1;
  }

  /**
   * 在arr上，找满足<=value的最右位置(TEST)
   * 
   * @param arr
   * @param value
   * @return
   */
  public int TestBSNearRight(int[] arr, int value) {
    for (int i = arr.length - 1; i >= 0; i--) {
      if (arr[i] <= value) {
        return i;
      }
    }
    return -1;
  }

  /**
   * 二分答案（TEST） 验证得到的结果，是不是局部最小
   * 
   * @param arr
   * @param index
   * @return
   */
  public static boolean isRight(int[] arr, int index) {
    if (arr.length <= 1) {
      return true;
    }
    if (index == 0) {
      return arr[index] < arr[index + 1];
    }
    if (index == arr.length - 1) {
      return arr[index] < arr[index - 1];
    }
    return arr[index] < arr[index - 1] && arr[index] < arr[index + 1];
  }

  /**
   * 生成相邻不相等的数组
   * 
   * @param maxSize
   * @param maxValue
   * @return
   */
  public int[] GenerateRandomArray2(int maxSize, int maxValue) {
    int[] arr = new int[(int) (Math.random() * maxSize) + 1];
    arr[0] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
    for (int i = 1; i < arr.length; i++) {
      do {
        arr[i] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
      } while (arr[i] == arr[i - 1]);
    }
    return arr;
  }

  public void PrintArray(int[] arr) {
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
    int maxSize = 10;
    int maxValue = 100;
    boolean succeed = true;
    for (int i = 0; i < testTime; i++) {
      int[] arr = solution.GenerateRandomArray(maxSize, maxValue);
      Arrays.sort(arr);
      int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());

      if (solution.TestBSExist(arr, value) != solution.BSExist(arr, value)) {
        System.out.println("BSExist");
        solution.PrintArray(arr);
        System.out.println(value);
        System.out.println(solution.TestBSExist(arr, value));
        System.out.println(solution.BSExist(arr, value));
        succeed = false;
        break;
      }

      if (solution.TestBSNearLeft(arr, value) != solution.BSNearLeft(arr, value)) {
        System.out.println("BSNearLeft");
        solution.PrintArray(arr);
        System.out.println(value);
        System.out.println(solution.TestBSNearLeft(arr, value));
        System.out.println(solution.BSNearLeft(arr, value));
        succeed = false;
        break;
      }

      if (solution.TestBSNearRight(arr, value) != solution.BSNearRight(arr, value)) {
        System.out.println("BSNearRight");
        solution.PrintArray(arr);
        System.out.println(value);
        System.out.println(solution.TestBSNearRight(arr, value));
        System.out.println(solution.BSNearRight(arr, value));
        succeed = false;
        break;
      }

      int[] arr2 = solution.GenerateRandomArray2(maxSize, maxValue);
      int ans = solution.getLessIndex(arr2);
      if (!isRight(arr2, ans)) {
        System.out.println("getLessIndex 出错了！");
        break;
      }

    }
    System.out.println(succeed ? "Nice!" : "Fucking fucked!");
  }
}
