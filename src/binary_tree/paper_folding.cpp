#include <iostream>
using namespace std;

class Solution {
 private:
  void process(int i, int N, bool down) {
    if (i > N) return;
    process(i + 1, N, true);
    cout << (down ? "凹 " : "凸 ");
    process(i + 1, N, false);
  }

 public:
  void printAllFolds(int N) {
    process(1, N, true);
    cout << endl;
  }
};

int main() {
  Solution solution;
  int N = 4;
  solution.printAllFolds(N);
  return 0;
}