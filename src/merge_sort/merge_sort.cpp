#include <iostream>

using namespace std;

class Solution {
 private:
  void process(vector<int>& arr, int l, int r) {
    if (l == r) return;
    int mid = l + ((r - l) >> 1);
    process(arr, l, mid);
    process(arr, mid + 1, r);
    merge(arr, l, mid, r);
  }
  void merge(vector<int>& arr, int l, int mid, int r) {
    vector<int> help((r - l + 1), 0);
    int i = 0, p1 = l, p2 = mid + 1;
    while (p1 <= mid && p2 <= r) {
      help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
    }

    while (p1 <= mid) help[i++] = arr[p1++];
    while (p2 <= r) help[i++] = arr[p2++];
    for (i = 0; i < help.size(); i++) arr[l + i] = help[i];
  }

 public:
  void merge_sort1(vector<int>& arr) {
    if (arr.size() < 2) return;
    process(arr, 0, arr.size() - 1);
  }
  void merge_sort2(vector<int>& arr) {
    if (arr.size() < 2) return;
    int N = arr.size(), merge_size = 1;  // merge_size 步长
    while (merge_size < N) {
      int l = 0;
      while (l < N) {
        if (merge_size >= N - l) break;

        int mid = l + merge_size - 1;
        int r = mid + min(merge_size, (N - mid - 1));
        merge(arr, l, mid, r);
        l = r + 1;
      }
      if (merge_size > N / 2) break;
      merge_size <<= 1;
    }
  }

  // ==================== TEST ====================
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
  cout << "Begin" << endl;
  for (int i = 0; i < testTime; i++) {
    vector<int> arr1 = solution.generate_random_array(maxSize, maxValue);

    vector<int> arr2 = solution.copy_array(arr1);

    solution.merge_sort1(arr1);
    solution.merge_sort2(arr2);

    if (!solution.is_equal(arr1, arr2)) {
      cout << "ERROR!" << endl;
      solution.print_array(arr1);
      solution.print_array(arr2);
      break;
    }
  }
  cout << "Finish!" << endl;
  return 0;
}