#include <iostream>

using namespace std;

class Solution {
 private:
  int process(vector<int>& w, vector<int>& v, int index, int rest) {
    if (rest < 0) return -1;
    if (index == w.size()) return 0;
    int p1 = process(w, v, index + 1, rest);
    int p2 = 0;
    int next = process(w, v, index + 1, rest - w[index]);

    if (next != -1) p2 = v[index] + next;
    return max(p1, p2);
  };

 public:
  int maxValue(vector<int>& w, vector<int>& v, int bag) {
    if (w.empty() || v.empty() || w.size() != v.size() || w.size() == 0)
      return 0;
    return process(w, v, 0, bag);
  }

  int dp(vector<int>& w, vector<int>& v, int bag) {
    if (w.empty() || v.empty() || w.size() != v.size() || w.size() == 0)
      return 0;
    int N = w.size();

    vector<vector<int>> dp(N + 1, vector<int>(bag + 1, 0));
    for (int index = N - 1; index >= 0; index--) {
      for (int rest = 0; rest <= bag; rest++) {
        int p1 = dp[index + 1][rest];
        int p2 = 0;
        int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
        if (next != -1) p2 = v[index] + next;

        dp[index][rest] = max(p1, p2);
      }
    }
    return dp[0][bag];
  }
};

int main() {
  Solution s;
  vector<int> weights = {3, 2, 4, 7, 3, 1, 7};
  vector<int> values = {5, 6, 3, 19, 12, 4, 2};
  int bag = 15;
  cout << s.maxValue(weights, values, bag) << endl;
  cout << s.dp(weights, values, bag) << endl;
  return 0;
}