#include <iostream>
#include <queue>
using namespace std;

class Solution {
 public:
  int right(vector<int>& arr, int sum) {
    if (arr.empty() || arr.size() == 0 || sum < 0) return 0;
    int N = arr.size();
    int count = 0;
    for (int L = 0; L < N; L++) {
      for (int R = L; R < N; R++) {
        int ma = arr[L];
        int mi = arr[L];
        for (int i = L + 1; i <= R; i++) {
          ma = max(ma, arr[i]);
          mi = min(mi, arr[i]);
        }
        if (ma - mi <= sum) count++;
      }
    }
    return count;
  }

  int num(vector<int>& arr, int sum) {
    if (arr.empty() || arr.size() == 0 || sum < 0) return 0;
    int N = arr.size();
    int count = 0;

    deque<int> maxWindow;
    deque<int> minWindow;
    int R = 0;
    for (int L = 0; L < N; L++) {
      while (R < N) {
        while (!maxWindow.empty() && arr[maxWindow.back()] <= arr[R])
          maxWindow.pop_back();

        maxWindow.push_back(R);

        while (!minWindow.empty() && arr[minWindow.back()] >= arr[R])
          minWindow.pop_back();
        minWindow.push_back(R);

        if (arr[maxWindow.front()] - arr[minWindow.front()] > sum) {
          break;
        } else {
          R++;
        }
      }
      count += R - L;
      if (maxWindow.front() == L) {
        maxWindow.pop_front();
      }
      if (minWindow.front() == L) {
        minWindow.pop_front();
      }
    }
    return count;
  }

  // TEST
  vector<int> generateRandomArray(int maxSize, int maxValue) {
    vector<int> arr(rand() % (maxSize + 1), 0);
    for (int i = 0; i < arr.size(); i++) {
      arr[i] = (rand() % (maxSize + 1)) - (rand() % (maxSize));
    }

    return arr;
  }

  void printArray(vector<int>& arr) {
    if (arr.size() == 0) return;
    for (int i = 0; i < arr.size(); i++) {
      cout << arr[i] << " ";
    }
    cout << endl;
  }
};

int main() {
  Solution slu;
  int maxLen = 100;
  int maxValue = 200;
  int testTime = 10000;
  cout << "测试开始" << endl;
  for (int i = 0; i < testTime; i++) {
    vector<int> arr = slu.generateRandomArray(maxLen, maxValue);
    int sum = (int)(rand() % (maxValue + 1));
    int ans1 = slu.right(arr, sum);
    int ans2 = slu.num(arr, sum);
    if (ans1 != ans2) {
      cout << "Oops!" << endl;
      slu.printArray(arr);
      cout << sum << endl;
      cout << ans1 << endl;
      cout << ans2 << endl;
      break;
    }
  }
  cout << "测试结束" << endl;
  return 0;
}