#include <iostream>

using namespace std;

class Solution {
 private:
  void swap(vector<int>& arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

 public:
  void shell_sort(vector<int>& arr) {
    if (arr.empty() || arr.size() < 2) return;

    const vector<int> step = {5, 2, 1};
    for (int s = 0; s < step.size(); s++) {
      for (int i = step[s]; i < arr.size(); i++) {
        for (int j = i - step[s]; j >= 0 && arr[j] > arr[j + step[s]];
             j -= step[s]) {
          swap(arr, j, j + step[s]);
        }
      }
    }
  }

  // ==================== TEST ====================
  void insertionSort(vector<int>& arr) {
    if (arr.empty() || arr.size() < 2) return;

    // 不只1个数
    for (int i = 1; i < arr.size(); i++) {  // 0 ~ i 做到有序
      for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
        swap(arr, j, j + 1);
      }
    }
  }

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

    solution.shell_sort(arr1);
    solution.insertionSort(arr2);

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