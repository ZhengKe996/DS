#include <iostream>

using namespace std;

class Solution {
 private:
  int process(vector<int>& arr, int index, int rest) {
    if (index == arr.size()) return rest == 0 ? 1 : 0;
    int ways = 0;
    for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
      ways += process(arr, index + 1, rest - (zhang * arr[index]));
    }
    return ways;
  }

 public:
  int coinsWays(vector<int>& arr, int aim) {
    if (arr.size() == 0 || aim < 0 || arr.empty()) return 0;
    return process(arr, 0, aim);
  }
  int dp1(vector<int>& arr, int aim) {
    if (arr.size() == 0 || aim < 0 || arr.empty()) return 0;

    int N = arr.size();
    vector<vector<int>> dp(N + 1, vector<int>(aim + 1, 0));
    dp[N][0] = 1;
    for (int index = N - 1; index >= 0; index--) {
      for (int rest = 0; rest <= aim; rest++) {
        int ways = 0;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
          ways += dp[index + 1][rest - (zhang * arr[index])];
        }
        dp[index][rest] = ways;
      }
    }
    return dp[0][aim];
  }

  int dp2(vector<int>& arr, int aim) {
    if (arr.size() == 0 || aim < 0 || arr.empty()) return 0;

    int N = arr.size();
    vector<vector<int>> dp(N + 1, vector<int>(aim + 1, 0));
    dp[N][0] = 1;
    for (int index = N - 1; index >= 0; index--) {
      for (int rest = 0; rest <= aim; rest++) {
        dp[index][rest] = dp[index + 1][rest];
        if (rest - arr[index] >= 0)
          dp[index][rest] += dp[index][rest - arr[index]];
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
  Solution sln;
  int maxLen = 20;
  int maxValue = 30;
  int testTime = 1000000;

  cout << "测试开始" << endl;
  for (int i = 0; i < testTime; i++) {
    vector<int> arr = sln.randomArray(maxLen, maxValue);
    int aim = (int)(rand() % maxValue);
    int ans1 = sln.coinsWays(arr, aim);
    int ans2 = sln.dp1(arr, aim);
    int ans3 = sln.dp2(arr, aim);

    if (ans1 != ans2 || ans1 != ans3) {
      cout << "Oops!" << endl;
      sln.printArray(arr);

      cout << aim << endl;
      cout << ans1 << endl;
      cout << ans2 << endl;
      cout << ans3 << endl;

      break;
    }
  }

  cout << "测试结束" << endl;

  return 0;
}
