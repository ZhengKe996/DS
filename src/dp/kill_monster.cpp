#include <math.h>

#include <iostream>
using namespace std;

class Solution {
 private:
  long process(int times, int M, int hp) {
    if (times == 0) return hp <= 0 ? 1 : 0;
    if (hp <= 0) return (long)pow(M + 1, times);

    long ways = 0;
    for (int i = 0; i <= M; i++) ways += process(times - 1, M, hp - i);
    return ways;
  };

 public:
  double kill(int N, int M, int K) {
    if (N < 1 || M < 1 || K < 1) return 0;
    long all = (long)pow(M + 1, K);
    long kill = process(K, M, N);
    return (double)((double)kill) / ((double)all);
  };

  double dp1(int N, int M, int K) {
    if (N < 1 || M < 1 || K < 1) return 0;
    long all = (long)pow(M + 1, K);

    vector<vector<long>> dp(K + 1, vector<long>(N + 1, 0));
    dp[0][0] = 1;
    for (int times = 1; times <= K; times++) {
      dp[times][0] = (long)pow(M + 1, times);
      for (int hp = 1; hp <= N; hp++) {
        long ways = 0;
        for (int i = 0; i <= M; i++) {
          if (hp - i >= 0)
            ways += dp[times - 1][hp - i];
          else
            ways += (long)pow(M + 1, times - 1);
        }
        dp[times][hp] = ways;
      }
    }
    long kill = dp[K][N];
    return (double)((double)kill) / ((double)all);
  }

  double dp2(int N, int M, int K) {
    if (N < 1 || M < 1 || K < 1) return 0;
    long all = (long)pow(M + 1, K);

    vector<vector<long>> dp(K + 1, vector<long>(N + 1, 0));
    dp[0][0] = 1;
    for (int times = 1; times <= K; times++) {
      dp[times][0] = (long)pow(M + 1, times);
      for (int hp = 1; hp <= N; hp++) {
        dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];
        if (hp - 1 - M >= 0)
          dp[times][hp] -= dp[times - 1][hp - 1 - M];
        else
          dp[times][hp] -= pow(M + 1, times - 1);
      }
    }
    long kill = dp[K][N];
    return (double)((double)kill) / ((double)all);
  }
};

int main() {
  Solution slu;
  int NMax = 10;
  int MMax = 10;
  int KMax = 10;
  int testTime = 200;

  cout << "测试开始" << endl;
  for (int i = 0; i < testTime; i++) {
    int N = (int)(rand() % NMax);
    int M = (int)(rand() % MMax);
    int K = (int)(rand() % KMax);
    double ans1 = slu.kill(N, M, K);
    double ans2 = slu.dp1(N, M, K);
    double ans3 = slu.dp2(N, M, K);
    if (ans1 != ans2 || ans1 != ans3) {
      cout << "Oops!" << endl;
      break;
    }
  }
  cout << "测试结束" << endl;
  return 0;
}