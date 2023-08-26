#include <iostream>

using namespace std;

struct MyMaxHeap {
 private:
  vector<int> heap;
  int limit, size;

  void insert(vector<int>& arr, int index) {
    while (arr[index] > arr[(index - 1) / 2]) {
      swap(arr, index, (index - 1) / 2);
      index = (index - 1) / 2;
    }
  }
  void swap(vector<int>& arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
  void heapify(vector<int>& arr, int index, int size) {
    int left = index * 2 + 1;
    while (left < size) {
      int largest = left + 1 < size && arr[left + 1] > arr[left]
                        ? left + 1
                        : left;  // 比较左右孩子的下标，赋值给largest
      largest = arr[largest] > arr[index] ? largest : index;
      if (largest == index) break;

      swap(arr, largest, index);
      index = largest;
      left = index * 2 + 1;
    }
  }

 public:
  MyMaxHeap(int limit) {
    heap = vector<int>(limit, 0);
    this->limit = limit;
    size = 0;
  };

  bool empty() { return size == 0; }
  bool full() { return size == limit; }
  void push(int value) {
    if (size == limit) cout << "Heap is full" << endl;
    heap[size] = value;
    insert(heap, size++);
  }
  int pop() {
    int ans = heap[0];
    swap(heap, 0, --size);
    heapify(heap, 0, size);
    return ans;
  }
};

struct RightMaxHeap {
 private:
  vector<int> heap;
  int limit, size;

 public:
  RightMaxHeap(int limit) {
    heap = vector<int>(limit, 0);
    this->limit = limit;
    size = 0;
  };

  bool empty() { return size == 0; }
  bool full() { return size == limit; }
  void push(int value) {
    if (size == limit) cout << "Heap is full" << endl;
    heap[size++] = value;
  }
  int pop() {
    int maxIndex = 0;
    for (int i = 1; i < size; i++) {
      if (heap[i] > heap[maxIndex]) {
        maxIndex = i;
      }
    }
    int ans = heap[maxIndex];
    heap[maxIndex] = heap[--size];
    return ans;
  }
};

int main() {
  int value = 1000, limit = 100, testTimes = 1000000;
  for (int i = 0; i < testTimes; i++) {
    int curLimit = (int)(rand() % limit) + 1;
    MyMaxHeap* my = new MyMaxHeap(curLimit);
    RightMaxHeap* test = new RightMaxHeap(curLimit);
    int curOpTimes = (int)(rand() % limit);
    for (int j = 0; j < curOpTimes; j++) {
      if (my->empty() != test->empty()) {
        cout << "Oops" << endl;
      }
      if (my->full() != test->full()) {
        cout << "Oops" << endl;
      }
      if (my->empty()) {
        int curValue = (int)(rand() % value);
        my->push(curValue);
        test->push(curValue);
      } else if (my->full()) {
        if (my->pop() != test->pop()) {
          cout << "Oops" << endl;
        }
      } else {
        if (rand() < 0.5) {
          int curValue = (int)(rand() % value);
          my->push(curValue);
          test->push(curValue);
        } else {
          if (my->pop() != test->pop()) {
            cout << "Oops" << endl;
          }
        }
      }
    }
  }
  cout << "finish!" << endl;
  return 0;
}