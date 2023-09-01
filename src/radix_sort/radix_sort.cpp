#include <iostream>
using namespace std;

class Solution {
 private:
  int maxbits(vector<int>& arr) {
    int ma = INT32_MIN;
    for (int i = 0; i < arr.size(); i++) ma = max(ma, arr[i]);
    int res = 0;
    while (ma != 0) {
      res++;
      ma /= 10;
    }
    return res;
  }
  int getDigit(int x, int d) { return ((x / ((int)pow(10, d - 1))) % 10); }

  void radix_sort(vector<int>& arr, int l, int r, int digit) {
    const int radix = 10;
    int i = 0, j = 0;
    vector<int> help(r - l + 1, 0);

    for (int d = 1; d <= digit; d++) {
      vector<int> count(radix, 0);

      for (i = l; i <= r; i++) {
        j = getDigit(arr[i], d);
        count[j]++;
      }
      for (i = 1; i < radix; i++) {
        count[i] = count[i] + count[i - 1];
      }
      for (i = r; i >= l; i--) {
        j = getDigit(arr[i], d);
        help[count[j] - 1] = arr[i];
        count[j]--;
      }
      for (i = l, j = 0; i <= r; i++, j++) {
        arr[i] = help[j];
      }
    }
  }

 public:
  void radix_sort(vector<int>& arr) {
    if (arr.empty() || arr.size() < 2) return;
    radix_sort(arr, 0, arr.size() - 1, maxbits(arr));
  }

  // ==================== TEST ====================

  void comparator(vector<int>& arr) {
    if (arr.size() < 2) return;
    for (int i = 1; i < arr.size(); i++) {
      for (int j = i - 1; j >= 0; j--) {
        if (arr[j] > arr[j + 1]) {
          swap(arr[j], arr[j + 1]);
        }
      }
    }
  }

  vector<int> generate_random_array(int maxSize, int maxValue) {
    vector<int> arr((int)rand() % (maxSize + 1), 0);
    for (int i = 0; i < arr.size(); i++) {
      arr[i] = (int)((rand() % (maxValue + 1)));
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
  cout << "Begin" << endl;
  for (int i = 0; i < testTime; i++) {
    vector<int> arr1 = solution.generate_random_array(maxSize, maxValue);
    vector<int> arr2 = solution.copy_array(arr1);
    solution.radix_sort(arr1);
    solution.comparator(arr2);

    if (!solution.is_equal(arr1, arr2)) {
      cout << "ERROR!" << endl;
      succeed = false;
      solution.print_array(arr1);
      solution.print_array(arr2);
      break;
    }
  }
  cout << (succeed ? "Nice!" : "Fucking fucked!") << endl;
  return 0;
}