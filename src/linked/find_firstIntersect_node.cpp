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
   * 找到链表的第一个入环节点，如果无环，返回null
   * 快指针一次走两步、慢指针一次走一步，在第一次相遇节点停下， 快指针 回到头
   * 慢指针留在原地 同时每次分别走一步，在第一个入环节点处相遇
   */
  Node* get_loop_node(Node* head) {
    if (head == nullptr || head->next == nullptr || head->next->next == nullptr)
      return nullptr;
    Node* slow = head->next;
    Node* fast = head->next->next;

    while (slow != fast) {
      if (fast->next == nullptr || fast->next->next == nullptr) return nullptr;
      fast = fast->next->next, slow = slow->next;
    }

    // slow fast 相遇后 fast 回到头 slow留在原地 同时各走一步
    fast = head;
    while (fast != slow) slow = slow->next, fast = fast->next;
    return slow;
  };

  /**
   * 如果两个链表都无环，返回第一个相交节点，如果不相交，返回null
   */
  Node* no_loop_node(Node* head1, Node* head2) {
    if (head1 == nullptr || head2 == nullptr) return nullptr;
    Node *cur1 = head1, *cur2 = head2;
    int n = 0;
    while (cur1->next != nullptr) {
      n++;
      cur1 = cur1->next;
    }

    while (cur2->next != nullptr) {
      n--;
      cur2 = cur2->next;
    }

    if (cur1 != cur2) return nullptr;

    // n=|head1.size - head2.size|
    cur1 = n > 0 ? head1 : head2;
    cur2 = cur1 == head1 ? head2 : head1;

    n = abs(n);
    while (n != 0) {
      n--;
      cur1 = cur1->next;
    }
    while (cur1 != cur2) {
      cur1 = cur1->next;
      cur2 = cur2->next;
    }
    return cur1;  // 此时返回的是第一个相交节点
  };

  /**
   * 两个有环链表，返回第一个相交节点，如果不相交返回null
   */
  Node* both_loop_node(Node* head1, Node* loop1, Node* head2, Node* loop2) {
    Node *cur1 = nullptr, *cur2 = nullptr;
    if (loop1 == loop2) {
      cur1 = head1;
      cur2 = head2;
      int n = 0;
      while (cur1 != loop1) {
        n++;
        cur1 = cur1->next;
      }
      while (cur2 != loop2) {
        n--;
        cur2 = cur2->next;
      }
      cur1 = n > 0 ? head1 : head2;
      cur2 = cur1 == head1 ? head2 : head1;
      n = abs(n);
      while (n != 0) {
        n--;
        cur1 = cur1->next;
      }
      while (cur1 != cur2) {
        cur1 = cur1->next;
        cur2 = cur2->next;
      }
      return cur1;
    } else {
      // 此情况解决 两个环链表不相交、或 相交在同一个环中
      cur1 = loop1->next;
      while (cur1 != loop1) {
        if (cur1 == loop2) return loop1;
        cur1 = cur1->next;
      }
      return nullptr;
    }
  };

  /**
   * 判断两个单向链表是否相交
   */
  Node* get_intersect_node(Node* head1, Node* head2) {
    if (head1 == nullptr || head2 == nullptr) return nullptr;
    Node* loop1 = get_loop_node(head1);
    Node* loop2 = get_loop_node(head2);
    if (loop1 == nullptr && loop2 == nullptr) {
      return no_loop_node(head1, head2);
    }
    if (loop1 != nullptr && loop2 != nullptr) {
      return both_loop_node(head1, loop1, head2, loop2);
    }
    return nullptr;
  }
};

int main() {
  Solution solution;
  Node* head1 = new Node(1);
  head1->next = new Node(2);
  head1->next->next = new Node(3);
  head1->next->next->next = new Node(4);
  head1->next->next->next->next = new Node(5);
  head1->next->next->next->next->next = new Node(6);
  head1->next->next->next->next->next->next = new Node(7);

  // 0->9->8->6->7->null
  Node* head2 = new Node(0);
  head2->next = new Node(9);
  head2->next->next = new Node(8);
  head2->next->next->next = head1->next->next->next->next->next;  // 8->6

  cout << (solution.get_intersect_node(head1, head2)->value) << endl;

  // // 1->2->3->4->5->6->7->4...
  // head1 = new Node(1);
  // head1->next = new Node(2);
  // head1->next->next = new Node(3);
  // head1->next->next->next = new Node(4);
  // head1->next->next->next->next = new Node(5);
  // head1->next->next->next->next->next = new Node(6);
  // head1->next->next->next->next->next->next = new Node(7);
  // head1->next->next->next->next->next->next = head1->next->next->next;  //
  // 7->4

  // // 0->9->8->2...
  // head2 = new Node(0);
  // head2->next = new Node(9);
  // head2->next->next = new Node(8);
  // head2->next->next->next = head1->next;  // 8->2
  // cout << (solution.get_intersect_node(head1, head2)->value) << endl;

  // // 0->9->8->6->4->5->6..
  // head2 = new Node(0);
  // head2->next = new Node(9);
  // head2->next->next = new Node(8);
  // head2->next->next->next = head1->next->next->next->next->next;  // 8->6
  // cout << (solution.get_intersect_node(head1, head2)->value) << endl;

  return 0;
}