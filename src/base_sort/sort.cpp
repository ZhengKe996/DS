#include <cstdlib>
#include <iostream>

using namespace std;

class Solution {
 public:
  void insert_sort(vector<int> &arr) {
    if (arr.size() < 2) return;
    for (int i = 1; i < arr.size(); i++) {
      for (int j = i - 1; j >= 0; j--) {
        if (arr[j] > arr[j + 1]) {
          swap(arr[j], arr[j + 1]);
        }
      }
    }
  };

  void bubble_sort(vector<int> &arr) {
    if (arr.size() < 2) return;
    for (int i = 0; i < arr.size(); i++) {
      for (int j = 0; j < i; j++) {
        if (arr[i] < arr[j]) {
          swap(arr[i], arr[j]);
        }
      }
    }
  }

  void select_sort(vector<int> &arr) {
    if (arr.size() < 2) return;
    for (int i = 0; i < arr.size(); i++) {
      for (int j = i + 1; j < arr.size(); j++) {
        if (arr[i] > arr[j]) {
          swap(arr[i], arr[j]);
        }
      }
    }
  }

  void quick_sort(vector<int> &arr) { quick_sort(arr, 0, arr.size() - 1); }

  void quick_sort(vector<int> &arr, int l, int r) {
    if (l >= r) return;
    int i = l - 1, j = r + 1, mid = arr[l + ((r - l) >> 1)];
    while (i < j) {
      while (arr[++i] < mid)
        ;
      while (arr[--j] > mid)
        ;
      if (i < j) swap(arr[i], arr[j]);
    }
    quick_sort(arr, l, j);
    quick_sort(arr, j + 1, r);
  }

  // TEST
  vector<int> generateRandomArray(int maxSize, int maxValue) {
    vector<int> arr(rand() % (maxSize + 1), 0);
    for (int i = 0; i < arr.size(); i++) {
      arr[i] = (rand() % (maxSize + 1)) - (rand() % (maxSize));
    }

    return arr;
  }

  vector<int> copyArray(vector<int> &arr) {
    if (arr.size() == 0) return {};
    vector<int> out(arr.size(), 0);
    for (int i = 0; i < arr.size(); i++) {
      out[i] = arr[i];
    }
    return out;
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

  void printArray(vector<int> &arr) {
    if (arr.size() == 0) return;
    for (int i = 0; i < arr.size(); i++) {
      cout << arr[i] << " ";
    }
    cout << endl;
  }
};

int main() {
  Solution s;

  int testTime = 100;
  int maxSize = 100;
  int maxValue = 100;
  bool succeed = true;
  for (int i = 0; i < testTime; i++) {
    vector<int> arr1 = s.generateRandomArray(maxSize, maxValue);
    vector<int> arr2 = s.copyArray(arr1);
    vector<int> arr3 = s.copyArray(arr1);
    vector<int> arr4 = s.copyArray(arr1);
    s.bubble_sort(arr1);
    s.select_sort(arr2);
    s.insert_sort(arr3);
    s.quick_sort(arr4);

    if (!s.isEqual(arr1, arr2)) {
      cout << "arr1, arr2:" << endl;
      succeed = false;
      s.printArray(arr1);
      s.printArray(arr2);
      break;
    }

    if (!s.isEqual(arr2, arr3)) {
      cout << "arr2, arr3:" << endl;
      succeed = false;
      s.printArray(arr2);
      s.printArray(arr3);
      break;
    }

    if (!s.isEqual(arr3, arr4)) {
      cout << "arr3, arr4:" << endl;
      succeed = false;
      s.printArray(arr3);
      s.printArray(arr4);
      break;
    }
  }
  cout << (succeed ? "Nice!" : "Fucking fucked!") << endl;
  return 0;
}
