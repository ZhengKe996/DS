package monotonous_stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class monotonous_stack {
  /**
   * 单调栈(无法处理重复元素)
   * 
   * @param arr
   * @return
   */
  public static int[][] getNearLessNoRepeat(int[] arr) {
    int[][] res = new int[arr.length][2]; // res[x][0]:左边最近最小元素下标 rex[x][1]:右边最近最小元素下标
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < arr.length; i++) {
      while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
        int j = stack.pop();
        int leftLessIndex = stack.isEmpty() ? -1 : stack.peek(); // 最左边元素 如果栈不为空则为栈顶元素
        res[j][0] = leftLessIndex;
        res[j][1] = i;
      }
      stack.push(i);
    }
    while (!stack.isEmpty()) {
      int j = stack.pop();
      int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
      res[j][0] = leftLessIndex;
      res[j][1] = -1;
    }
    return res;
  }

  /**
   * 单调栈(可以处理重复元素)
   * 
   * @param arr
   * @return
   */
  public static int[][] getNearLess(int[] arr) {
    int[][] res = new int[arr.length][2]; // res[x][0]:左边最近最小元素下标 rex[x][1]:右边最近最小元素下标
    Stack<List<Integer>> stack = new Stack<>();
    for (int i = 0; i < arr.length; i++) {
      while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
        List<Integer> pops = stack.pop();
        int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
        for (Integer pop : pops) {
          res[pop][0] = leftLessIndex;
          res[pop][1] = i;
        }
      }

      if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
        stack.peek().add(Integer.valueOf(i));
      } else {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(i);
        stack.push(list);
      }
    }
    while (!stack.isEmpty()) {
      List<Integer> pops = stack.pop();
      int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
      for (Integer pop : pops) {
        res[pop][0] = leftLessIndex;
        res[pop][1] = -1;
      }
    }
    return res;
  }

  // for test
  public static int[] getRandomArrayNoRepeat(int size) {
    int[] arr = new int[(int) (Math.random() * size) + 1];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = i;
    }
    for (int i = 0; i < arr.length; i++) {
      int swapIndex = (int) (Math.random() * arr.length);
      int tmp = arr[swapIndex];
      arr[swapIndex] = arr[i];
      arr[i] = tmp;
    }
    return arr;
  }

  // for test
  public static int[] getRandomArray(int size, int max) {
    int[] arr = new int[(int) (Math.random() * size) + 1];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
    }
    return arr;
  }

  // for test
  public static int[][] rightWay(int[] arr) {
    int[][] res = new int[arr.length][2];
    for (int i = 0; i < arr.length; i++) {
      int leftLessIndex = -1;
      int rightLessIndex = -1;
      int cur = i - 1;
      while (cur >= 0) {
        if (arr[cur] < arr[i]) {
          leftLessIndex = cur;
          break;
        }
        cur--;
      }
      cur = i + 1;
      while (cur < arr.length) {
        if (arr[cur] < arr[i]) {
          rightLessIndex = cur;
          break;
        }
        cur++;
      }
      res[i][0] = leftLessIndex;
      res[i][1] = rightLessIndex;
    }
    return res;
  }

  // for test
  public static boolean isEqual(int[][] res1, int[][] res2) {
    if (res1.length != res2.length) {
      return false;
    }
    for (int i = 0; i < res1.length; i++) {
      if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
        return false;
      }
    }

    return true;
  }

  // for test
  public static void printArray(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int size = 10;
    int max = 20;
    int testTimes = 2000000;
    System.out.println("测试开始");
    for (int i = 0; i < testTimes; i++) {
      int[] arr1 = getRandomArrayNoRepeat(size);
      int[] arr2 = getRandomArray(size, max);
      if (!isEqual(getNearLessNoRepeat(arr1), rightWay(arr1))) {
        System.out.println("Oops!");
        printArray(arr1);
        break;
      }
      if (!isEqual(getNearLess(arr2), rightWay(arr2))) {
        System.out.println("Oops!");
        printArray(arr2);
        break;
      }
    }
    System.out.println("测试结束");
  }
}
