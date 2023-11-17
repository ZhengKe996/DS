package sliding_window;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

public class min_coins_one_paper {
  /**
   * 暴力递归
   * 
   * @param arr
   * @param aim
   * @return
   */
  public static int minCoins(int[] arr, int aim) {
    return process(arr, 0, aim);
  }

  private static int process(int[] arr, int index, int rest) {
    if (rest < 0)
      return Integer.MAX_VALUE;
    if (index == arr.length)
      return rest == 0 ? 0 : Integer.MAX_VALUE;

    int p1 = process(arr, index + 1, rest);
    int p2 = process(arr, index + 1, rest - arr[index]);

    if (p2 != Integer.MAX_VALUE)
      p2++;
    return Math.min(p1, p2);
  }

  /**
   * 记忆化搜索
   * 
   * @param arr
   * @param aim
   * @return
   */
  public static int dp(int[] arr, int aim) {
    if (aim == 0)
      return 0;
    int N = arr.length;
    int[][] dp = new int[N + 1][aim + 1];
    dp[N][0] = 0;
    for (int j = 1; j < aim + 1; j++) {
      dp[N][j] = Integer.MAX_VALUE;
    }
    for (int index = N - 1; index >= 0; index--) {
      for (int rest = 0; rest <= aim; rest++) {
        int p1 = dp[index + 1][rest];
        int p2 = rest - arr[index] >= 0 ? dp[index + 1][rest - arr[index]] : Integer.MAX_VALUE;
        if (p2 != Integer.MAX_VALUE)
          p2++;
        dp[index][rest] = Math.min(p1, p2);
      }
    }
    return dp[0][aim];
  }

  public static class Info {
    public int[] coins;
    public int[] zhangs;

    public Info(int[] c, int[] z) {
      this.coins = c;
      this.zhangs = z;
    }
  }

  public static Info getInfo(int[] arr) {
    HashMap<Integer, Integer> counts = new HashMap<>();
    for (int value : arr) {
      if (!counts.containsKey(value))
        counts.put(value, 1);
      else
        counts.put(value, counts.get(value) + 1);
    }

    int N = counts.size();
    int[] coins = new int[N];
    int[] zhangs = new int[N];
    int index = 0;
    for (Entry<Integer, Integer> entry : counts.entrySet()) {
      coins[index] = entry.getKey();
      zhangs[index++] = entry.getValue();
    }
    return new Info(coins, zhangs);
  }

  /**
   * 斜率优化的DP
   * 
   * @param arr
   * @param aim
   * @return
   */
  public static int dp2(int[] arr, int aim) {
    if (aim == 0)
      return 0;

    Info info = getInfo(arr);
    int[] coins = info.coins;
    int[] zhangs = info.zhangs;

    int N = coins.length;
    int[][] dp = new int[N + 1][aim + 1];
    dp[N][0] = 0;
    for (int j = 1; j < aim + 1; j++) {
      dp[N][j] = Integer.MAX_VALUE;
    }

    for (int index = N - 1; index >= 0; index--) {
      for (int rest = 0; rest <= aim; rest++) {
        dp[index][rest] = dp[index + 1][rest];
        for (int zhang = 1; zhang * coins[index] <= aim && zhang <= zhangs[index]; zhang++) {
          if (rest - zhang * coins[index] >= 0 && dp[index + 1][rest - zhang * coins[index]] != Integer.MAX_VALUE) {
            dp[index][rest] = Math.min(dp[index][rest], zhang + dp[index + 1][rest - zhang * coins[index]]);
          }
        }
      }
    }
    return dp[0][aim];
  }

