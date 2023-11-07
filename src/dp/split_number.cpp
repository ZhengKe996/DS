#include <iostream>

using namespace std;

class Solution {
 private:
  int process(int pre, int rest) {
    if (rest == 0) return 1;
    if (pre > rest) return 0;
    int ways = 0;
    for (int first = pre; first <= rest; first++) {
      ways += process(first, rest - first);
    }
    return ways;
  };

 public:
  int ways(int n) {
    if (n < 0) return 0;

    if (n == 1) return 1;
    return process(1, n);
  }

  int dp1(int n) {
    if (n < 0) return 0;
    if (n == 1) return 1;

    vector<vector<int>> dp(n + 1, vector<int>(n + 1, 0));
    for (int pre = 1; pre <= n; pre++) {
      dp[pre][0] = 1;
      dp[pre][pre] = 1;
    }

    for (int pre = n - 1; pre >= 1; pre--) {
      for (int rest = pre + 1; rest <= n; rest++) {
        int ways = 0;
        for (int first = pre; first <= rest; first++) {
          ways += dp[first][rest - first];
        }
        dp[pre][rest] = ways;
      }
    }
    return dp[1][n];
  }

  int dp2(int n) {
    if (n < 0) return 0;
    if (n == 1) return 1;

    vector<vector<int>> dp(n + 1, vector<int>(n + 1, 0));
    for (int pre = 1; pre <= n; pre++) {
      dp[pre][0] = 1;
      dp[pre][pre] = 1;
    }
    for (int pre = n - 1; pre >= 1; pre--) {
      for (int rest = pre + 1; rest <= n; rest++) {
        dp[pre][rest] = dp[pre + 1][rest];
        dp[pre][rest] += dp[pre][rest - pre];
      }
    }
    return dp[1][n];
  }
};

int main() {
  Solution slu;
  int test = 39;

  cout << slu.ways(test) << endl;
  cout << slu.dp1(test) << endl;
  cout << slu.dp2(test) << endl;

  return 0;
}