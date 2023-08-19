#include <iostream>

using namespace std;

struct Node {
  int value;
  Node *next;
  Node() : value(0), next(nullptr) {}
  Node(int x) : value(x), next(nullptr) {}
  Node(int x, Node *next) : value(x), next(next) {}
};

struct DoubleNode {
  int value;
  DoubleNode *next;
  DoubleNode *pre;
  DoubleNode() : value(0), next(nullptr), pre(nullptr) {}
  DoubleNode(int x) : value(x), next(nullptr), pre(nullptr) {}
  DoubleNode(int x, DoubleNode *next, DoubleNode *pre)
      : value(x), next(next), pre(pre) {}
};

class Solution {
 public:
  /**
   * 反转双向链表
   */
  DoubleNode *reverse_double_list(DoubleNode *head) {
    DoubleNode *pre = nullptr, *next = nullptr;
    while (head != nullptr) {
      next = head->next;
      head->next = pre;
      head->pre = next;
      pre = head;
      head = next;
    }
    return pre;
  }
  /**
   * 反转单向链表
   */
  Node *reverse_linked_list(Node *head) {
    Node *pre = nullptr, *next = nullptr;
    while (head != nullptr) {
      next = head->next;
      head->next = pre;
      pre = head;
      head = next;
    }
    return pre;
  }

  /**
   * 链表删除指定值
   */
  Node *remove_value(Node *head, int value) {
    while (head != nullptr) {
      if (head->value != value) break;

      head = head->next;
    }

    Node *pre = head, *cur = head;
    while (cur != nullptr) {
      if (cur->value == value)
        pre->next = cur->next;
      else
        pre = cur;
      cur = cur->next;
    }
    return head;
  }

  // ============ TEST ============
  /**
   * 反转单向链表（TEST）
   */
  Node *test_reverse_linked_list(Node *head) {
    if (head == nullptr) return nullptr;
    vector<Node *> list;
    while (head != nullptr) {
      list.push_back(head);
      head = head->next;
    }

    list[0]->next = nullptr;
    int N = list.size();
    for (int i = 1; i < N; i++) {
      list[i]->next = list[i - 1];
    }
    return list[N - 1];
  };

  /**
   * 反转双向链表（TEST）
   */
  DoubleNode *test_reverse_double_list(DoubleNode *head) {
    if (head == nullptr) return nullptr;
    vector<DoubleNode *> list;
    while (head != nullptr) {
      list.push_back(head);
      head = head->next;
    }

    list[0]->next = nullptr;
    DoubleNode *pre = list[0];
    int N = list.size();
    for (int i = 1; i < N; i++) {
      DoubleNode *cur = list[i];
      cur->pre = nullptr;
      cur->next = pre;
      pre->pre = cur;
      pre = cur;
    }
    return list[N - 1];
  };

  /**
   * 生成单向链表
   */
  Node *generate_random_linked_list(int len, int value) {
    int size = (rand() % (len + 1));
    if (size == 0) return nullptr;
    size--;
    Node *head = new Node(rand() % (value + 1));
    Node *pre = head;
    while (size != 0) {
      Node *cur = new Node(rand() % (value + 1));
      pre->next = cur;
      pre = cur;
      size--;
    }
    return head;
  };

  /**
   * 生成双向链表
   */
  DoubleNode *generate_random_double_list(int len, int value) {
    int size = (rand() % (len + 1));
    if (size == 0) return nullptr;
    size--;
    DoubleNode *head = new DoubleNode(rand() % (value + 1));
    DoubleNode *pre = head;

    while (size != 0) {
      DoubleNode *cur = new DoubleNode(rand() % (value + 1));
      pre->next = cur;
      cur->pre = pre;
      pre = cur;
      size--;
    }
    return head;
  };

  /**
   * 遍历单向链表
   */
  vector<int> get_linked_list_origin_order(Node *head) {
    vector<int> ans;
    while (head != nullptr) {
      ans.push_back(head->value);
      head = head->next;
    }
    return ans;
  };

  /**
   * 检查单向链表反转是否正确
   */
  bool check_linked_list_reverse(vector<int> origin, Node *head) {
    for (int i = origin.size() - 1; i >= 0; i--) {
      if (origin[i] != head->value) return false;
      head = head->next;
    }
    return true;
  };

  /**
   * 遍历双向链表
   */
  vector<int> get_double_list_origin_order(DoubleNode *head) {
    vector<int> ans;
    while (head != nullptr) {
      ans.push_back(head->value);
      head = head->next;
    }
    return ans;
  };

  /**
   * 检查双向链表反转是否正确
   */
  bool check_double_list_reverse(vector<int> origin, DoubleNode *head) {
    DoubleNode *end = nullptr;
    for (int i = origin.size() - 1; i >= 0; i--) {
      if (origin[i] != head->value) return false;
      end = head;
      head = head->next;
    }

    for (int i = 0; i < origin.size(); i++) {
      if (origin[i] != end->value) return false;
      end = end->pre;
    }
    return true;
  };
};

int main() {
  Solution solution;
  int len = 50;
  int value = 100;
  int testTime = 100000;
  cout << "TEST Begin" << endl;
  for (int i = 0; i < testTime; i++) {
    Node *node1 = solution.generate_random_linked_list(len, value);
    vector<int> list1 = solution.get_linked_list_origin_order(node1);
    node1 = solution.reverse_linked_list(node1);
    if (!solution.check_linked_list_reverse(list1, node1)) {
      cout << "Oops1!" << endl;
    }

    Node *node2 = solution.generate_random_linked_list(len, value);
    vector<int> list2 = solution.get_linked_list_origin_order(node2);
    node2 = solution.test_reverse_linked_list(node2);
    if (!solution.check_linked_list_reverse(list2, node2)) {
      cout << "Oops2!" << endl;
    }

    DoubleNode *node3 = solution.generate_random_double_list(len, value);
    vector<int> list3 = solution.get_double_list_origin_order(node3);
    node3 = solution.reverse_double_list(node3);
    if (!solution.check_double_list_reverse(list3, node3)) {
      cout << "Oops3!" << endl;
    }

    DoubleNode *node4 = solution.generate_random_double_list(len, value);
    vector<int> list4 = solution.get_double_list_origin_order(node4);
    node4 = solution.test_reverse_double_list(node4);
    if (!solution.check_double_list_reverse(list4, node4)) {
      cout << "Oops4!" << endl;
    }
  };
  cout << "TEST Finish!" << endl;

  return 0;
}