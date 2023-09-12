#include <iostream>

using namespace std;

struct Node {
  int val;
  Node *left, *right;
  Node(int data) : val(data){};
};

class Solution {
 private:
  struct Info {
    int maxBSTSubtreeSize, allSize, max, min;
    Info(int maxBSTSubtreeSize, int allSize, int max, int min)
        : maxBSTSubtreeSize(maxBSTSubtreeSize),
          allSize(allSize),
          max(max),
          min(min){};
  };

  Info *process(Node *x) {
    if (x == nullptr) return nullptr;
    Info *lInfo = process(x->left);
    Info *rInfo = process(x->right);
    int ma = x->val, mi = x->val;
    int allSize = 1;
    if (lInfo != nullptr) {
      ma = max(ma, lInfo->max);
      mi = min(mi, lInfo->min);
      allSize += lInfo->allSize;
    }

    if (rInfo != nullptr) {
      ma = max(ma, rInfo->max);
      mi = min(mi, rInfo->min);
      allSize += rInfo->allSize;
    }

    int p1 = -1;
    if (lInfo != nullptr) {
      p1 = lInfo->maxBSTSubtreeSize;
    }
    int p2 = -1;
    if (rInfo != nullptr) {
      p2 = rInfo->maxBSTSubtreeSize;
    }

    int p3 = -1;
    bool leftBST =
        (lInfo == nullptr ? true
                          : (lInfo->maxBSTSubtreeSize == lInfo->allSize));
    bool rightBST =
        (rInfo == nullptr ? true
                          : (rInfo->maxBSTSubtreeSize == rInfo->allSize));

    if (leftBST && rightBST) {
      bool leftMaxLessX = (lInfo == nullptr ? true : (lInfo->max < x->val));
      bool rightMinMoreX = (rInfo == nullptr ? true : (x->val < rInfo->min));
      if (leftMaxLessX && rightMinMoreX) {
        int leftSize = lInfo == nullptr ? 0 : lInfo->allSize;
        int rightSize = rInfo == nullptr ? 0 : rInfo->allSize;
        p3 = leftSize + rightSize + 1;
      }
    }
    return new Info(max(p1, max(p2, p3)), allSize, ma, mi);
  }

 public:
  int largestBSTSubtree(Node *head) {
    if (head == nullptr) return 0;
    return process(head)->maxBSTSubtreeSize;
  }

  // ============ TEST ============
  int right(Node *head) {
    if (head == nullptr) return 0;

    int h = getBSTSize(head);

    if (h != 0) {
      return h;
    }
    return max(right(head->left), right(head->right));
  }

  int getBSTSize(Node *head) {
    if (head == nullptr) return 0;
    vector<Node *> arr;
    in(head, arr);
    for (int i = 1; i < arr.size(); i++) {
      if (arr[i]->val <= arr[i - 1]->val) {
        return 0;
      }
    }
    return arr.size();
  }
  void in(Node *head, vector<Node *> &arr) {
    if (head == nullptr) {
      return;
    }
    in(head->left, arr);
    arr.push_back(head);
    in(head->right, arr);
  }

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
    if (solution.right(head) != solution.largestBSTSubtree(head)) {
      cout << "Oops!" << endl;
    }
  }
  cout << "finish!" << endl;
  return 0;
}