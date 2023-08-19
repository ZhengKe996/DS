#include <iostream>
#include <queue>

using namespace std;

struct TwoQueueStack {
  queue<int> q, help;
  void push(int value) { q.push(value); }
  int poll() {
    while (q.size() > 1) {
      help.push(q.front());
      q.pop();
    }
    int ans = q.front();
    q.pop();
    queue<int> temp = q;
    q = help;
    help = temp;
    return ans;
  }

  int peek() {
    while (q.size() > 1) {
      help.push(q.front());
      q.pop();
    }
    int ans = q.front();
    q.pop();
    queue<int> temp = q;
    q = help;
    help = temp;
    return ans;
  }
  bool isEmpty() { return q.empty(); }
};

int main() { return 0; }