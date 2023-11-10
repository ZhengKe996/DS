#include <iostream>

using namespace std;

class Solution {
 private:
  int process(vector<int>& arr, int index, int picks, int rest) {
    if (index == arr.size()) return picks == 0 ? 0 : -1;

    int p1 = process(arr, index + 1, picks, rest);
    int p2 = -1, next = -1;
    if (arr[index] <= rest)
      next = process(arr, index + 1, picks - 1, rest - arr[index]);

    if (next != -1) p2 = arr[index] + next;

    return max(p1, p2);
  }

 public:
  int right(vector<int>& arr) {
    if (arr.empty() || arr.size() < 2) return 0;

    int sum = 0;
    for (int num : arr) sum += num;

    if ((arr.size() & 1) == 0) {
      // 偶数情况下 只有一条路
      return process(arr, 0, arr.size() / 2, sum / 2);
    } else {
      // 奇数有两条路
      return max(process(arr, 0, arr.size() / 2, sum / 2),
                 process(arr, 0, arr.size() / 2 + 1, sum / 2));
    }
  }

  int dp(vector<int>& arr) {
    if (arr.empty() || arr.size() < 2) return 0;

    int sum = 0;
    for (int num : arr) sum += num;

    sum = sum >> 1;
    int N = arr.size();
    int M = (N + 1) / 2;
    vector<vector<vector<int>>> dp(
        N + 1, vector<vector<int>>(M + 1, vector<int>(sum + 1, -1)));

    for (int rest = 0; rest <= sum; rest++) dp[N][0][rest] = 0;

    for (int index = N - 1; index >= 0; index--) {
      for (int picks = 0; picks <= M; picks++) {
        for (int rest = 0; rest <= sum; rest++) {
          int p1 = dp[index + 1][picks][rest];  // 当前位置不选的情况
          int p2 = -1;
          int next = -1;
          if (picks - 1 >= 0 && arr[index] <= rest)
            next = dp[index + 1][picks - 1]
                     [rest - arr[index]];  // 当前位置选的情况

          if (next != -1) p2 = arr[index] + next;
          dp[index][picks][rest] = max(p2, p1);
        }
      }
    }

    if ((arr.size() & 1) == 0) {
      // 偶数情况下 只有一条路
      return dp[0][arr.size() / 2][sum];
    } else {
      // 奇数有两条路
      return max(dp[0][arr.size() / 2][sum], dp[0][arr.size() / 2 + 1][sum]);
    }
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
};