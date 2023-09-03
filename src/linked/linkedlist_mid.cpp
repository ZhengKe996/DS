#include <iostream>

using namespace std;
struct Node {
  int value;
  Node* next;
  Node(int value) : value(value){};
};

class Solution {
 public:
  /**
   * 1. 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
   */
  Node* midOrUpMidNode(Node* head) {
    if (head == nullptr || head->next == nullptr || head->next->next == nullptr)
      return head;

    Node *slow = head->next, *fast = head->next->next;
    while (fast->next != nullptr && fast->next->next != nullptr) {
      slow = slow->next, fast = fast->next->next;
    }
    return slow;
  };

  /**
   * 2. 输入链表头节点，奇数长度返回中点，偶数长度返回中下点
   */
  Node* midOrDownMidNode(Node* head) {
    if (head == nullptr || head->next == nullptr) return head;

    Node *slow = head->next, *fast = head->next;
    while (fast->next != nullptr && fast->next->next != nullptr) {
      slow = slow->next, fast = fast->next->next;
    }
    return slow;
  };

  /**
   * 3. 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
   */
  Node* midOrUpMidPreNode(Node* head) {
    if (head == nullptr || head->next == nullptr || head->next->next == nullptr)
      return head;

    Node *slow = head, *fast = head->next->next;
    while (fast->next != nullptr && fast->next->next != nullptr) {
      slow = slow->next, fast = fast->next->next;
    }
    return slow;
  }

  /**
   * 4. 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
   */
  Node* midOrDownMidPreNode(Node* head) {
    if (head == nullptr || head->next == nullptr) return nullptr;
    if (head->next->next == nullptr) return head;

    Node *slow = head, *fast = head->next;
    while (fast->next != nullptr && fast->next->next != nullptr) {
      slow = slow->next, fast = fast->next->next;
    }
    return slow;
  }

  // ==================== TEST ====================
  Node* right1(Node* head) {
    if (head == nullptr) return nullptr;

    Node* cur = head;
    vector<Node*> arr;
    while (cur != nullptr) {
      arr.push_back(cur);
      cur = cur->next;
    }
    return arr[(arr.size() - 1) / 2];
  }

  Node* right2(Node* head) {
    if (head == nullptr) return nullptr;

    Node* cur = head;
    vector<Node*> arr;
    while (cur != nullptr) {
      arr.push_back(cur);
      cur = cur->next;
    }
    return arr[arr.size() / 2];
  }

  Node* right3(Node* head) {
    if (head == nullptr || head->next == nullptr || head->next->next == nullptr)
      return nullptr;

    Node* cur = head;
    vector<Node*> arr;
    while (cur != nullptr) {
      arr.push_back(cur);
      cur = cur->next;
    }
    return arr[(arr.size() - 3) / 2];
  }

  Node* right4(Node* head) {
    if (head == nullptr || head->next == nullptr) return nullptr;
    if (head->next->next == nullptr) return head;
    Node* cur = head;
    vector<Node*> arr;
    while (cur != nullptr) {
      arr.push_back(cur);
      cur = cur->next;
    }
    return arr[(arr.size() - 2) / 2];
  }
};

int main() {
  Solution solution;
  Node* test = nullptr;
  test = new Node(0);
  test->next = new Node(1);
  test->next->next = new Node(2);
  test->next->next->next = new Node(3);
  test->next->next->next->next = new Node(4);
  test->next->next->next->next->next = new Node(5);
  test->next->next->next->next->next->next = new Node(6);
  test->next->next->next->next->next->next->next = new Node(7);
  test->next->next->next->next->next->next->next->next = new Node(8);
  test->next->next->next->next->next->next->next->next->next = new Node(9);

  Node *ans1 = nullptr, *ans2 = nullptr;

  ans1 = solution.midOrUpMidNode(test);
  ans2 = solution.right1(test);

  cout << (ans1 != nullptr ? ans1->value : NULL) << " "
       << (ans2 != nullptr ? ans2->value : NULL) << endl;

  ans1 = solution.midOrDownMidNode(test);
  ans2 = solution.right2(test);

  cout << (ans1 != nullptr ? ans1->value : NULL) << " "
       << (ans2 != nullptr ? ans2->value : NULL) << endl;
  ans1 = solution.midOrUpMidPreNode(test);
  ans2 = solution.right3(test);

  cout << (ans1 != nullptr ? ans1->value : NULL) << " "
       << (ans2 != nullptr ? ans2->value : NULL) << endl;
  ans1 = solution.midOrDownMidPreNode(test);
  ans2 = solution.right4(test);

  cout << (ans1 != nullptr ? ans1->value : NULL) << " "
       << (ans2 != nullptr ? ans2->value : NULL) << endl;
  return 0;
}