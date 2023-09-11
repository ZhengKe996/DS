#include <iostream>

using namespace std;

struct Node {
  int value;
  Node *left, *right;
  Node(int value) : value(value){};
};

class Solution {
 private:
  void printInOrder(Node *head, int height, string to, int len) {
    if (head == nullptr) return;
    printInOrder(head->right, height + 1, "v", len);
    string val = to + to_string(head->value) + to;
    int lenM = val.length(), lenL = (len - lenM) / 2, lenR = len - lenM - lenL;

    val = getSpace(lenL) + val + getSpace(lenR);
    cout << (getSpace(height * len) + val) << endl;
    printInOrder(head->left, height + 1, "^", len);
  }
  string getSpace(int num) {
    string space = " ";
    for (int i = 0; i < num; i++) space.append(space);
    return space;
  }

 public:
  void printTree(Node *head) {
    cout << "Binary Tree:" << endl;
    printInOrder(head, 0, "H", 2);
    cout << endl;
  }
};

int main() {
  Solution solution;
  Node *head = new Node(1);
  head->left = new Node(-222222222);
  head->right = new Node(3);
  head->left->left = new Node(INT32_MIN);
  head->right->left = new Node(55555555);
  head->right->right = new Node(66);
  head->left->left->right = new Node(777);
  solution.printTree(head);

  head = new Node(1);
  head->left = new Node(2);
  head->right = new Node(3);
  head->left->left = new Node(4);
  head->right->left = new Node(5);
  head->right->right = new Node(6);
  head->left->left->right = new Node(7);
  solution.printTree(head);

  head = new Node(1);
  head->left = new Node(1);
  head->right = new Node(1);
  head->left->left = new Node(1);
  head->right->left = new Node(1);
  head->right->right = new Node(1);
  head->left->left->right = new Node(1);
  solution.printTree(head);
  return 0;
}