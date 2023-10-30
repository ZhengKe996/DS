#include <iostream>

using namespace std;

class Solution {
 public:
  int longestCommonSubsequence(string text1, string text2) {
    if (text1.size() == 0 || text2.size() == 0 || text1.empty() ||
        text2.empty())
      return 0;

    int N = text1.size();
    int M = text2.size();

    vector<vector<int>> dp(N, vector<int>(M, 0));
    dp[0][0] = text1[0] == text2[0] ? 1 : 0;
    for (int j = 1; j < M; j++)
      dp[0][j] = text1[0] == text2[j] ? 1 : dp[0][j - 1];

    for (int i = 1; i < N; i++)
      dp[i][0] = text1[i] == text2[0] ? 1 : dp[i - 1][0];

    for (int i = 1; i < N; i++) {
      for (int j = 1; j < M; j++) {
        int p1 = dp[i][j - 1];
        int p2 = dp[i - 1][j];
        int p3 = text1[i] == text2[j] ? 1 + dp[i - 1][j - 1] : 0;

        dp[i][j] = max(p1, max(p2, p3));
      }
    }
    return dp[N - 1][M - 1];
  }
};