#include <iostream>
#include <stack>
using namespace std;
struct Node {
  int value;
  Node *left, *right;
  Node(int value) : value(value){};
};

class Solution {
 public:
  // 前序遍历（非递归）
  void pre(Node* head) {
    cout << "pre-order: ";
    if (head != nullptr) {
      stack<Node*> s;
      s.push(head);
      while (!s.empty()) {
        head = s.top();
        s.pop();

        cout << head->value << " ";
        if (head->right != nullptr) s.push(head->right);
        if (head->left != nullptr) s.push(head->left);
      }
    }
    cout << endl;
  };

  // 中序遍历（非递归）
  void in(Node* head) {
    cout << "in-order: ";
    if (head != nullptr) {
      stack<Node*> s;
      while (!s.empty() || head != nullptr) {
        if (head != nullptr) {
          s.push(head);
          head = head->left;
        } else {
          head = s.top();
          s.pop();
          cout << head->value << " ";
          head = head->right;
        }
      }
    }
    cout << endl;
  };

  // 后序遍历(非递归)
  void pos(Node* head) {
    cout << "pos-order: ";
    if (head != nullptr) {
      stack<Node*> s;
      s.push(head);
      Node* cur = nullptr;
      while (!s.empty()) {
        cur = s.top();
        if (cur->left != nullptr && head != cur->right && head != cur->left)
          s.push(cur->left);
        else if (cur->right != nullptr && head != cur->right)
          s.push(cur->right);
        else {
          cout << s.top()->value << " ";
          s.pop();
          head = cur;
        }
      }
    }
    cout << endl;
  }
};
int main() {
  Solution solution;
  Node* head = new Node(1);
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