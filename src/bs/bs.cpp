#include <iostream>

using namespace std;

class Solution {
 public:
  /**
   * 二分存在
   */
  bool bs_exist(vector<int>& arr, int num) {
    if (arr.size() == 0) return false;
    int l = 0, r = arr.size() - 1;
    while (l < r) {
      int mid = l + ((r - l) >> 1);
      if (arr[mid] == num)
        return true;
      else if (arr[mid] > num)
        r = mid - 1;
      else
        l = mid + 1;
    }

    return arr[l] == num;
  }

  /**
   * 二分查找
   */
  int bs_search(vector<int>& arr, int num) {
    if (arr.size() == 0) return -1;
    int l = 0, r = arr.size() - 1;
    while (l < r) {
      int mid = l + ((r - l) >> 1);
      if (arr[mid] == num)
        return mid;
      else if (arr[mid] > num)
        r = mid - 1;
      else
        l = mid + 1;
    }
    return -1;
  }

  /**
   * 在arr上，找满足>=value的最左位置
   */
  int bs_near_left(vector<int>& arr, int value) {
    if (arr.size() == 0) return -1;
    int l = 0, r = arr.size() - 1, index = -1;
    while (l < r) {
      int mid = l + ((r - l) >> 1);
      if (arr[mid] >= value) {
        index = mid;
        r = mid - 1;
      } else
        l = mid + 1;
    }
    return index;
  }

  int bs_near_right(vector<int>& arr, int value) {
    if (arr.size() == 0) return -1;
    int l = 0, r = arr.size() - 1, index = -1;
    while (l < r) {
      int mid = l + ((r - l) >> 1);
      if (arr[mid] <= value) {
        index = mid;
        l = mid + 1;
      } else
        r = mid - 1;
    }
    return index;
  }

  int get_less_index(vector<int>& arr) {
    if (arr.size() == 0) return -1;
    if (arr.size() == 1) return 0;
    if (arr[arr.size() - 1] < arr[arr.size() - 2]) return arr.size() - 1;

    int l = 1, r = arr.size() - 2;

    while (l < r) {
      int mid = l + ((r - l) >> 1);
      if (arr[mid] > arr[mid - 1]) {
        r = mid - 1;
      } else if (arr[mid] > arr[mid + 1]) {
        l = mid + 1;
      } else {
        return mid;
      }
    }
    return l;
  }
};

int main() {
  Solution solution;
  vector<int> nums{1, 2, 3, 4, 5, 6, 7, 8};
  auto ans = solution.bs_near_right(nums, 4);
  cout << ans;
  return 0;
}