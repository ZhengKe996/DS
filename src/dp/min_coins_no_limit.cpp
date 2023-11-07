#include <iostream>

using namespace std;

class Solution {
 private:
  int process(vector<int>& arr, int index, int rest) {
    if (index == arr.size())
      return rest == 0 ? 0 : INT32_MAX;
    else {
      int ans = INT32_MAX;
      for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
        int next = process(arr, index + 1, rest - zhang * arr[index]);
        if (next != INT32_MAX) ans = min(ans, zhang + next);
      }
      return ans;
    }
  };

 public:
  int minCoins(vector<int>& arr, int aim) { return process(arr, 0, aim); }

  int dp1(vector<int>& arr, int aim) {
    if (aim == 0) return 0;
    int N = arr.size();
    vector<vector<int>> dp(N + 1, vector<int>(aim + 1, 0));
    dp[N][0] = 0;
    for (int j = 1; j <= aim; j++) dp[N][j] = INT32_MAX;

    for (int index = N - 1; index >= 0; index--) {
      for (int rest = 0; rest <= aim; rest++) {
        int ans = INT32_MAX;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
          int next = dp[index + 1][rest - zhang * arr[index]];
          if (next != INT32_MAX) ans = min(ans, zhang + next);
        }
        dp[index][rest] = ans;
      }
    }
    return dp[0][aim];
  };

  int dp2(vector<int>& arr, int aim) {
    if (aim == 0) return 0;
    int N = arr.size();
    vector<vector<int>> dp(N + 1, vector<int>(aim + 1, 0));
    dp[N][0] = 0;
    for (int j = 1; j <= aim; j++) dp[N][j] = INT32_MAX;

    for (int index = N - 1; index >= 0; index--) {
      for (int rest = 0; rest <= aim; rest++) {
        dp[index][rest] = dp[index + 1][rest];
        if (rest - arr[index] >= 0 &&
            dp[index][rest - arr[index]] != INT32_MAX) {
          dp[index][rest] =
              min(dp[index][rest], dp[index][rest - arr[index]] + 1);
        }
      }
    }
    return dp[0][aim];
  }

  // Test
  vector<int> randomArray(int maxLen, int maxValue) {
    int N = (int)(rand() % maxLen);
    vector<int> arr(N, 0);
    for (int i = 0; i < N; i++) {
      arr[i] = (int)(rand() % maxValue) + 1;
    };
    return arr;
  };

  void printArray(vector<int>& arr) {
    for (int i = 0; i < arr.size(); i++) {
      cout << arr[i] << " " << endl;
    }
    cout << endl;
  }
};
int main() {
  Solution slu;
  int maxLen = 20;
  int maxValue = 30;
  int testTime = 3000;
  cout << "功能测试开始" << endl;
  for (int i = 0; i < testTime; i++) {
    int N = (int)(rand() % maxLen);
    vector<int> arr = slu.randomArray(N, maxValue);
    int aim = (int)(rand() % maxValue);
    int ans1 = slu.minCoins(arr, aim);
    int ans2 = slu.dp1(arr, aim);
    int ans3 = slu.dp2(arr, aim);
    if (ans1 != ans2 || ans1 != ans3) {
      cout << "Oops!" << endl;
      slu.printArray(arr);
      cout << aim << endl;
      cout << ans1 << endl;
      cout << ans2 << endl;
      break;
    }
  }
  cout << "功能测试结束" << endl;
  return 0;
}