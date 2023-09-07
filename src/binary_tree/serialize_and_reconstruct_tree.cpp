#include <iostream>
#include <queue>
#include <stack>

using namespace std;
struct Node {
  int value;
  Node *left, *right;
  Node(int value) : value(value){};
};
class Solution {
 public:
  // 前序序列化
  queue<string> preSerial(Node *head) {
    queue<string> ans;
    pres(head, ans);
    return ans;
  }

  void pres(Node *head, queue<string> &ans) {
    if (head == nullptr)
      ans.push("#");
    else {
      ans.push(to_string(head->value));
      pres(head->left, ans);
      pres(head->right, ans);
    }
  }

  // 中序序列化
  queue<string> inSerial(Node *head) {
    queue<string> ans;

    ins(head, ans);
    return ans;
  }

  void ins(Node *head, queue<string> &ans) {
    if (head == nullptr)
      ans.push("#");
    else {
      ins(head->left, ans);
      ans.push(to_string(head->value));
      ins(head->right, ans);
    }
  }

  // 后序序列化
  queue<string> posSerial(Node *head) {
    queue<string> ans;
    poss(head, ans);
    return ans;
  }

  void poss(Node *head, queue<string> &ans) {
    if (head == nullptr)
      ans.push("#");
    else {
      ins(head->left, ans);
      ins(head->right, ans);
      ans.push(to_string(head->value));
    }
  }

  // 前序 反序列化~~~~
  Node *buildByPreQueue(queue<string> &list) {
    if (list.empty() || list.size() == 0) return nullptr;

    return preb(list);
  }
  Node *preb(queue<string> list) {
    string value = list.front();
    list.pop();
    if (value == "#") return nullptr;
    Node *head = new Node(stoi(value));
    head->left = preb(list);
    head->right = preb(list);
    return head;
  }

  // 后序 反序列化~~~~
  Node *buildByPosQueue(queue<string> &list) {
    if (list.empty() || list.size() == 0) return nullptr;
    stack<string> s;
    while (!list.empty()) {
      s.push(list.front());
      list.pop();
    }
    return posb(s);
  }

  Node *posb(stack<string> &s) {
    string value = s.top();
    s.pop();
    if (value == "#") return nullptr;
    Node *head = new Node(stoi(value));
    head->right = posb(s);
    head->left = posb(s);
    return head;
  }

  // 层序遍历 序列化
  queue<string> levelSerial(Node *head) {
    queue<string> ans;
    if (head == nullptr)
      ans.push("#");
    else {
      ans.push(to_string(head->value));
      queue<Node *> q;
      q.push(head);
      while (!q.empty()) {
        head = q.front();
        q.pop();
        if (head->left != nullptr) {
          ans.push(to_string(head->left->value));
          q.push(head->left);
        } else {
          ans.push("#");
        }

        if (head->right != nullptr) {
          ans.push(to_string(head->right->value));
          q.push(head->right);
        } else {
          ans.push("#");
        }
      }
    }
    return ans;
  }

  // 层序遍历 反序列化
  Node *buildByLevelQueue(queue<string> &list) {
    if (list.empty() || list.size() == 0) return nullptr;
    Node *head = generateNode(list.front());
    list.pop();
    queue<Node *> q;
    if (head != nullptr) q.push(head);

    Node *node = nullptr;
    while (!q.empty()) {
      node = q.front();
      q.pop();
      node->left = generateNode(list.front());
      list.pop();
      node->right = generateNode(list.front());
      list.pop();

      if (node->left != nullptr) q.push(node->left);
      if (node->right != nullptr) q.push(node->right);
    }
    return head;
  }
  Node *generateNode(string val) {
    if (val == "#") return nullptr;
    return new Node(stoi(val));
  }

  // =============== TEST ===============
  Node *generateRandomBST(int maxLevel, int maxValue) {
    return generate(1, maxLevel, maxValue);
  }

  Node *generate(int level, int maxLevel, int maxValue) {
    if (level > maxLevel || rand() < 0.5) {
      return nullptr;
    }
    Node *head = new Node((int)(rand() * maxValue));
    head->left = generate(level + 1, maxLevel, maxValue);
    head->right = generate(level + 1, maxLevel, maxValue);
    return head;
  }

  bool isSameValueStructure(Node *head1, Node *head2) {
    if (head1 == nullptr && head2 != nullptr) {
      return false;
    }
    if (head1 != nullptr && head2 == nullptr) {
      return false;
    }
    if (head1 == nullptr && head2 == nullptr) {
      return true;
    }
    if (head1->value != head2->value) {
      return false;
    }
    return isSameValueStructure(head1->left, head2->left) &&
           isSameValueStructure(head1->right, head2->right);
  }
  void printTree(Node *head) {
    cout << "Binary Tree: ";
    printInOrder(head, 0, "H", 17);
    cout << endl;
  }

  void printInOrder(Node *head, int height, string to, int len) {
    if (head == nullptr) {
      return;
    }
    printInOrder(head->right, height + 1, "v", len);
    string val = to + to_string(head->value) + to;
    int lenM = val.length();
    int lenL = (len - lenM) / 2;
    int lenR = len - lenM - lenL;
    val = getSpace(lenL) + val + getSpace(lenR);
    cout << (getSpace(height * len) + val) << endl;
    printInOrder(head->left, height + 1, "^", len);
  }
  string getSpace(int num) {
    string space = " ";

    for (int i = 0; i < num; i++) {
      space.append(space);
    }
    return space;
  }
};

int main() {
  Solution solution;
  int maxLevel = 5, maxValue = 100, testTimes = 1;
  cout << "TEST Begin" << endl;
  for (int i = 0; i < testTimes; i++) {
    Node *head = solution.generateRandomBST(maxLevel, maxValue);
    queue<string> pre = solution.preSerial(head);
    queue<string> pos = solution.posSerial(head);
    queue<string> level = solution.levelSerial(head);
    Node *preBuild = solution.buildByPreQueue(pre);
    Node *posBuild = solution.buildByPosQueue(pos);
    Node *levelBuild = solution.buildByLevelQueue(level);

    if (!solution.isSameValueStructure(preBuild, posBuild) ||
        !solution.isSameValueStructure(posBuild, levelBuild)) {
      cout << "Oops!" << endl;
    }
  }

  cout << "TEST Finish" << endl;

  return 0;
}