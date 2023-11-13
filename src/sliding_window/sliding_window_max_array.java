package sliding_window;

import java.util.LinkedList;

public class sliding_window_max_array {

  /**
   * 对数器
   * 
   * @param arr
   * @param w
   * @return
   */
  public static int[] right(int[] arr, int w) {
    if (arr == null || w < 1 || arr.length < w) {
      return null;
    }
    int N = arr.length;
    int[] res = new int[N - w + 1];
    int index = 0;
    int L = 0;
    int R = w - 1;
    while (R < N) {
      int max = arr[L];
      for (int i = L + 1; i <= R; i++) {
        max = Math.max(max, arr[i]);

      }
      res[index++] = max;
      L++;
      R++;
    }
    return res;
  }

  public static int[] getMaxWindow(int[] arr, int w) {
    if (arr == null || w < 1 || arr.length < w)
      return null;
    LinkedList<Integer> qmax = new LinkedList<>();
    int[] res = new int[arr.length - w + 1];
    int index = 0;
    for (int R = 0; R < arr.length; R++) {
      // 如果队列不为空并且队尾元素小于当前元素，则弹出队尾元素
      while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R])
        qmax.pollLast();

      qmax.addLast(R); // 将当前位置的下表添加到队列中
      if (qmax.peekFirst() == R - w)
        qmax.pollFirst();

      if (R >= w - 1)
        res[index++] = arr[qmax.peekFirst()]; // 形成窗口的情况下，将队头的值(Max)放入结果数组
    }
    return res;
  }

  public static int[] generateRandomArray(int maxSize, int maxValue) {
    int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) (Math.random() * (maxValue + 1));
    }
    return arr;
  }

  // for test
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

  public static void main(String[] args) {
    int testTime = 100000;
    int maxSize = 100;
    int maxValue = 100;
    System.out.println("test begin");
    for (int i = 0; i < testTime; i++) {
      int[] arr = generateRandomArray(maxSize, maxValue);
      int w = (int) (Math.random() * (arr.length + 1));
      int[] ans1 = getMaxWindow(arr, w);
      int[] ans2 = right(arr, w);
      if (!isEqual(ans1, ans2)) {
        System.out.println("Oops!");
      }
    }
    System.out.println("test finish");
  }
}