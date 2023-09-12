#include <iostream>
#include <queue>
using namespace std;

struct Node {
  int value;
  Node *left, *right;

  Node(int value) : value(value){};
};

class Solution {
 private:
  struct Info {
    bool isFull, isCBT;
    int height;
    Info(bool full, bool cbt, int h) : isFull(full), isCBT(cbt), height(h){};
  };

  Info *process(Node *x) {
    if (x == nullptr) return new Info(true, true, 0);
    Info *lInfo = process(x->left);
    Info *rInfo = process(x->right);

    int height = max(lInfo->height, rInfo->height) + 1;
    bool isFull =
        lInfo->isFull && rInfo->isFull && lInfo->height == rInfo->height;
    bool isCBT = false;
    if (isFull)
      isCBT = true;
    else {
      if (lInfo->isCBT && rInfo->isCBT) {
        if (lInfo->isCBT && rInfo->isFull && lInfo->height == rInfo->height + 1)
          isCBT = true;
        if (lInfo->isFull && rInfo->isFull &&
            lInfo->height == rInfo->height + 1)
          isCBT = true;
        if (lInfo->isFull && rInfo->isCBT && lInfo->height == rInfo->height)
          isCBT = true;
      }
    }
    return new Info(isFull, isCBT, height);
  }

 public:
  bool isCBT1(Node *head) {
    if (head == nullptr) return true;
    queue<Node *> q;
    bool leaf = false;
    Node *l = nullptr, *r = nullptr;
    q.push(head);
    while (!q.empty()) {
      head = q.front();
      q.pop();

      l = head->left, r = head->right;
      if ((leaf && (l != nullptr || r != nullptr)) ||
          (l == nullptr && r != nullptr))
        return false;

      if (l != nullptr) q.push(l);
      if (r != nullptr) q.push(r);

      if (l == nullptr || r == nullptr) leaf = true;
    }
    return true;
  }

  bool isCBT2(Node *head) {
    if (head == nullptr) return true;
    return process(head)->isCBT;
  };

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
  int testTimes = 1000000;
  for (int i = 0; i < testTimes; i++) {
    Node *head = solution.generateRandomBST(maxLevel, maxValue);
    if (solution.isCBT1(head) != solution.isCBT2(head)) {
      cout << "Oops!" << endl;
    }
  }
  cout << "finish!" << endl;
  return 0;
}