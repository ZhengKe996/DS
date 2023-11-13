package dp;

public class n_queen {
  public static int num1(int n) {
    if (n < 1)
      return 0;

    int[] record = new int[n];
    return process1(0, record, n);
  }

  private static int process1(int i, int[] record, int n) {
    if (i == n)
      return 1;

    int res = 0;
    // i行的皇后，放哪一列呢？j列，
    for (int j = 0; j < n; j++) {
      if (isValid(record, i, j)) {
        record[i] = j;
        res += process1(i + 1, record, n);
      }
    }
    return res;
  }

  private static boolean isValid(int[] record, int i, int j) {
    // 0..i-1
    for (int k = 0; k < i; k++) {
      if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k))
        return false;
    }
    return true;
  }

  /**
   * 不超过32的n皇后
   * 
   * @param n
   * @return
   */
  public static int num2(int n) {
    if (n < 1 || n > 32)
      return 0;

    // 如果你是13皇后问题，limit 最右13个1，其他都是0
    int limit = n == 32 ? -1 : (1 << n) - 1;
    // 1左移 n位 0001 n=4 -> 10000-1 -> 01111;
    return process2(limit, 0, 0, 0);
  }

  // 7皇后问题
  // limit : 0....0 1 1 1 1 1 1 1
  // 之前皇后的列影响：colLim
  // 之前皇后的左下对角线影响：leftDiaLim
  // 之前皇后的右下对角线影响：rightDiaLim
  public static int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
    if (colLim == limit) {
      return 1;
    }
    // pos中所有是1的位置，是你可以去尝试皇后的位置
    int pos = limit & (~(colLim | leftDiaLim | rightDiaLim)); // 或完后，整体取反，并与limit做与运算
    int mostRightOne = 0;
    int res = 0;
    while (pos != 0) { // 尝试所有可能的1，发方法数累加
      mostRightOne = pos & (~pos + 1);// 提取出最右侧的1
      pos = pos - mostRightOne; // pos - 最右侧的1，process2 再去尝试下一个最右侧的1
      res += process2(limit,
          colLim | mostRightOne, // 列限制或上1 调整列限制
          (leftDiaLim | mostRightOne) << 1, // 左对角线或完后，整体左移动，调整左对角线的限制(左移 补0)
          (rightDiaLim | mostRightOne) >>> 1 // 右对角线或完后，整体右移，调整右对角线的限制(右移>> 是拿符号位补，>>> 是用0来补)
      ); // 后续再去求方法数
    }
    return res;
  }

  public static void main(String[] args) {
    int n = 14;

    long start = System.currentTimeMillis();
    System.out.println(num2(n));
    long end = System.currentTimeMillis();
    System.out.println("cost time: " + (end - start) + "ms");

    start = System.currentTimeMillis();
    System.out.println(num1(n));
    end = System.currentTimeMillis();
    System.out.println("cost time: " + (end - start) + "ms");

  }
}
