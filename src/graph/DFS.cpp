#include <iostream>
#include <stack>
#include <unordered_set>

using namespace std;
struct Node {
 public:
  int value, in, out;
  vector<Node*> nexts, edges;

  Node(int value) : value(value) {
    this->in = 0;
    this->out = 0;
  }
};

class Solution {
 public:
  void dfs(Node* node) {
    if (node == nullptr) return;
    stack<Node*> s;
    unordered_set<Node*> set;
    s.push(node);
    set.insert(node);
    cout << (node->value) << endl;
    while (!s.empty()) {
      Node* cur = s.top();
      s.pop();
      for (Node* next : cur->nexts) {
        if (set.find(next) == set.end()) {
          s.push(cur);
          s.push(next);
          set.insert(next);
          cout << (next->value) << endl;
          break;
        }
      }
    }
  }
};