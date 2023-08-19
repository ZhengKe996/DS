#include <iostream>
#include <stack>

using namespace std;

struct MyStack1 {
  stack<int> data;
  stack<int> min;

  void push(int value) {
    if (min.empty() || value <= this->getMin()) {
      min.push(value);
    }
    data.push(value);
  }
  int pop() {
    if (data.empty()) {
      cout << "Your stack is empty!";
      return -1;
    }
    int value = data.top();
    data.pop();
    if (value == this->getMin()) {
      min.pop();
    }
    return value;
  }
  int getMin() {
    if (min.empty()) {
      cout << "Your stack is empty!";
      return -1;
    }

    return min.top();
  }
};

struct MyStack2 {
  stack<int> data;
  stack<int> min;

  void push(int value) {
    if (min.empty() || value < getMin()) {
      min.push(value);
    } else {
      min.push(min.top());
    }
    data.push(value);
  }

  int pop() {
    if (data.empty()) {
      cout << "Your stack is empty!";
      return -1;
    }
    min.pop();
    int value = data.top();
    data.pop();
    return value;
  }

  int getMin() {
    if (min.empty()) {
      cout << "Your stack is empty!";
      return -1;
    }

    return min.top();
    ;
  }
};
int main() { return 0; }