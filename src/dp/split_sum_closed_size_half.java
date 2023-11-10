package dp;

public class split_sum_closed_size_half {

  public static int right(int[] arr) {
    if (arr == null || arr.length < 2)
      return 0;

    int sum = 0;
    for (int num : arr)
      sum += num;

    if ((arr.length & 1) == 0) {
      // 偶数情况下 只有一条路
      return process(arr, 0, arr.length / 2, sum / 2);
    } else {
      // 奇数有两条路
      return Math.max(process(arr, 0, arr.length / 2, sum / 2), process(arr, 0, arr.length / 2 + 1, sum / 2));
    }
  }

  private static int process(int[] arr, int index, int picks, int rest) {
    if (index == arr.length)
      return picks == 0 ? 0 : -1;

    int p1 = process(arr, index + 1, picks, rest); // 当前位置不选的情况
    int p2 = -1;
    int next = -1;

    if (arr[index] <= rest)
      next = process(arr, index + 1, picks - 1, rest - arr[index]); // 当前位置选的情况

    if (next != -1)
      p2 = arr[index] + next;

    return Math.max(p2, p1);
  }

  public static int dp(int[] arr) {
    if (arr == null || arr.length < 2)
      return 0;

    int sum = 0;
    for (int num : arr)
      sum += num;

    sum = sum >> 1;
    int N = arr.length;
    int M = (N + 1) / 2; // 向上取整

    int[][][] dp = new int[N + 1][M + 1][sum + 1];

    // 初始情况下 全部为 -1
    for (int i = 0; i <= N; i++) {
      for (int j = 0; j <= M; j++) {
        for (int k = 0; k <= sum; k++) {
          dp[i][j][k] = -1;
        }
      }
    }

    // 边界情况
    for (int rest = 0; rest <= sum; rest++)
      dp[N][0][rest] = 0;

    for (int index = N - 1; index >= 0; index--) {
      for (int picks = 0; picks <= M; picks++) {
        for (int rest = 0; rest <= sum; rest++) {

          int p1 = dp[index + 1][picks][rest]; // 当前位置不选的情况
          int p2 = -1;
          int next = -1;

          if (picks - 1 >= 0 && arr[index] <= rest)
            next = dp[index + 1][picks - 1][rest - arr[index]]; // 当前位置选的情况

          if (next != -1)
            p2 = arr[index] + next;
          dp[index][picks][rest] = Math.max(p2, p1);
        }
      }
    }

    if ((arr.length & 1) == 0) {
      // 偶数情况下 只有一条路
      return dp[0][arr.length / 2][sum];
    } else {
      // 奇数有两条路
      return Math.max(dp[0][arr.length / 2][sum], dp[0][arr.length / 2 + 1][sum]);
    }
  }

  // for test
  public static int[] randomArray(int len, int value) {
    int[] arr = new int[len];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) (Math.random() * value);
    }
    return arr;
  }

  // for test
  public static void printArray(int[] arr) {
    for (int num : arr) {
      System.out.print(num + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int maxLen = 10;
    int maxValue = 50;
    int testTime = 10000;
    System.out.println("测试开始");
    for (int i = 0; i < testTime; i++) {
      int len = (int) (Math.random() * maxLen);
      int[] arr = randomArray(len, maxValue);
      int ans1 = right(arr);
      int ans2 = dp(arr);
      if (ans1 != ans2) {
        printArray(arr);
        System.out.println(ans1);
        System.out.println(ans2);
        System.out.println("Oops!");
        break;
      }
    }
    System.out.println("测试结束");
  }
}