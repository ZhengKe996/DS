#include <iostream>

using namespace std;

class Solution {
 public:
  int minPathSum1(vector<vector<int>>& m) {
    if (m.empty() || m.size() == 0 || m[0].empty() || m[0].size() == 0)
      return 0;

    int row = m.size();
    int col = m[0].size();
    vector<vector<int>> dp(row, vector<int>(col, 0));

    dp[0][0] = m[0][0];
    for (int i = 1; i < row; i++) dp[i][0] = dp[i - 1][0] + m[i][0];
    for (int j = 1; j < col; j++) dp[0][j] = dp[0][j - 1] + m[0][j];

    for (int i = 1; i < row; i++) {
      for (int j = 1; j < col; j++) {
        dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
      }
    }

    return dp[row - 1][col - 1];
  };
  int minPathSum2(vector<vector<int>>& m) {
    if (m.empty() || m.size() == 0 || m[0].empty() || m[0].size() == 0)
      return 0;

    int row = m.size();
    int col = m[0].size();
    vector<int> arr(col, 0);
    arr[0] = m[0][0];  // 起点
    for (int j = 1; j < col; j++) {
      arr[j] = arr[j - 1] + m[0][j];  // 0列特殊处理
    }
    for (int i = 1; i < row; i++) {
      arr[0] += m[i][0];
      for (int j = 1; j < col; j++) {
        arr[j] = min(arr[j - 1], arr[j]) + m[i][j];
      }
    }
    return arr[col - 1];
  };

  vector<vector<int>> generateRandomMatrix(int rowSize, int colSize) {
    if (rowSize < 0 || colSize < 0) return {};

    vector<vector<int>> result(rowSize, vector<int>(colSize, 0));
    for (int i = 0; i != result.size(); i++) {
      for (int j = 0; j != result[0].size(); j++) {
        result[i][j] = (int)(rand() % 100);
      }
    }
    return result;
  }

  void printMatrix(vector<vector<int>>& matrix) {
    for (int i = 0; i != matrix.size(); i++) {
      for (int j = 0; j != matrix[0].size(); j++) {
        cout << matrix[i][j] + " " << endl;
      }
      cout << endl;
    }
  }
};

int main() {
  Solution solution;
  int rowSize = 10;
  int colSize = 10;
  vector<vector<int>> m = solution.generateRandomMatrix(rowSize, colSize);

  cout << (solution.minPathSum1(m)) << endl;
  cout << (solution.minPathSum2(m)) << endl;
  return 0;
}