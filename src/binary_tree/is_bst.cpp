#include <iostream>

using namespace std;

struct Node {
  int value;
  Node *left, *right;
  Node(int value) : value(value){};
};

class Solution {
 private:
  void in(Node *head, vector<Node *> &arr) {
    if (head == nullptr) return;
    in(head->left, arr);
    arr.push_back(head);
    in(head->right, arr);
  }

  struct Info {
    bool isBST;
    int max, min;
    Info(bool bst, int max, int min) : isBST(bst), max(max), min(min){};
  };

  Info *process(Node *x) {
    if (x == nullptr) return nullptr;
    Info *lInfo = process(x->left);
    Info *rInfo = process(x->right);

    int ma = x->value, mi = x->value;
    if (lInfo != nullptr) {
      ma = max(ma, lInfo->max);
      mi = min(mi, lInfo->min);
    }
    if (rInfo != nullptr) {
      ma = max(ma, rInfo->max);
      mi = min(mi, rInfo->min);
    }

    bool isBST = true;
    if (lInfo != nullptr && !lInfo->isBST) isBST = false;
    if (rInfo != nullptr && !rInfo->isBST) isBST = false;

    if (lInfo != nullptr && lInfo->max >= x->value) isBST = false;
    if (rInfo != nullptr && rInfo->min <= x->value) isBST = false;
    return new Info(isBST, ma, mi);
  }

 public:
  bool isBST1(Node *head) {
    if (head == nullptr) return true;
    vector<Node *> arr;
    in(head, arr);
    for (int i = 1; i < arr.size(); i++) {
      if (arr[i]->value <= arr[i - 1]->value) return false;
    }
    return true;
  }

  bool isBST2(Node *head) {
    if (head == nullptr) return true;
    return process(head)->isBST;
  }

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
  int maxLevel = 4;
  int maxValue = 100;
  int testTimes = 1000000;
  for (int i = 0; i < testTimes; i++) {
    Node *head = solution.generateRandomBST(maxLevel, maxValue);
    if (solution.isBST1(head) != solution.isBST2(head)) {
      cout << "Oops!" << endl;
    }
  }
  cout << "finish!" << endl;
  return 0;
}