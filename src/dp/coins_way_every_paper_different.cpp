#include <iostream>

using namespace std;

class Solution {
 private:
  int process(vector<int>& arr, int index, int rest) {
    if (rest < 0) return 0;
    if (index == arr.size())
      return rest == 0 ? 1 : 0;
    else
      return process(arr, index + 1, rest) +
             process(arr, index + 1, rest - arr[index]);
  };

 public:
  int coinWays(vector<int>& arr, int aim) { return process(arr, 0, aim); };
  int dp(vector<int>& arr, int aim) {
    if (aim == 0) return 1;
    int N = arr.size();
    vector<vector<int>> dp(N + 1, vector<int>(aim + 1, 0));
    dp[N][0] = 1;
    for (int index = N - 1; index >= 0; index--) {
      for (int rest = 0; rest <= aim; rest++) {
        dp[index][rest] =
            dp[index + 1][rest] + (rest - arr[index] >= 0
                                       ? dp[index + 1][rest - arr[index]]
                                       : 0);  // 小心数组越界
      }
    }
    return dp[0][aim];
  };

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
    int ans1 = sln.coinWays(arr, aim);
    int ans2 = sln.dp(arr, aim);
    if (ans1 != ans2) {
      cout << "Oops!" << endl;
      sln.printArray(arr);

      cout << aim << endl;
      cout << ans1 << endl;
      cout << ans2 << endl;

      break;
    }
  }

  cout << "测试结束" << endl;

  return 0;
}
