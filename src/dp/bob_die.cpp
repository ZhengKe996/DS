#include <math.h>

#include <iostream>
using namespace std;

class Solution {
 private:
  long process(int row, int col, int rest, int N, int M) {
    if (row < 0 || row == N || col < 0 || col == M) return 0;

    // 还在棋盘中！
    if (rest == 0) return 1;

    // 还在棋盘中！还有步数要走
    long up = process(row - 1, col, rest - 1, N, M);
    long down = process(row + 1, col, rest - 1, N, M);
    long left = process(row, col - 1, rest - 1, N, M);
    long right = process(row, col + 1, rest - 1, N, M);

    return up + down + left + right;
  };

  long pick(vector<vector<vector<long>>> &dp, int N, int M, int r, int c,
            int rest) {
    if (r < 0 || r == N || c < 0 || c == M) {
      return 0;
    }
    return dp[r][c][rest];
  }

 public:
  double livePosibility(int row, int col, int k, int N, int M) {
    return (double)process(row, col, k, N, M) / pow(4, k);
  }

  double dp(int row, int col, int k, int N, int M) {
    vector<vector<vector<long>>> dp(
        N, vector<vector<long>>(M, vector<long>(k + 1, 0)));

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        dp[i][j][0] = 1;
      }
    }

    for (int rest = 1; rest <= k; rest++) {
      for (int r = 0; r < N; r++) {
        for (int c = 0; c < M; c++) {
          dp[r][c][rest] = pick(dp, N, M, r - 1, c, rest - 1);
          dp[r][c][rest] += pick(dp, N, M, r + 1, c, rest - 1);
          dp[r][c][rest] += pick(dp, N, M, r, c - 1, rest - 1);
          dp[r][c][rest] += pick(dp, N, M, r, c + 1, rest - 1);
        }
      }
    }

    return (double)dp[row][col][k] / pow(4, k);
  }
};

int main() {
  Solution sol;

  cout << (sol.livePosibility(6, 6, 10, 50, 50)) << endl;
  cout << (sol.dp(6, 6, 10, 50, 50)) << endl;
  return 0;
}