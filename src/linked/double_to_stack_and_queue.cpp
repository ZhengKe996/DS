#include <iostream>
#include <queue>
#include <stack>
using namespace std;

struct Node {
  int value;
  Node *next;
  Node *last;
  Node() : value(0), next(nullptr), last(nullptr) {}
  Node(int x) : value(x), next(nullptr), last(nullptr) {}
  Node(int x, Node *next, Node *last) : value(x), next(next), last(last) {}
};

struct DoubleEndsQueue {
  Node *head;
  Node *tail;

  /**
   * 队头插入元素
   */
  void addFromHead(int value) {
    Node *cur = new Node(value);
    if (head == nullptr) {
      head = cur, tail = cur;
    } else {
      cur->next = head;
      head->last = cur;

      head = cur;
    }
  }

  /**
   * 队尾插入元素
   */
  void addFromBottom(int value) {
    Node *cur = new Node(value);
    if (head == nullptr) {
      head = cur, tail = cur;
    } else {
      cur->last = tail;
      tail->next = cur;

      tail = cur;
    }
  }

  /**
   * 队头出队
   */
  int popFromHead() {
    if (head == nullptr) return -1;

    Node *cur = head;
    if (head == tail) {
      head = nullptr, tail = nullptr;
    } else {
      head = head->next;
      head->last = nullptr;
      cur->next = nullptr;
    }
    return cur->value;
  }

  /**
   * 队尾出队
   */
  int popFromBottom() {
    if (head == nullptr) return -1;

    Node *cur = head;
    if (head == tail) {
      head = nullptr, tail = nullptr;
    } else {
      tail = cur->last;
      tail->next = nullptr;
      cur->last = nullptr;
    }
    return cur->value;
  }

  int empty() { return head == nullptr; }
};

struct MyStack {
  DoubleEndsQueue *queue;
  MyStack() { queue = new DoubleEndsQueue(); }

  void push(int value) { queue->addFromHead(value); }

  int pop() { return queue->popFromHead(); }
  bool empty() { return queue->empty(); }
};
struct MyQueue {
  DoubleEndsQueue *queue;
  MyQueue() { queue = new DoubleEndsQueue(); }
  void push(int value) { queue->addFromBottom(value); }
  int poll() { return queue->popFromHead(); }
  bool empty() { return queue->empty(); }
};

class Solution2 {
 public:
  bool isEqual(int o1, int o2) {
    if (o1 == NULL && o2 != NULL) {
      return false;
    }
    if (o1 != NULL && o2 == NULL) {
      return false;
    }
    if (o1 == NULL && o2 == NULL) {
      return true;
    }
    return o1 == o2;
  }
};

int main() {
  Solution2 solution;
  int oneTestDataNum = 100;
  int value = 10000;
  int testTimes = 100000;
  for (int i = 0; i < testTimes; i++) {
    MyStack myStack;
    MyQueue myQueue;
    stack<int> stack;
    queue<int> queue;
    for (int j = 0; j < oneTestDataNum; j++) {
      int nums = (int)(rand() * value);
      if (stack.empty()) {
        myStack.push(nums);
        stack.push(nums);
      } else {
        if (rand() < 0.5) {
          myStack.push(nums);
          stack.push(nums);
        } else {
          int val = stack.top();
          stack.pop();
          int val2 = myStack.pop();
          if (!solution.isEqual(val2, val)) {
            cout << "Oops!" << endl;
          }
        }
      }

      int numq = (int)(rand() * value);
      if (queue.empty()) {
        myQueue.push(numq);
        queue.push(numq);
      } else {
        if (rand() < 0.5) {
          myQueue.push(numq);
          queue.push(numq);
        } else {
          int val = queue.front();
          queue.pop();
          int val2 = myQueue.poll();
          if (!solution.isEqual(val2, val)) {
            cout << "Oops!" << endl;
          }
        }
      }
    }
  }
  cout << "Finish!" << endl;
  return 0;
}