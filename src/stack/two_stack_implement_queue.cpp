#include <iostream>
#include <stack>

using namespace std;

struct TwoStacksQueue {
 public:
  stack<int> push, pop;
  /**
   * Push栈向Pop倒数据
   * 【注】pop栈不为空 不能操作
   */
  void pushToPop() {
    if (pop.empty()) {
      while (!push.empty()) {
        pop.push(push.top());
        push.pop();
      }
    }
  }

  void add(int newValue) {
    push.push(newValue);
    pushToPop();
  }

  int poll() {
    if (push.empty() && pop.empty()) {
      cout << "Queue is empty!";
      return -1;
    }
    pushToPop();
    int value = pop.top();
    pop.pop();
    return value;
  }

  int peek() {
    if (push.empty() && pop.empty()) {
      cout << "Queue is empty!";
      return -1;
    }
    pushToPop();
    return pop.top();
  }
};

int main() {
  TwoStacksQueue test;

  test.add(1);
  test.add(2);
  test.add(3);
  cout << (test.peek()) << endl;
  cout << (test.poll()) << endl;
  cout << (test.peek()) << endl;
  cout << (test.poll()) << endl;
  cout << (test.peek()) << endl;
  cout << (test.poll()) << endl;
  return 0;
}