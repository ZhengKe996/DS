#include <iostream>
using namespace std;

struct Node {
  int value;
  vector<Node *> children;
  Node(){};
  Node(int value) : value(value){};
  Node(int value, vector<Node *> &children)
      : value(value), children(children){};
};

struct TreeNode {
  int value;
  TreeNode *left, *right;
  TreeNode(int x) : value(x){};
};

// N叉树编码为二叉树
class Codec {
 public:
  TreeNode *encode(Node *root) {
    if (root == nullptr) return nullptr;
    TreeNode *head = new TreeNode(root->value);
    head->left = en(root->children);
    return head;
  };
  Node *decode(TreeNode *root) {
    if (root == nullptr) return nullptr;

    vector<Node *> childrens = de(root->left);
    return new Node(root->value, childrens);
  }

 private:
  TreeNode *en(vector<Node *> &children) {
    TreeNode *head = nullptr, *cur = nullptr;

    for (Node *child : children) {
      TreeNode *tNode = new TreeNode(child->value);
      if (head == nullptr)
        head = tNode;
      else
        cur->right = tNode;
      cur = tNode;
      cur->left = en(child->children);
    }
    return head;
  }

  vector<Node *> de(TreeNode *root) {
    vector<Node *> children;
    while (root != nullptr) {
      vector<Node *> cur_child = de(root->left);
      Node *cur = new Node(root->value, cur_child);
      children.push_back(cur);
      root = root->right;
    }
    return children;
  }
};
