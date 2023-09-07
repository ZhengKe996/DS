#include <iostream>
#include <queue>

using namespace std;
struct Node {
  int value;
  Node *left, *right;
  Node(int value) : value(value){};
};

class Solution {
 public:
  // 层序遍历
  void level(Node *head) {
    if (head == nullptr) return;
    queue<Node *> q;
    q.push(head);
    while (!q.empty()) {
      Node *cur = q.front();
      q.pop();

      cout << cur->value << " ";
      if (cur->left != nullptr) q.push(cur->left);
      if (cur->right != nullptr) q.push(cur->right);
    }
  }
};

int main() {
  Solution solution;
  Node *head = new Node(1);
  head->left = new Node(2);
  head->right = new Node(3);
  head->left->left = new Node(4);
  head->left->right = new Node(5);
  head->right->left = new Node(6);
  head->right->right = new Node(7);

  solution.level(head);
  return 0;
}