  /**
   * DP优化（需要使用滑动窗口的最小值更新结构）
   * 
   * @param arr
   * @param aim
   * @return
   */
  public static int dp3(int[] arr, int aim) {
    if (aim == 0)
      return 0;

    Info info = getInfo(arr);
    int[] coins = info.coins;
    int[] zhangs = info.zhangs;

    int N = coins.length;
    int[][] dp = new int[N + 1][aim + 1];
    dp[N][0] = 0;
    for (int j = 1; j < aim + 1; j++) {
      dp[N][j] = Integer.MAX_VALUE;
    }

    for (int index = N - 1; index >= 0; index--) {
      for (int mod = 0; mod < Math.min(aim + 1, coins[index]); mod++) {
        LinkedList<Integer> window = new LinkedList<>();
        window.add(mod);
        dp[index][mod] = dp[index + 1][mod];
        for (int R = mod + coins[index]; R <= aim; R += coins[index]) {
          while (!window.isEmpty() && (dp[index + 1][window.peekLast()] == Integer.MAX_VALUE
              || dp[index + 1][window.peekLast()]
                  + compensate(window.peekLast(), R, coins[index]) >= dp[index + 1][R])) {
            window.pollLast();
          }

          window.addLast(R);
          int overdue = R - coins[index] * (zhangs[index] + 1);
          if (window.peekFirst() == overdue)
            window.pollFirst();

          if (dp[index + 1][window.peekFirst()] == Integer.MAX_VALUE) {
            dp[index][R] = Integer.MAX_VALUE;
          } else {
            dp[index][R] = dp[index + 1][window.peekFirst()] + compensate(window.peekFirst(), R, coins[index]);
          }
        }
      }
    }
    return dp[0][aim];
  }

  public static int compensate(int pre, int cur, int coin) {
    return (cur - pre) / coin;
  }

  // 为了测试
  public static int[] randomArray(int N, int maxValue) {
    int[] arr = new int[N];
    for (int i = 0; i < N; i++) {
      arr[i] = (int) (Math.random() * maxValue) + 1;
    }
    return arr;
  }

  // 为了测试
  public static void printArray(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  // 为了测试
  public static void main(String[] args) {
    int maxLen = 20;
    int maxValue = 30;
    int testTime = 300000;
    System.out.println("功能测试开始");
    for (int i = 0; i < testTime; i++) {
      int N = (int) (Math.random() * maxLen);
      int[] arr = randomArray(N, maxValue);
      int aim = (int) (Math.random() * maxValue);
      int ans1 = minCoins(arr, aim);
      int ans2 = dp(arr, aim);
      int ans3 = dp2(arr, aim);
      int ans4 = dp3(arr, aim);

      if (ans1 != ans2 || ans3 != ans4 || ans1 != ans3) {
        System.out.println("Oops!");
        printArray(arr);
        System.out.println(aim);
        System.out.println(ans1);
        System.out.println(ans2);
        System.out.println(ans3);
        System.out.println(ans4);
        break;
      }
    }
    System.out.println("功能测试结束");

    System.out.println("==========");

    int aim = 0;
    int[] arr = null;
    long start;
    long end;
    int ans2;
    int ans3;

    System.out.println("性能测试开始");
    maxLen = 30000;
    maxValue = 20;
    aim = 60000;
    arr = randomArray(maxLen, maxValue);

    start = System.currentTimeMillis();
    ans2 = dp2(arr, aim);
    end = System.currentTimeMillis();
    System.out.println("dp2答案 : " + ans2 + ", dp2运行时间 : " + (end - start) + "ms");

    start = System.currentTimeMillis();
    ans3 = dp3(arr, aim);
    end = System.currentTimeMillis();
    System.out.println("dp3答案 : " + ans3 + ", dp3运行时间 : " + (end - start) + "ms");
    System.out.println("性能测试结束");

    System.out.println("===========");

    System.out.println("货币大量重复出现情况下，");
    System.out.println("大数据量测试dp3开始");
    maxLen = 20000000;
    aim = 10000;
    maxValue = 10000;
    arr = randomArray(maxLen, maxValue);
    start = System.currentTimeMillis();
    ans3 = dp3(arr, aim);
    end = System.currentTimeMillis();
    System.out.println("dp3运行时间 : " + (end - start) + " ms");
    System.out.println("大数据量测试dp3结束");

    System.out.println("===========");

    System.out.println("当货币很少出现重复，dp2比dp3有常数时间优势");
    System.out.println("当货币大量出现重复，dp3时间复杂度明显优于dp2");
    System.out.println("dp3的优化用到了窗口内最小值的更新结构");
  }

}