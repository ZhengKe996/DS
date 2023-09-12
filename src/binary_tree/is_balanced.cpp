#include <iostream>

using namespace std;

struct Node {
  int value;
  Node *left, *right;
  Node(int data) : value(data){};
};

class Solution {
 private:
  int process1(Node *head, vector<bool> &ans) {
    if (!ans[0] || head == nullptr) return -1;
    int lHeight = process1(head->left, ans);
    int rHeight = process1(head->right, ans);
    if (abs(lHeight - rHeight) > 1) ans[0] = false;
    return max(lHeight, rHeight) + 1;
  }

  struct Info {
    bool isBalanced;
    int height;
    Info(bool i, int h) : isBalanced(i), height(h){};
  };

  Info *process(Node *x) {
    if (x == nullptr) return new Info(true, 0);
    Info *lInfo = process(x->left);
    Info *rInfo = process(x->right);
    int height = max(lInfo->height, rInfo->height) + 1;
    bool isBalanced = true;
    if (!lInfo->isBalanced || !rInfo->isBalanced) isBalanced = false;

    if (abs(lInfo->height - rInfo->height) > 1) isBalanced = false;
    return new Info(isBalanced, height);
  }

 public:
  bool isBalanced1(Node *head) {
    vector<bool> ans(1, false);
    ans[0] = true;
    process1(head, ans);
    return ans[0];
  }

  bool isBalanced2(Node *head) { return process(head)->isBalanced; }

  // ============ TEST ============
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
    if (solution.isBalanced1(head) != solution.isBalanced2(head)) {
      cout << "Oops!" << endl;
    }
  }
  cout << "finish!" << endl;
  return 0;
  return 0;
}