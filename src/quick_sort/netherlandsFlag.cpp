#include <iostream>
#include <queue>
#include <stack>
using namespace std;

struct Op {
  int l, r;
  Op(int l, int r) : l(l), r(r){};
};

class Solution {
 private:
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

  void process(vector<int>& arr, int l, int r) {
    if (l >= r) return;
    swap(arr, l + (int)(rand() % (r - l + 1)), r);
    vector<int> equal_area = netherlandsFlag(arr, l, r);
    process(arr, l, equal_area[0] - 1);
    process(arr, equal_area[1] + 1, r);
  }

 public:
  void quick_sort(vector<int>& arr) {
    if (arr.empty() || arr.size() < 2) return;
    process(arr, 0, arr.size() - 1);
  }

  void quick_sort2(vector<int>& arr) {
    if (arr.empty() || arr.size() < 2) return;
    int n = arr.size();
    swap(arr, (int)(rand() % n), n - 1);
    vector<int> equal_area = netherlandsFlag(arr, 0, n - 1);
    int el = equal_area[0], er = equal_area[1];
    stack<Op*> s;
    s.push(new Op(0, el - 1));
    s.push(new Op(er + 1, n - 1));

    while (!s.empty()) {
      Op* op = s.top();
      s.pop();
      if (op->l < op->r) {
        swap(arr, op->l + (int)(rand() % (op->r - op->l + 1)), op->r);
        equal_area = netherlandsFlag(arr, op->l, op->r);
        el = equal_area[0], er = equal_area[1];
        s.push(new Op(op->l, el - 1));
        s.push(new Op(er + 1, op->r));
      }
    }
  }

  void quick_sort3(vector<int>& arr) {
    if (arr.empty() || arr.size() < 2) return;
    int n = arr.size();
    swap(arr, (int)(rand() % n), n - 1);
    vector<int> equal_area = netherlandsFlag(arr, 0, n - 1);
    int el = equal_area[0], er = equal_area[1];
    queue<Op*> q;
    q.push(new Op(0, el - 1));
    q.push(new Op(er + 1, n - 1));

    while (!q.empty()) {
      Op* op = q.front();
      q.pop();
      if (op->l < op->r) {
        swap(arr, op->l + (int)(rand() % (op->r - op->l + 1)), op->r);
        equal_area = netherlandsFlag(arr, op->l, op->r);
        el = equal_area[0], er = equal_area[1];
        q.push(new Op(op->l, el - 1));
        q.push(new Op(er + 1, op->r));
      }
    }
  }

  // ============= TEST =============
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
  cout << "TEST Begin" << endl;
  for (int i = 0; i < testTime; i++) {
    vector<int> arr1 = solution.generate_random_array(maxSize, maxValue);
    vector<int> arr2 = solution.copy_array(arr1);
    vector<int> arr3 = solution.copy_array(arr1);
    solution.quick_sort(arr1);
    solution.quick_sort2(arr2);
    solution.quick_sort3(arr3);
    if (!solution.is_equal(arr1, arr2) || !solution.is_equal(arr1, arr3)) {
      solution.print_array(arr1);
      solution.print_array(arr2);
      solution.print_array(arr3);
      succeed = false;
      break;
    }
  }
  cout << "TEST END: "
       << "测试" << testTime << "组是否全部通过：" << (succeed ? "是" : "否")
       << endl;
  return 0;
}