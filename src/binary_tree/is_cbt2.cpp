#include <iostream>
#include <queue>

using namespace std;

struct TreeNode {
  int val;
  TreeNode *left, *right;
  TreeNode(int value) : val(value){};
};

class Solution {
 private:
  // 二叉树递归套路实现;
  struct Info {
    bool isFull, isCBT;
    int height;
    Info(bool full, bool cbt, int h) : isFull(full), isCBT(cbt), height(h){};
  };

  Info *process(TreeNode *x) {
    if (x == nullptr) return new Info(true, true, 0);

    Info *lInfo = process(x->left);
    Info *rInfo = process(x->right);
    int height = max(lInfo->height, rInfo->height) + 1;

    bool isFull =
        lInfo->isFull && rInfo->isFull && lInfo->height == rInfo->height;
    bool isCBT = false;

    if (lInfo->isFull && rInfo->isFull && lInfo->height == rInfo->height)
      isCBT = true;
    else if (lInfo->isCBT && rInfo->isFull &&
             lInfo->height == rInfo->height + 1)
      isCBT = true;
    else if (lInfo->isFull && rInfo->isFull &&
             lInfo->height == rInfo->height + 1)
      isCBT = true;
    else if (lInfo->isFull && rInfo->isCBT && lInfo->height == rInfo->height)
      isCBT = true;

    return new Info(isFull, isCBT, height);
  };

 public:
  bool isCompleteTree1(TreeNode *head) {
    if (head == nullptr) return true;

    queue<TreeNode *> q;
    bool leaf = false;
    TreeNode *l = nullptr, *r = nullptr;
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

  bool isCompleteTree2(TreeNode *head) { return process(head)->isCBT; }

  TreeNode *generateRandomBST(int maxLevel, int maxValue) {
    return generate(1, maxLevel, maxValue);
  }
  TreeNode *generate(int level, int maxLevel, int maxValue) {
    if (level > maxLevel || random() < 0.5) {
      return nullptr;
    }
    TreeNode *head = new TreeNode((int)(random() % maxValue));
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
    TreeNode *head = solution.generateRandomBST(maxLevel, maxValue);
    if (solution.isCompleteTree1(head) != solution.isCompleteTree2(head)) {
      cout << "Oops!" << endl;
    }
  }
  cout << "finish!" << endl;
}
