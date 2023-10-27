package dp;

public class convert_to_letter_string {
  public static int number(String str) {
    if (str == null || str.length() == 0)
      return 0;

    return process(str.toCharArray(), 0);
  }

  private static int process(char[] str, int i) {
    if (i == str.length)
      return 1;
    if (str[i] == '0')
      return 0;

    int ways = process(str, i + 1);
    if (i + 1 < str.length && (str[i] - '0') * 10 + (str[i + 1] - '0') < 27) {
      ways += process(str, i + 2);
    }
    return ways;
  }

  public static int dp(String s) {
    if (s == null || s.length() == 0)
      return 0;
    char[] str = s.toCharArray();
    int N = str.length;
    int[] dp = new int[N + 1];
    dp[N] = 1;
    for (int i = N - 1; i >= 0; i--) {
      if (str[i] != '0') {
        int ways = dp[i + 1];
        if (i + 1 < str.length && (str[i] - '0') * 10 + (str[i + 1] - '0') < 27) {
          ways += dp[i + 2];
        }
        dp[i] = ways;
      }
    }
    return dp[0];
  }

  // 为了测试
  public static String randomString(int len) {
    char[] str = new char[len];
    for (int i = 0; i < len; i++) {
      str[i] = (char) ((int) (Math.random() * 10) + '0');
    }
    return String.valueOf(str);
  }

  public static void main(String[] args) {
    int N = 30;
    int testTime = 1000000;
    System.out.println("测试开始");
    for (int i = 0; i < testTime; i++) {
      int len = (int) (Math.random() * N);
      String s = randomString(len);
      int ans0 = number(s);
      int ans1 = dp(s);
      // int ans2 = dp2(s);
      if (ans0 != ans1) {
        System.out.println(s);
        System.out.println(ans0);
        System.out.println(ans1);
        // System.out.println(ans2);
        System.out.println("Oops!");
        break;
      }
    }
    System.out.println("测试结束");
  }
}
