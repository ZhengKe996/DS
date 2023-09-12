#include <iostream>
#include <unordered_map>
#include <unordered_set>
using namespace std;

struct Node {
  int value;
  Node *left, *right;

  Node(int data) : value(data){};
};

class Solution {
 private:
  struct Info {
    int maxDistance, height;
    Info(int m, int h) : maxDistance(m), height(h){};
  };
  Info *process(Node *x) {
    if (x == nullptr) return new Info(0, 0);
    Info *lInfo = process(x->left);
    Info *rInfo = process(x->right);
    int height = max(lInfo->height, rInfo->height) + 1;
    int p1 = lInfo->maxDistance;
    int p2 = rInfo->maxDistance;
    int p3 = lInfo->height + rInfo->height + 1;
    int maxDistance = max(max(p1, p2), p3);
    return new Info(maxDistance, height);
  }

 public:
  int maxDistance(Node *head) { return process(head)->maxDistance; }

  // ============== TEST ==============
  Node *generateRandomBST(int maxLevel, int maxValue) {
    return generate(1, maxLevel, maxValue);
  };
  Node *generate(int level, int maxLevel, int maxValue) {
    if (level > maxLevel || rand() < 0.5) {
      return nullptr;
    }
    Node *head = new Node((int)(rand() % maxValue));
    head->left = generate(level + 1, maxLevel, maxValue);
    head->right = generate(level + 1, maxLevel, maxValue);
    return head;
  }
};

int main() {
  Solution solution;
  int maxLevel = 5;
  int maxValue = 100;
  int testTimes = 1;
  for (int i = 0; i < testTimes; i++) {
    Node *head = solution.generateRandomBST(maxLevel, maxValue);
    solution.maxDistance(head);
  }
  cout << "finish!" << endl;
  return 0;
};