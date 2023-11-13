#include <iostream>
#include <queue>

using namespace std;

class Solution {
 public:
  vector<int> right(vector<int> &nums, int w) {
    if (nums.empty() || w < 1 || nums.size() < w) return {};
    int N = nums.size();
    vector<int> res(N - w + 1, 0);

    int index = 0;
    int L = 0;
    int R = w - 1;
    while (R < N) {
      int ma = nums[L];
      for (int i = L + 1; i <= R; i++) {
        ma = max(ma, nums[i]);
      }
      res[index++] = ma;
      L++;
      R++;
    }
    return res;
  }
  vector<int> getMaxWindow(vector<int> &nums, int w) {
    if (nums.empty() || w < 1 || nums.size() < w) return {};
    deque<int> qmax;
    vector<int> res(nums.size() - w + 1, 0);
    int index = 0;
    for (int R = 0; R < nums.size(); R++) {
      while (!qmax.empty() && nums[qmax.back()] <= nums[R]) qmax.pop_back();

      qmax.push_back(R);
      if (qmax.front() == R - w) qmax.pop_front();

      if (R >= w - 1) res[index++] = nums[qmax.front()];
    }
    return res;
  }

  // TEST
  vector<int> generateRandomArray(int maxSize, int maxValue) {
    vector<int> arr(rand() % (maxSize + 1), 0);
    for (int i = 0; i < arr.size(); i++) {
      arr[i] = (rand() % (maxSize + 1)) - (rand() % (maxSize));
    }

    return arr;
  }

  bool isEqual(vector<int> &arr1, vector<int> &arr2) {
    if ((arr1.size() == 0 && arr2.size() != 0) ||
        (arr1.size() != 0 && arr2.size() == 0))
      return false;

    if (arr1.size() == 0 && arr2.size() == 0) return true;
    if (arr1.size() != arr2.size()) return false;
    for (int i = 0; i < arr1.size(); i++) {
      if (arr1[i] != arr2[i]) return false;
    }
    return true;
  }
};

int main() {
  Solution slu;
  int testTime = 100;
  int maxSize = 100;
  int maxValue = 100;

  cout << "test begin" << endl;
  for (int i = 0; i < testTime; i++) {
    vector<int> arr = slu.generateRandomArray(maxSize, maxValue);
    int w = (int)(rand() % (arr.size() + 1));
    vector<int> ans1 = slu.getMaxWindow(arr, w);
    vector<int> ans2 = slu.right(arr, w);
    if (!slu.isEqual(ans1, ans2)) {
      cout << "Oops!" << endl;
    }
  }
  cout << "test finish" << endl;
  return 0;
};