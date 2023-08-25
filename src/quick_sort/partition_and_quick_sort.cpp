#include <iostream>
using namespace std;
class Solution {
 private:
  void swap(vector<int>& arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  int partition(vector<int>& arr, int l, int r) {
    if (l > r) return -1;
    if (l == r) return l;
    int lessEqual = l - 1, index = l;
    while (index < r) {
      if (arr[index] <= arr[r]) swap(arr, index, ++lessEqual);
      index++;
    }
    swap(arr, ++lessEqual, r);
    return lessEqual;
  }

  vector<int> netherlandsFlag(vector<int>& arr, int l, int r) {
    if (l > r) return {-1, -1};
    if (l == r) return {l, r};

    int less = l - 1, more = r, index = l;
    while (index < more) {
      if (arr[index] == arr[r])
        index++;
      else if (arr[index] < arr[r])
        swap(arr, index++, ++less);
      else
        swap(arr, index, --more);
    }
    swap(arr, more, r);
    return {less + 1, more};
  }

  void process1(vector<int>& arr, int l, int r) {
    if (l >= r) return;
    int mid = partition(arr, l, r);
    process1(arr, l, mid - 1);
    process1(arr, mid + 1, r);
  }

  void process2(vector<int>& arr, int l, int r) {
    if (l >= r) return;
    vector<int> equal_area = netherlandsFlag(arr, l, r);
    process2(arr, l, equal_area[0] - 1);
    process2(arr, equal_area[1] + 1, r);
  }
  void process3(vector<int>& arr, int l, int r) {
    if (l >= r) return;
    swap(arr, l + (int)(rand() % (r - l + 1)), r);
    vector<int> equal_area = netherlandsFlag(arr, l, r);
    process3(arr, l, equal_area[0] - 1);
    process3(arr, equal_area[1] + 1, r);
  }

 public:
  void quick_sort(vector<int>& arr) {
    if (arr.empty() || arr.size() < 2) return;
    process1(arr, 0, arr.size() - 1);
  }

  void quick_sort2(vector<int>& arr) {
    if (arr.empty() || arr.size() < 2) return;
    process2(arr, 0, arr.size() - 1);
  }
  void quick_sort3(vector<int>& arr) {
    if (arr.empty() || arr.size() < 2) return;
    process3(arr, 0, arr.size() - 1);
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
  bool succeed = true;

  for (int i = 0; i < testTime; i++) {
    vector<int> arr1 = solution.generate_random_array(maxSize, maxValue);
    vector<int> arr2 = solution.copy_array(arr1);
    vector<int> arr3 = solution.copy_array(arr1);
    solution.quick_sort(arr1);
    solution.quick_sort2(arr2);
    solution.quick_sort3(arr3);
    if (!solution.is_equal(arr1, arr2) || !solution.is_equal(arr2, arr3)) {
      solution.print_array(arr1);
      solution.print_array(arr2);
      solution.print_array(arr3);
      succeed = false;
      break;
    }
  }
  cout << (succeed ? "Nice!" : "Oops!") << endl;

  return 0;
}