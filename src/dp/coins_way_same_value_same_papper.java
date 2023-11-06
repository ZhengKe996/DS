package dp;

import java.util.HashMap;
import java.util.Map.Entry;

public class coins_way_same_value_same_papper {

  public static class Info {
    public int[] coins;
    public int[] zhangs;

    public Info(int[] c, int[] z) {
      coins = c;
      zhangs = z;
    }
  }

  /**
   * 类似于词频统计
   * 
   * @param arr
   * @return
   */
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
   * 暴力递归 解题
   * 
   * @param arr
   * @param aim
   * @return
   */
  public static int coinsWay(int[] arr, int aim) {
    if (arr == null || arr.length == 0 || aim < 0) {
      return 0;
    }
    Info info = getInfo(arr);
    return process(info.coins, info.zhangs, 0, aim);
  }

  /**
   * 暴力递归的子过程
   * 
   * @param coins  面值数组，全为整数且去重
   * @param zhangs 每种面值对应的张数
   * @param rest   剩余金额
   * @return
   */
  private static int process(int[] coins, int[] zhangs, int index, int rest) {
    if (index == coins.length)
      return rest == 0 ? 1 : 0;

    int ways = 0;
    for (int zhang = 0; zhang * coins[index] <= rest && zhang <= zhangs[index]; zhang++) {
      ways += process(coins, zhangs, index + 1, rest - (zhang * coins[index]));
    }
    return ways;
  }

  /**
   * 动态规划（有很大的优化空间）：三重for循环 还可以根据边界条件优化
   * 
   * @param arr
   * @param aim
   * @return
   */
  public static int dp1(int[] arr, int aim) {
    if (arr == null || arr.length == 0 || aim < 0) {
      return 0;
    }
    Info info = getInfo(arr);
    int[] coins = info.coins;
    int[] zhangs = info.zhangs;
    int N = coins.length;
    int[][] dp = new int[N + 1][aim + 1];
    dp[N][0] = 1;
    for (int index = N - 1; index >= 0; index--) {
      for (int rest = 0; rest <= aim; rest++) {
        int ways = 0;
        for (int zhang = 0; zhang * coins[index] <= rest && zhang <= zhangs[index]; zhang++) {
          ways += dp[index + 1][rest - (zhang * coins[index])];
        }
        dp[index][rest] = ways;
      }
    }
    return dp[0][aim];
  }

  /**
   * 优化后的动态规划（少了一层循环）
   * 
   * @param arr
   * @param aim
   * @return
   */
  public static int dp2(int[] arr, int aim) {
    if (arr == null || arr.length == 0 || aim < 0) {
      return 0;
    }
    Info info = getInfo(arr);
    int[] coins = info.coins;
    int[] zhangs = info.zhangs;
    int N = coins.length;
    int[][] dp = new int[N + 1][aim + 1];
    dp[N][0] = 1;
    for (int index = N - 1; index >= 0; index--) {
      for (int rest = 0; rest <= aim; rest++) {
        dp[index][rest] = dp[index + 1][rest];
        if (rest - coins[index] >= 0) {
          dp[index][rest] += dp[index][rest - coins[index]];
        }
        if (rest - coins[index] * (zhangs[index] + 1) >= 0) {
          dp[index][rest] -= dp[index + 1][rest - coins[index] * (zhangs[index] + 1)];
        }
      }
    }
    return dp[0][aim];
  }

}