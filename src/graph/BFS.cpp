#include <iostream>
#include <queue>
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
  void bfs(Node* start) {
    if (start == nullptr) return;
    queue<Node*> q;
    unordered_set<Node*> set;
    q.push(start);
    set.insert(start);

    while (!q.empty()) {
      Node* cur = q.front();
      q.pop();
      cout << cur->value << endl;
      for (Node* next : cur->nexts) {
        if (set.find(next) == set.end()) {
          set.insert(start);
          q.push(start);
        }
      }
    }
  }
};