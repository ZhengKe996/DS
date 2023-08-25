#include <iostream>

using namespace std;

class Solution {
 private:
  int process(vector<long> &sum, int l, int r, int lower, int upper) {
    if (l == r)
      return sum[l] >= lower && sum[l] <= upper ? 1 : 0;  // 不越界返回1

    int mid = l + ((r - l) >> 1);
    return process(sum, l, mid, lower, upper) +
           process(sum, mid + 1, r, lower, upper) +
           merge(sum, l, mid, r, lower, upper);
  }

  int merge(vector<long> &arr, int l, int r, int mid, int lower, int upper) {
    int ans = 0, windowL = l, windowR = l;
    for (int i = mid + 1; i <= r; i++) {
      long min = arr[i] - upper, max = arr[i] - lower;
      while (windowR <= mid && arr[windowR] <= max) {
        windowR++;
      }

      while (windowL <= mid && arr[windowL] < min) {
        windowL++;
      }
      ans += windowR - windowL;
    }

    vector<long> help(r - l + 1, 0);
    int i = 0, p1 = l, p2 = mid + 1;
    while (p1 <= mid && p2 <= r)
      help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
    while (p1 <= mid) help[i++] = arr[p1++];
    while (p2 <= r) help[i++] = arr[p2++];
    for (i = 0; i < help.size(); i++) arr[l + i] = help[i];
    return ans;
  }

 public:
  int countRangeSum(vector<int> &nums, int lower, int upper) {
    if (nums.empty() || nums.size() == 0) return 0;
    vector<long> sum(nums.size(), 0);
    sum[0] = nums[0];
    for (int i = 0; i < nums.size(); i++)
      sum[i] = sum[i - 1] + nums[i];  // 前缀和

    return process(sum, 0, sum.size() - 1, lower, upper);
  }
};

int main() {
  Solution s;

  vector<int> nums = {-2, 5, -1};
  int lower = -2, upper = 2;
  int ans = s.countRangeSum(nums, lower, upper);
  cout << ans << endl;
  return 0;
}