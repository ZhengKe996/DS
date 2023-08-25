#include <iostream>

using namespace std;

class Solution {
 private:
  int process(vector<int>& arr, int l, int r) {
    if (l == r) return 0;
    int mid = l + ((r - l) >> 1);
    return process(arr, l, mid) + process(arr, mid + 1, r) +
           merge(arr, l, mid, r);
  }

  int merge(vector<int>& arr, int l, int mid, int r) {
    int ans = 0, windowR = mid + 1;
    for (int i = l; i <= mid; i++) {
      while (windowR <= r && (long)arr[i] > (long)arr[windowR] * 2) windowR++;
      ans += windowR - mid - 1;
    }
    vector<int> help(r - l + 1, 0);
    int i = 0, p1 = l, p2 = mid + 1;

    while (p1 <= mid && p2 <= r) {
      help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
    }
    while (p1 <= mid) help[i++] = arr[p1++];
    while (p2 <= r) help[i++] = arr[p2++];
    for (i = 0; i < help.size(); i++) arr[l + i] = help[i];
    return ans;
  }

 public:
  int reverse_pairs(vector<int>& arr) {
    if (arr.size() < 2) return 0;

    return process(arr, 0, arr.size() - 1);
  }

  int comparator(vector<int>& arr) {
    int ans = 0;
    for (int i = 0; i < arr.size(); i++) {
      for (int j = i + 1; j < arr.size(); j++) {
        if (arr[i] > (arr[j] << 1)) ans++;
      }
    }
    return ans;
  }

  // ========== TEST ==========
  vector<int> generate_random_array(int maxSize, int maxValue) {
    vector<int> arr((int)rand() % (maxSize + 1), 0);
    for (int i = 0; i < arr.size(); i++) {
      arr[i] = (int)((rand() % (maxValue + 1)) - (rand() % maxValue));
    }
    return arr;
  }

  vector<int> copy_array(vector<int>& arr) {
    vector<int> res(arr.size(), 0);
    for (int i = 0; i < arr.size(); i++) res[i] = arr[i];

    return res;
  }

  bool is_equal(vector<int>& arr1, vector<int>& arr2) {
    if (arr1.size() != arr2.size()) return false;

    for (int i = 0; i < arr1.size(); i++) {
      if (arr1[i] != arr2[i]) return false;
    }
    return true;
  }

  void print_array(vector<int>& arr) {
    for (int i = 0; i < arr.size(); i++) {
      cout << arr[i] << " ";
    }
    cout << endl;
  }
};

int main() {
  Solution solution;
  int testTime = 500000, maxSize = 100, maxValue = 100;
  cout << "测试开始" << endl;
  for (int i = 0; i < testTime; i++) {
    vector<int> arr1 = solution.generate_random_array(maxSize, maxValue);
    vector<int> arr2 = solution.copy_array(arr1);
    if (solution.reverse_pairs(arr1) != solution.comparator(arr2)) {
      cout << "Oops" << endl;
      solution.print_array(arr1);
      solution.print_array(arr2);
      break;
    }
  }
  cout << "测试结束" << endl;
  return 0;
}