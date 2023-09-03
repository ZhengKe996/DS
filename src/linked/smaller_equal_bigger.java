package linked;

class Node {
  public int value;
  public Node next;

  public Node(int data) {
    this.value = data;
  }
}

class Solution5 {

  public Node listPartition1(Node head, int pivot) {
    if (head == null)
      return head;
    Node cur = head;
    int i = 0;
    while (cur != null) { // 统计链表节点个数
      i++;
      cur = cur.next;
    }

    // 将链表节点放入数字中 进行区域划分
    Node[] nodeArr = new Node[i];
    i = 0;
    cur = head;
    for (i = 0; i != nodeArr.length; i++) {
      nodeArr[i] = cur;
      cur = cur.next;
    }

    arrPartition(nodeArr, pivot);
    for (i = 1; i != nodeArr.length; i++) {
      nodeArr[i - 1].next = nodeArr[i];
    }
    nodeArr[i - 1].next = null;
    return nodeArr[0];
  }

  private void arrPartition(Node[] nodeArr, int pivot) {
    int small = -1;
    int big = nodeArr.length;
    int index = 0;
    while (index != big) {
      if (nodeArr[index].value < pivot) {
        swap(nodeArr, ++small, index++);
      } else if (nodeArr[index].value == pivot) {
        index++;
      } else {
        swap(nodeArr, --big, index);
      }
    }
  }

  private void swap(Node[] nodeArr, int a, int b) {
    Node tmp = nodeArr[a];
    nodeArr[a] = nodeArr[b];
    nodeArr[b] = tmp;
  }

  public Node listPartition2(Node head, int pivot) {
    Node sH = null; // 小头
    Node sT = null; // 小尾
    Node eH = null; // 等头
    Node eT = null; // 等尾
    Node mH = null; // 大头
    Node mT = null; // 大尾
    Node next = null;

    // 1. 划分为三个区域
    while (head != null) {
      next = head.next;

      head.next = null;
      if (head.value < pivot) {
        // 小区操作
        if (sH == null) {
          sH = head;
          sT = head;
        } else {
          sT.next = head;
          sT = head;
        }
      } else if (head.value == pivot) {
        // 等区操作
        if (eH == null) {
          eH = head;
          eT = head;
        } else {
          eT.next = head;
          eT = head;
        }
      } else {
        // 大区操作
        if (mH == null) {
          mH = head;
          mT = head;
        } else {
          mT.next = head;
          mT = head;
        }
      }
      head = next;
    }

    // 2. 小尾连等头，等尾连大头
    if (sT != null) {
      sT.next = eH;
      eT = (eT == null ? sT : eT); // 等头为空 代表等区为空；等头就是小尾
    }

    if (eT != null) {
      eT.next = mH;
    }

    return sH != null ? sH : (eH != null ? eH : mH); // 如果小头为null 返回等头；若等头为null，返回大头
  }

  // =========== TEST ===========
  public void printLinkedList(Node node) {
    System.out.print("Linked List: ");
    while (node != null) {
      System.out.print(node.value + " ");
      node = node.next;
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Solution5 solution = new Solution5();
    Node head1 = new Node(7);
    head1.next = new Node(9);
    head1.next.next = new Node(1);
    head1.next.next.next = new Node(8);
    head1.next.next.next.next = new Node(5);
    head1.next.next.next.next.next = new Node(2);
    head1.next.next.next.next.next.next = new Node(5);
    solution.printLinkedList(head1);
    head1 = solution.listPartition1(head1, 5);
    // head1 = solution.listPartition2(head1, 5);
    solution.printLinkedList(head1);

  }
}
