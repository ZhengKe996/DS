#include <algorithm>
#include <iostream>

using namespace std;

class Solution {
 public:
  void counting_sort(vector<int>& arr) {
    if (arr.empty() || arr.size() < 2) return;

    int ma = INT32_MIN;
    for (int i = 0; i < arr.size(); i++) ma = max(ma, arr[i]);

    vector<int> bucket(ma + 1, 0);
    for (int i = 0; i < arr.size(); i++) bucket[arr[i]]++;

    int i = 0;
    for (int j = 0; j < bucket.size(); j++) {
      while (bucket[j]-- > 0) arr[i++] = j;
    }
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
    solution.counting_sort(arr1);
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