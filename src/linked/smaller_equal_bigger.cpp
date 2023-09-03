#include <iostream>

using namespace std;
struct Node {
  int value;
  Node* next;
  Node(int value) : value(value){};
};
class Solution {
 private:
  void arrPartition(vector<Node*>& nodeArr, int pivot) {
    int small = -1, big = nodeArr.size(), index = 0;
    while (index != big) {
      if (nodeArr[index]->value < pivot) {
        swap(nodeArr, ++small, index++);
      } else if (nodeArr[index]->value == pivot) {
        index++;
      } else {
        swap(nodeArr, --big, index);
      }
    }
  }

  void swap(vector<Node*>& nodeArr, int a, int b) {
    Node* tmp = nodeArr[a];
    nodeArr[a] = nodeArr[b];
    nodeArr[b] = tmp;
  }

 public:
  Node* listPartition1(Node* head, int pivot) {
    if (head == nullptr) return head;

    Node* cur = head;
    int i = 0;
    while (cur != nullptr) {
      i++;
      cur = cur->next;
    }

    vector<Node*> nodeArr(i, new Node(0));
    i = 0;
    cur = head;
    for (i = 0; i != nodeArr.size(); i++) {
      nodeArr[i] = cur;
      cur = cur->next;
    }

    arrPartition(nodeArr, pivot);
    for (i = 1; i != nodeArr.size(); i++) {
      nodeArr[i - 1]->next = nodeArr[i];
    }
    nodeArr[i - 1]->next = nullptr;
    return nodeArr[0];
  }

  Node* listPartition2(Node* head, int pivot) {
    Node* sH = nullptr;  // 小头
    Node* sT = nullptr;  // 小尾
    Node* eH = nullptr;  // 等头
    Node* eT = nullptr;  // 等尾
    Node* mH = nullptr;  // 大头
    Node* mT = nullptr;  // 大尾
    Node* next = nullptr;

    while (head != nullptr) {
      next = head->next;

      head->next = nullptr;
      if (head->value < pivot) {
        // 小区操作
        if (sH == nullptr) {
          sH = head;
          sT = head;
        } else {
          sT->next = head;
          sT = head;
        }
      } else if (head->value == pivot) {
        // 等区操作
        if (eH == nullptr) {
          eH = head;
          eT = head;
        } else {
          eT->next = head;
          eT = head;
        }
      } else {
        // 大区操作
        if (mH == nullptr) {
          mH = head;
          mT = head;
        } else {
          mT->next = head;
          mT = head;
        }
      }
      head = next;
    }

    if (sT != nullptr) {
      sT->next = eH;
      eT = (eT == nullptr ? sT : eT);  // 等头为空 代表等区为空；等头就是小尾
    }

    if (eT != nullptr) {
      eT->next = mH;
    }

    return sH != nullptr
               ? sH
               : (eH != nullptr
                      ? eH
                      : mH);  // 如果小头为null 返回等头；若等头为null，返回大头
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
  Node* head1 = new Node(7);
  head1->next = new Node(9);
  head1->next->next = new Node(1);
  head1->next->next->next = new Node(8);
  head1->next->next->next->next = new Node(5);
  head1->next->next->next->next->next = new Node(2);
  head1->next->next->next->next->next->next = new Node(5);
  solution.printLinkedList(head1);
  head1 = solution.listPartition1(head1, 5);
  // head1 = solution.listPartition2(head1, 5);
  solution.printLinkedList(head1);
  return 0;
}