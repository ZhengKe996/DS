#include <iostream>
#include <queue>

using namespace std;

class Solution {
 private:
  void insert(vector<int>& arr, int index) {
    while (arr[index] > arr[(index - 1) / 2]) {
      swap(arr, index, (index - 1) / 2);
      index = (index - 1) / 2;
    };
  };
  void swap(vector<int>& arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
  void heapify(vector<int>& arr, int index, int size) {
    int l = index * 2 + 1;
    while (l < size) {
      int largest = l + 1 < size && arr[l + 1] > arr[l] ? l + 1 : l;
      largest = arr[largest] > arr[index] ? largest : index;
      if (largest == index) break;

      swap(arr, largest, index);
      index = largest;
      l = index * 2 + 1;
    }
  }

 public:
  void heap_sort(vector<int>& arr) {
    if (arr.empty() || arr.size() < 2) return;
    for (int i = 0; i < arr.size(); i++) insert(arr, i);
    int heapSize = arr.size();
    swap(arr, 0, --heapSize);
    while (heapSize > 0) {
      heapify(arr, 0, heapSize);
      swap(arr, 0, --heapSize);
    }
  }

  // ============== TEST ==============
  void select_sort(vector<int>& arr) {
    if (arr.size() < 2) return;
    for (int i = 0; i < arr.size(); i++) {
      for (int j = i + 1; j < arr.size(); j++) {
        if (arr[i] > arr[j]) {
          swap(arr, i, j);
        }
      }
    }
  }
  vector<int> generateRandomArray(int maxSize, int maxValue) {
    vector<int> arr(rand() % (maxSize + 1), 0);
    for (int i = 0; i < arr.size(); i++) {
      arr[i] = (rand() % (maxSize + 1)) - (rand() % (maxSize));
    }

    return arr;
  }

  vector<int> copyArray(vector<int>& arr) {
    if (arr.size() == 0) return {};
    vector<int> out(arr.size(), 0);
    for (int i = 0; i < arr.size(); i++) {
      out[i] = arr[i];
    }
    return out;
  }

  bool isEqual(vector<int>& arr1, vector<int>& arr2) {
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

  void printArray(vector<int>& arr) {
    if (arr.size() == 0) return;
    for (int i = 0; i < arr.size(); i++) {
      cout << arr[i] << " ";
    }
    cout << endl;
  }
};

int main() {
  Solution solution;
  // priority_queue<int> heap;
  priority_queue<int, vector<int>, greater<int>> heap;
  heap.push(6);
  heap.push(8);
  heap.push(0);
  heap.push(2);
  heap.push(9);
  heap.push(1);
  while (!heap.empty()) {
    cout << (heap.top()) << endl;
    heap.pop();
  }

  int testTime = 500000, maxSize = 100, maxValue = 100;
  bool succeed = true;
  for (int i = 0; i < testTime; i++) {
    vector<int> arr1 = solution.generateRandomArray(maxSize, maxValue);
    vector<int> arr2 = solution.copyArray(arr1);
    solution.heap_sort(arr1);
    solution.select_sort(arr2);
    if (!solution.isEqual(arr1, arr2)) {
      succeed = false;
      solution.printArray(arr1);
      solution.printArray(arr2);
      break;
    }
  }
  cout << (succeed ? "Nice!" : "Fucking fucked!") << endl;

  return 0;
}