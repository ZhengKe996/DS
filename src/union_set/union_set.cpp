#include <iostream>
#include <stack>
#include <unordered_map>
using namespace std;

struct Node {
  int value;
  Node(int v) : value(v){};
};

struct UnionSet {
 private:
  unordered_map<int, Node*> nodes;
  unordered_map<Node*, Node*> parents;
  unordered_map<Node*, int> sizeMap;

 public:
  UnionSet(vector<int>& values) {
    nodes = unordered_map<int, Node*>();
    parents = unordered_map<Node*, Node*>();
    sizeMap = unordered_map<Node*, int>();

    for (int cur : values) {
      Node* node = new Node(cur);
      nodes[cur] = node;
      parents[node] = node;
      sizeMap[node] = 1;
    }
  }

  Node* find(Node* cur) {
    stack<Node*> path;
    while (cur != parents[cur]) {
      path.push(cur);
      cur = parents[cur];
    }
    while (!path.empty()) {
      parents[path.top()] = cur;
      path.pop();
    }
    return cur;
  };

  bool isSameSet(int a, int b) { return find(nodes[a]) == find(nodes[b]); }

  void uni(int a, int b) {
    Node* aHead = find(nodes[a]);
    Node* bHead = find(nodes[b]);
    if (aHead != bHead) {
      int aSetSize = sizeMap[aHead];
      int bSetSize = sizeMap[bHead];
      Node* big = aSetSize >= bSetSize ? aHead : bHead;
      Node* small = big == aHead ? bHead : aHead;
      parents[small] = big;
      sizeMap[big] = (aSetSize + bSetSize);
      sizeMap.erase(small);
    }
  };

  int sets() { return sizeMap.size(); }
};