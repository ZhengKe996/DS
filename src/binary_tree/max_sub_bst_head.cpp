#include <iostream>

using namespace std;

struct Node {
  int value;
  Node *left, *right;
  Node(int data) : value(data){};
};

class Solution {
  // 中序遍历 实现;
 private:
  int getBSTSize(Node *head) {
    if (head == nullptr) return 0;
    vector<Node *> arr;
    in(head, arr);
    for (int i = 1; i < arr.size(); i++) {
      if (arr[i]->value <= arr[i - 1]->value) return 0;
    }
  }

  void in(Node *head, vector<Node *> &arr) {
    if (head == nullptr) return;

    in(head->left, arr);
    arr.push_back(head);
    in(head->right, arr);
  }

  struct Info {
    Node *maxSubBSTHead;
    int maxSubBSTSize, min, max;
    Info(Node *h, int size, int mi, int ma)
        : maxSubBSTHead(h), maxSubBSTSize(size), min(mi), max(ma){};
  };

  Info *process(Node *x) {
    if (x == nullptr) return nullptr;
    Info *lInfo = process(x->left);
    Info *rInfo = process(x->right);

    int mi = x->value, ma = x->value;

    Node *maxSubBSTHead = nullptr;
    int maxSubBSTSize = 0;

    if (lInfo != nullptr) {
      mi = min(mi, lInfo->min);
      ma = max(ma, lInfo->max);
      maxSubBSTHead = lInfo->maxSubBSTHead;
      maxSubBSTSize = lInfo->maxSubBSTSize;
    }
    if (rInfo != nullptr) {
      mi = min(mi, rInfo->min);
      ma = max(ma, rInfo->max);
      if (rInfo->maxSubBSTSize > maxSubBSTSize) {
        maxSubBSTHead = rInfo->maxSubBSTHead;
        maxSubBSTSize = rInfo->maxSubBSTSize;
      }
    }

    if ((lInfo == nullptr
             ? true
             : (lInfo->maxSubBSTHead == x->left && lInfo->max < x->value)) &&
        (rInfo == nullptr
             ? true
             : (rInfo->maxSubBSTHead == x->right && rInfo->min > x->value))) {
      maxSubBSTHead = x;
      maxSubBSTSize = (lInfo == nullptr ? 0 : lInfo->maxSubBSTSize) +
                      (rInfo == nullptr ? 0 : rInfo->maxSubBSTSize) + 1;
    }
    return new Info(maxSubBSTHead, maxSubBSTSize, mi, ma);
  }

 public:
  Node *maxSubBSTHead1(Node *head) {
    if (head == nullptr) return nullptr;
    if (getBSTSize(head) != 0) return head;

    Node *lANS = maxSubBSTHead1(head->left);
    Node *rANS = maxSubBSTHead1(head->right);
    return getBSTSize(lANS) >= getBSTSize(rANS) ? lANS : rANS;
  }

  Node *maxSubBSTHead2(Node *head) {
    if (head == nullptr) return nullptr;
  }

  Node *generateRandomBST(int maxLevel, int maxValue) {
    return generate(1, maxLevel, maxValue);
  }
  Node *generate(int level, int maxLevel, int maxValue) {
    if (level > maxLevel || random() < 0.5) {
      return nullptr;
    }
    Node *head = new Node((int)(random() % maxValue));
    head->left = generate(level + 1, maxLevel, maxValue);
    head->right = generate(level + 1, maxLevel, maxValue);
    return head;
  }
};

int main() {
  Solution solution;

  int maxLevel = 4;
  int maxValue = 100;
  int testTimes = 1000000;
  for (int i = 0; i < testTimes; i++) {
    Node *head = solution.generateRandomBST(maxLevel, maxValue);
    if (solution.maxSubBSTHead1(head) != solution.maxSubBSTHead2(head)) {
      cout << "Oops!" << endl;
    }
  }
  cout << "finish!" << endl;
  return 0;
};