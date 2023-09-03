#include <iostream>
#include <unordered_map>

using namespace std;
struct Node {
  int value;
  Node *next, *random;
  Node(int value) : value(value), next(nullptr), random(nullptr){};
};
class Solution {
  Node* copyRandomList1(Node* head) {
    unordered_map<Node*, Node*> m;
    Node* cur = head;
    while (cur != nullptr) {
      m.insert(cur, new Node(cur->value));
      cur = cur->next;
    }

    cur = head;
    while (cur != nullptr) {
      m[cur]->next = m[cur->next];
      m[cur]->random = m[cur->random];
      cur = cur->next;
    }
    return m[head];
  }

  Node* copyRandomList2(Node* head) {
    if (head == nullptr) return nullptr;
    Node *cur = head, *next = nullptr;
    while (cur != nullptr) {
      next = cur->next;
      cur->next = new Node(cur->value);
      cur->next->next = next;
      cur = next;
    }

    cur = head;
    Node* copy = nullptr;
    // 依次设置 1' 2' 3' random指针
    while (cur != nullptr) {
      next = cur->next->next;
      copy = cur->next;
      copy->random = cur->random != nullptr ? cur->random->next : nullptr;
      cur = next;
    }
    Node* res = head->next;
    cur = head;

    // 分离新老链表
    while (cur != nullptr) {
      next = cur->next->next;
      copy = cur->next;
      cur->next = next;
      copy->next = next != nullptr ? next->next : nullptr;
      cur = next;
    }
    return res;
  }
};

int main() { return 0; }