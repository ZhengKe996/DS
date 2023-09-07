#include <iostream>

using namespace std;

struct Node {
  int value;
  Node *left, *right;
  Node(int value) : value(value){};
};

class Solution {
 public:
  // 二叉树前序遍历
  void pre(Node *head) {
    if (head == nullptr) return;
    cout << head->value << " ";
    pre(head->left);
    pre(head->right);
  }

  // 二叉树中序遍历
  void in(Node *head) {
    if (head == nullptr) return;
    in(head->left);
    cout << head->value << " ";
    in(head->right);
  }

  // 二叉树后序遍历
  void pos(Node *head) {
    if (head == nullptr) return;

    pos(head->left);
    pos(head->right);
    cout << head->value << " ";
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

  solution.pre(head);
  cout << endl << "=============" << endl;

  solution.in(head);
  cout << endl << "=============" << endl;

  solution.pos(head);
  cout << endl << "=============" << endl;
  return 0;
}