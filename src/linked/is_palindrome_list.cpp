#include <iostream>
#include <stack>

using namespace std;
struct Node {
  int value;
  Node* next;
  Node(int value) : value(value){};
};
class Solution {
 public:
  // extra space O(N)
  bool isPalindrome1(Node* head) {
    stack<Node*> s;

    Node* cur = head;
    while (cur != nullptr) {
      s.push(cur);
      cur = cur->next;
    }

    while (head != nullptr) {
      if (head->value != s.top()->value) return false;
      s.pop();

      head = head->next;
    }
    return true;
  };
  // extra space O(N/2)
  bool isPalindrome2(Node* head) {
    if (head == nullptr || head->next == nullptr) return true;
    Node *right = head->next, *cur = head;

    while (cur->next != nullptr && cur->next->next != nullptr) {
      right = right->next;    // --> mid
      cur = cur->next->next;  // --> end
    }
    stack<Node*> s;
    while (right != nullptr) {
      s.push(right);
      right = right->next;
    }

    while (!s.empty()) {
      if (head->value != s.top()->value) return false;
      s.pop();
      head = head->next;
    }
    return true;
  }

  // extra space O(1)
  bool isPalindrome3(Node* head) {
    if (head == nullptr || head->next == nullptr) {
      return true;
    }
    Node *n1 = head, *n2 = head;

    while (n2->next != nullptr && n2->next->next != nullptr) {
      n1 = n1->next;        // n1 -> mid
      n2 = n2->next->next;  // n2 -> end
    }

    n2 = n1->next;       // n2 是 中点右侧的第一个节点
    n1->next = nullptr;  // 中点右侧与原链表断链
    Node* n3 = nullptr;

    while (n2 != nullptr) {  // 反转 中点右侧的所有节点
      n3 = n2->next;         // 暂存 中点右边->next节点
      n2->next = n1;         // 右节点的下一步转换
      n1 = n2;
      n2 = n3;
    }

    n3 = n1;  // n3暂存n1节点；n1节点是 中点右侧翻转过后的头节点
    n2 = head;  // 左侧开始的头节点
    bool res = true;

    while (n1 != nullptr && n2 != nullptr) {
      if (n1->value != n2->value) {
        res = false;
        break;
      }
      n1 = n1->next;
      n2 = n2->next;
    }

    n1 = n3->next;
    n3->next = nullptr;
    while (n1 != nullptr) {  // 将原链表恢复
      n2 = n1->next;
      n1->next = n3;
      n3 = n1;
      n1 = n2;
    }

    return res;
  }

  // =========== TEST ===========

  void printLinkedList(Node* node) {
    cout << "Linked List:";

    while (node != nullptr) {
      cout << node->value << " ";
      node = node->next;
    }
    cout << endl;
  }
};

int main() {
  Solution solution;
  Node* head = nullptr;
  solution.printLinkedList(head);
  cout << solution.isPalindrome1(head) << " | " << solution.isPalindrome2(head)
       << " | " << solution.isPalindrome3(head) << " | " << endl;
  solution.printLinkedList(head);
  cout << "=========================" << endl;

  head = new Node(1);
  solution.printLinkedList(head);
  cout << solution.isPalindrome1(head) << " | " << solution.isPalindrome2(head)
       << " | " << solution.isPalindrome3(head) << " | " << endl;
  solution.printLinkedList(head);
  cout << "=========================" << endl;

  head = new Node(1);
  head->next = new Node(2);
  solution.printLinkedList(head);
  cout << solution.isPalindrome1(head) << " | " << solution.isPalindrome2(head)
       << " | " << solution.isPalindrome3(head) << " | " << endl;
  solution.printLinkedList(head);
  cout << "=========================" << endl;

  head = new Node(1);
  head->next = new Node(1);
  solution.printLinkedList(head);
  cout << solution.isPalindrome1(head) << " | " << solution.isPalindrome2(head)
       << " | " << solution.isPalindrome3(head) << " | " << endl;
  solution.printLinkedList(head);
  cout << "=========================" << endl;

  head = new Node(1);
  head->next = new Node(2);
  head->next->next = new Node(3);
  solution.printLinkedList(head);
  cout << solution.isPalindrome1(head) << " | " << solution.isPalindrome2(head)
       << " | " << solution.isPalindrome3(head) << " | " << endl;
  solution.printLinkedList(head);
  cout << "=========================" << endl;

  head = new Node(1);
  head->next = new Node(2);
  head->next->next = new Node(1);
  solution.printLinkedList(head);
  cout << solution.isPalindrome1(head) << " | " << solution.isPalindrome2(head)
       << " | " << solution.isPalindrome3(head) << " | " << endl;
  solution.printLinkedList(head);
  cout << "=========================" << endl;

  head = new Node(1);
  head->next = new Node(2);
  head->next->next = new Node(3);
  head->next->next->next = new Node(1);
  solution.printLinkedList(head);
  cout << solution.isPalindrome1(head) << " | " << solution.isPalindrome2(head)
       << " | " << solution.isPalindrome3(head) << " | " << endl;
  solution.printLinkedList(head);
  cout << "=========================" << endl;

  head = new Node(1);
  head->next = new Node(2);
  head->next->next = new Node(2);
  head->next->next->next = new Node(1);
  solution.printLinkedList(head);
  cout << solution.isPalindrome1(head) << " | " << solution.isPalindrome2(head)
       << " | " << solution.isPalindrome3(head) << " | " << endl;
  solution.printLinkedList(head);
  cout << "=========================" << endl;

  head = new Node(1);
  head->next = new Node(2);
  head->next->next = new Node(3);
  head->next->next->next = new Node(2);
  head->next->next->next->next = new Node(1);
  solution.printLinkedList(head);
  cout << solution.isPalindrome1(head) << " | " << solution.isPalindrome2(head)
       << " | " << solution.isPalindrome3(head) << " | " << endl;
  solution.printLinkedList(head);
  cout << "=========================" << endl;

  return 0;
}