#include <iostream>

using namespace std;

class Solution {
 public:
  int longestPalindromeSubseq(string s) {
    if (s.empty() || s.size() == 0) return 0;
    int N = s.size();
    vector<vector<int>> dp(N, vector<int>(N, 0));
    dp[N - 1][N - 1] = 1;
    for (int i = 0; i < N - 1; i++) {
      dp[i][i] = 1;
      dp[i][i + 1] = s[i] == s[i + 1] ? 2 : 1;
    }

    for (int L = N - 3; L >= 0; L--) {
      for (int R = L + 2; R < N; R++) {
        dp[L][R] = max(dp[L][R - 1], dp[L + 1][R]);
        if (s[L] == s[R]) dp[L][R] = max(dp[L][R], 2 + dp[L + 1][R - 1]);
      }
    }
    return dp[0][N - 1];
  }
};