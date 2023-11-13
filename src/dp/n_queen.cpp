#include <iostream>

using namespace std;

class Solution {
 private:
  int process1(int index, vector<int>& record, int n) {
    if (index == n) return 1;
    int res = 0;
    // i行的皇后，放哪一列呢？j列，
    for (int j = 0; j < n; j++) {
      if (isValid(record, index, j)) {
        record[index] = j;
        res += process1(index + 1, record, n);
      }
    }
    return res;
  }
  bool isValid(vector<int>& record, int i, int j) {
    for (int k = 0; k < i; k++) {
      if (j == record[k] || abs(record[k] - j) == abs(i - k)) return false;
    }
    return true;
  }

  int process2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {
    if (colLim == limit) return 1;

    int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
    int mostRightOne = 0;
    int res = 0;
    while (pos != 0) {
      mostRightOne = pos & (~pos + 1);
      pos = pos - mostRightOne;
      res += process2(limit, (colLim | mostRightOne),
                      (leftDiaLim | mostRightOne) << 1,
                      (rightDiaLim | mostRightOne) >> 1);
    }
    return res;
  }

 public:
  int num1(int n) {
    if (n < 1) return 0;
    vector<int> record(n, 0);
    return process1(0, record, n);
  }
  int num2(int n) {
    if (n < 1 || n > 32) return 0;

    int limit = n == 32 ? -1 : (1 << n) - 1;

    return process2(limit, 0, 0, 0);
  }
};

int main() {
  Solution slu;
  int n = 14;

  cout << slu.num1(n) << endl;
  cout << slu.num2(n) << endl;
  return 0;
}