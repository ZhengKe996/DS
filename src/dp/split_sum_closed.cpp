#include <iostream>

using namespace std;

class Solution {
 private:
  int process(vector<int>& arr, int index, int rest) {
    if (index == arr.size()) return 0;

    int p1 = process(arr, index + 1, rest);
    int p2 = 0;
    if (rest - arr[index] >= 0) p2 = process(arr, index + 1, rest - arr[index]);
    return max(p1, p2);
  };

 public:
  int right(vector<int>& arr) {
    if (arr.size() <= 2 || arr.empty()) return 0;
    int sum = 0;

    for (int num : arr) sum += num;
    return process(arr, 0, sum / 2);
  }

  int dp(vector<int>& arr) {
    if (arr.size() <= 2 || arr.empty()) return 0;
    int sum = 0;

    for (int num : arr) sum += num;

    sum = sum / 2;
    int N = arr.size();
    vector<vector<int>> dp(N + 1, vector<int>(sum + 1, 0));

    for (int index = N - 1; index >= 0; index--) {
      for (int rest = 0; rest <= sum; rest++) {
        int p1 = dp[index + 1][rest];
        int p2 = 0;
        if (rest - arr[index] >= 0) p2 = dp[index + 1][rest - arr[index]];
        dp[index][rest] = max(p1, p2);
      }
    }
    return dp[0][sum];
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
  int maxValue = 50;
  int testTime = 10;

  cout << "测试开始" << endl;
  for (int i = 0; i < testTime; i++) {
    int len = (int)(rand() % maxLen);
    vector<int> arr = slu.randomArray(len, maxValue);
    int ans1 = slu.right(arr);
    int ans2 = slu.dp(arr);
    if (ans1 != ans2) {
      slu.printArray(arr);
      cout << ans1 << endl;
      cout << ans2 << endl;
      cout << "Oops!" << endl;
      break;
    }
  }

  cout << "测试结束" << endl;

  return 0;
}