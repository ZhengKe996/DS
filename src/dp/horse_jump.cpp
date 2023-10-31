#include <iostream>

using namespace std;

class Solution {
 public:
  int jump(int a, int b, int k) { return process(0, 0, k, a, b); }

  int dp(int a, int b, int k) {
    vector<vector<vector<int>>> dp(
        10, vector<vector<int>>(9, vector<int>(k + 1, 0)));

    dp[a][b][0] = 1;

    for (int rest = 1; rest <= k; rest++) {
      for (int x = 0; x < 10; x++) {
        for (int y = 0; y < 9; y++) {
          int ways = pick(dp, x + 1, y + 2, rest - 1);
          ways += pick(dp, x + 2, y + 1, rest - 1);
          ways += pick(dp, x + 2, y - 1, rest - 1);
          ways += pick(dp, x + 1, y - 2, rest - 1);
          ways += pick(dp, x - 1, y - 2, rest - 1);
          ways += pick(dp, x - 2, y - 1, rest - 1);
          ways += pick(dp, x - 2, y + 1, rest - 1);
          ways += pick(dp, x - 1, y + 2, rest - 1);

          dp[x][y][rest] = ways;
        }
      }
    }
    return dp[0][0][k];
  }

 private:
  int process(int x, int y, int rest, int a, int b) {
    if (x < 0 || x > 9 || y < 0 || y > 8) return 0;

    if (rest == 0) return (x == a && y == b) ? 1 : 0;
    int ways = process(x + 1, y + 2, rest - 1, a, b);
    ways += process(x + 2, y + 1, rest - 1, a, b);
    ways += process(x + 2, y - 1, rest - 1, a, b);
    ways += process(x + 1, y - 2, rest - 1, a, b);
    ways += process(x - 1, y - 2, rest - 1, a, b);
    ways += process(x - 2, y - 1, rest - 1, a, b);
    ways += process(x - 2, y + 1, rest - 1, a, b);
    ways += process(x - 1, y + 2, rest - 1, a, b);

    return ways;
  }

  int pick(vector<vector<vector<int>>> &dp, int x, int y, int rest) {
    if (x < 0 || x > 9 || y < 0 || y > 8) {
      return 0;
    }
    return dp[x][y][rest];
  }
};

int main() {
  Solution s;
  int x = 7;
  int y = 7;
  int step = 10;

  cout << s.jump(x, y, step) << endl;
  cout << s.dp(x, y, step) << endl;
  return 0;
}