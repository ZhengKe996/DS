#include <iostream>
#include <stack>

using namespace std;

class Solution {
 public:
  void reverse(stack<int>& s) {
    if (s.empty()) return;
    int i = f(s);
    reverse(s);
    s.push(i);
  };

 private:
  int f(stack<int>& s) {
    int result = s.top();
    s.pop();
    if (s.empty())
      return result;
    else {
      int last = f(s);
      s.push(result);
      return last;
    }
  }
};

int main() {
  Solution s;
  stack<int> test;
  test.push(1);
  test.push(2);
  test.push(3);
  test.push(4);
  test.push(5);
  s.reverse(test);

  while (!test.empty()) {
    cout << test.top() << endl;
    test.pop();
  }

  return 0;
}