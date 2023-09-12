#include <iostream>

using namespace std;

struct Node {
  int value;
  Node *left, *right;
  Node(int data) : value(data){};
};

class Solution {
 private:
  struct Info1 {
    int height, nodes;
    Info1(int h, int n) : height(h), nodes(n){};
  };

  Info1* process1(Node* head) {
    if (head == nullptr) return new Info1(0, 0);
    Info1* lInfo = process1(head->left);
    Info1* rInfo = process1(head->right);

    int height = max(lInfo->height, rInfo->height) + 1;
    int nodes = lInfo->nodes + rInfo->nodes + 1;
    return new Info1(height, nodes);
  }

  struct Info2 {
    bool isFull;
    int height;
    Info2(bool f, int h) : isFull(f), height(h){};
  };

  Info2* process2(Node* head) {
    if (head == nullptr) return new Info2(true, 0);

    Info2* lInfo = process2(head->left);
    Info2* rInfo = process2(head->right);
    bool isFull =
        lInfo->isFull && rInfo->isFull && lInfo->height == rInfo->height;
    int height = max(lInfo->height, rInfo->height) + 1;
    return new Info2(isFull, height);
  }

 public:
  bool isFull1(Node* head) {
    if (head == nullptr) return true;
    Info1* all = process1(head);
    return (1 << all->height) - 1 == all->nodes;
  }

  bool isFull2(Node* head) {
    if (head == nullptr) return true;
    return process2(head)->isFull;
  }
  // ============== TEST ==============
  Node* generateRandomBST(int maxLevel, int maxValue) {
    return generate(1, maxLevel, maxValue);
  };
  Node* generate(int level, int maxLevel, int maxValue) {
    if (level > maxLevel || rand() < 0.5) {
      return nullptr;
    }
    Node* head = new Node((int)(rand() % maxValue));
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
    Node* head = solution.generateRandomBST(maxLevel, maxValue);
    if (solution.isFull1(head) != solution.isFull2(head)) {
      cout << "Oops!" << endl;
    }
  }
  cout << "finish!" << endl;
  return 0;
}