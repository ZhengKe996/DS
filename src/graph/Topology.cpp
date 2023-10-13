#include <iostream>
#include <queue>
#include <unordered_map>

using namespace std;

class Solution {
 private:
  struct DirectedGraphNode {
    int label;
    vector<DirectedGraphNode *> neighbors;
    DirectedGraphNode(int x) : label(x){};
  };

 public:
  vector<DirectedGraphNode *> topSort(vector<DirectedGraphNode *> graph) {
    vector<DirectedGraphNode *> ans;
    // in记录节点的入度
    unordered_map<DirectedGraphNode *, int> in;
    queue<DirectedGraphNode *> que;
    for (auto e : graph) {
      for (auto i : e->neighbors) {
        in[i]++;
      }
    }
    for (auto e : graph) {
      if (in[e] == 0) {
        que.push(e);
      }
    }
    while (!que.empty()) {
      DirectedGraphNode *now = que.front();
      que.pop();
      ans.push_back(now);
      for (auto e : now->neighbors) {
        if (--in[e] == 0) {
          que.push(e);
        }
      }
    }
    return ans;
  }
};