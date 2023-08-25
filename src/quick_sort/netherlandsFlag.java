package quick_sort;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Solution2 {
  public int[] netherlandsFlag(int[] arr, int l, int r) {
    if (l > r)
      return new int[] { -1, -1 };
    if (l == r)
      return new int[] { l, r };

    int less = l - 1, more = r, index = l;
    while (index < more) {
      if (arr[index] == arr[r])
        index++;
      else if (arr[index] < arr[r])
        swap(arr, index++, ++less);
      else
        swap(arr, index, --more);
    }
    swap(arr, more, r);
    return new int[] { less + 1, more };
  }

  public void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public void quickSort(int[] arr) {
    if (arr == null || arr.length < 2)
      return;
    process(arr, 0, arr.length - 1);
  }

  public void process(int[] arr, int l, int r) {
    if (l >= r)
      return;
    swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
    int[] equalArea = netherlandsFlag(arr, l, r);
    process(arr, l, equalArea[0] - 1);
    process(arr, equalArea[1] + 1, r);
  }

  // 快排非递归 使用辅助类
  public class Op {
    public int l, r;

    public Op(int l, int r) {
      this.l = l;
      this.r = r;
    }
  }

  /**
   * 快排非递归 栈实现
   * 
   * @param arr
   */
  public void quickSort2(int[] arr) {
    if (arr == null || arr.length < 2)
      return;
    int n = arr.length;
    swap(arr, (int) (Math.random() * n), n - 1);
    int[] equalArea = netherlandsFlag(arr, 0, n - 1);
    int el = equalArea[0], er = equalArea[1];
    Stack<Op> stack = new Stack<>();
    stack.push(new Op(0, el - 1));
    stack.push(new Op(er + 1, n - 1));
    while (!stack.isEmpty()) {
      Op op = stack.pop();
      if (op.l < op.r) {
        swap(arr, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
        equalArea = netherlandsFlag(arr, op.l, op.r);
        el = equalArea[0];
        er = equalArea[1];
        stack.push(new Op(op.l, el - 1));
        stack.push(new Op(er + 1, op.r));

      }
    }
  }

  /**
   * 快排非递归（队列）
   * 
   * @param arr
   */
  public void quickSort3(int[] arr) {
    if (arr == null || arr.length < 2)
      return;
    int n = arr.length;
    swap(arr, (int) (Math.random() * n), n - 1);

    int[] equalArea = netherlandsFlag(arr, 0, n - 1);
    int el = equalArea[0], er = equalArea[1];
    Queue<Op> queue = new LinkedList<>();
    queue.offer(new Op(0, el - 1));
    queue.offer(new Op(er + 1, n - 1));
    while (!queue.isEmpty()) {
      Op op = queue.poll();
      if (op.l < op.r) {
        swap(arr, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
        equalArea = netherlandsFlag(arr, op.l, op.r);
        el = equalArea[0];
        er = equalArea[1];
        queue.offer(new Op(op.l, el - 1));
        queue.offer(new Op(er + 1, op.r));

      }
    }
  }

  // ============= TEST =============
  // 生成随机数组（用于测试）
  public int[] generateRandomArray(int maxSize, int maxValue) {
    int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
    }
    return arr;
  }

  // 拷贝数组（用于测试）
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

  // 对比两个数组（用于测试）
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

  // 打印数组（用于测试）
  public void printArray(int[] arr) {
    if (arr == null) {
      return;
    }
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  // 跑大样本随机测试（对数器）
  public static void main(String[] args) {
    Solution2 solution2 = new Solution2();
    int testTime = 500000;
    int maxSize = 100;
    int maxValue = 100;
    boolean succeed = true;
    System.out.println("test begin");
    for (int i = 0; i < testTime; i++) {
      int[] arr1 = solution2.generateRandomArray(maxSize, maxValue);
      int[] arr2 = solution2.copyArray(arr1);
      int[] arr3 = solution2.copyArray(arr1);
      solution2.quickSort(arr1);
      solution2.quickSort2(arr2);
      solution2.quickSort3(arr3);
      if (!solution2.isEqual(arr1, arr2) || !solution2.isEqual(arr1, arr3)) {
        succeed = false;
        break;
      }
    }
    System.out.println("test end");
    System.out.println("测试" + testTime + "组是否全部通过：" + (succeed ? "是" : "否"));
  }
}
