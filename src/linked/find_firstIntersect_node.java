package linked;

class Node {
  public int value;
  public Node next;

  public Node(int data) {
    this.value = data;
  }
}

class Solution7 {
  /**
   * 找到链表的第一个入环节点，如果无环，返回null
   * 快指针一次走两步、慢指针一次走一步，在第一次相遇节点停下， 快指针 回到头 慢指针留在原地 同时每次分别走一步，在第一个入环节点处相遇
   * 
   * @param head
   * @return
   */
  public Node getLoopNode(Node head) {
    if (head == null || head.next == null || head.next.next == null)
      return null;
    Node slow = head.next;
    Node fast = head.next.next;
    while (slow != fast) {
      if (fast.next == null || fast.next.next == null)
        return null;
      fast = fast.next.next;
      slow = slow.next;
    }

    // slow fast 相遇后 fast 回到头 slow留在原地 同时各走一步
    fast = head;
    while (fast != slow) {
      slow = slow.next;
      fast = fast.next;
    }
    return slow;
  }

  /**
   * 如果两个链表都无环，返回第一个相交节点，如果不相交，返回null
   * 
   * @param head1
   * @param head2
   * @return
   */
  public Node noLoop(Node head1, Node head2) {
    if (head1 == null || head2 == null)
      return null;

    Node cur1 = head1, cur2 = head2;
    int n = 0;
    while (cur1.next != null) {
      n++;
      cur1 = cur1.next;
    }

    while (cur2.next != null) {
      n--;
      cur2 = cur2.next;
    }

    if (cur1 != cur2)
      return null;

    // n=|head1.size - head2.size|
    cur1 = n > 0 ? head1 : head2;// who is long, who is cur1.
    cur2 = cur1 == head1 ? head2 : head1;// who is short, who is cur2.

    n = Math.abs(n);
    while (n != 0) {
      n--;
      cur1 = cur1.next;
    }
    while (cur1 != cur2) {
      cur1 = cur1.next;
      cur2 = cur2.next;
    }
    return cur1; // 此时返回的是第一个相交节点
  }

  /**
   * 两个有环链表，返回第一个相交节点，如果不相交返回null
   * 
   * @param head1
   * @param loop1
   * @param head2
   * @param loop2
   * @return
   */
  public Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
    Node cur1 = null, cur2 = null;
    if (loop1 == loop2) {
      // 此情况下 解决相交点在环外 就是 两个链表都无环，返回第一个相交节点，如果不相交，返回null
      cur1 = head1;
      cur2 = head2;
      int n = 0;
      while (cur1 != loop1) {
        n++;
        cur1 = cur1.next;
      }
      while (cur2 != loop2) {
        n--;
        cur2 = cur2.next;
      }
      cur1 = n > 0 ? head1 : head2;
      cur2 = cur1 == head1 ? head2 : head1;
      n = Math.abs(n);
      while (n != 0) {
        n--;
        cur1 = cur1.next;
      }
      while (cur1 != cur2) {
        cur1 = cur1.next;
        cur2 = cur2.next;
      }
      return cur1;
    } else {
      // 此情况解决 两个环链表不相交、或 相交在同一个环中
      cur1 = loop1.next;
      while (cur1 != loop1) {
        if (cur1 == loop2)
          return loop1;
        cur1 = cur1.next;
      }
      return null;
    }
  }

  public Node getIntersectNode(Node head1, Node head2) {
    if (head1 == null || head2 == null)
      return null;

    Node loop1 = getLoopNode(head1);
    Node loop2 = getLoopNode(head2);
    if (loop1 == null && loop2 == null) {
      return noLoop(head1, head2);
    }
    if (loop1 != null && loop2 != null) {
      return bothLoop(head1, loop1, head2, loop2);
    }
    return null;
  }

  public static void main(String[] args) {
    Solution7 solution = new Solution7();

    Node head1 = new Node(1);
    head1.next = new Node(2);
    head1.next.next = new Node(3);
    head1.next.next.next = new Node(4);
    head1.next.next.next.next = new Node(5);
    head1.next.next.next.next.next = new Node(6);
    head1.next.next.next.next.next.next = new Node(7);

    // 0->9->8->6->7->null
    Node head2 = new Node(0);
    head2.next = new Node(9);
    head2.next.next = new Node(8);
    head2.next.next.next = head1.next.next.next.next.next; // 8->6
    System.out.println(solution.getIntersectNode(head1, head2).value);

    // 1->2->3->4->5->6->7->4...
    head1 = new Node(1);
    head1.next = new Node(2);
    head1.next.next = new Node(3);
    head1.next.next.next = new Node(4);
    head1.next.next.next.next = new Node(5);
    head1.next.next.next.next.next = new Node(6);
    head1.next.next.next.next.next.next = new Node(7);
    head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

    // 0->9->8->2...
    head2 = new Node(0);
    head2.next = new Node(9);
    head2.next.next = new Node(8);
    head2.next.next.next = head1.next; // 8->2
    System.out.println(solution.getIntersectNode(head1, head2).value);

    // 0->9->8->6->4->5->6..
    head2 = new Node(0);
    head2.next = new Node(9);
    head2.next.next = new Node(8);
    head2.next.next.next = head1.next.next.next.next.next; // 8->6
    System.out.println(solution.getIntersectNode(head1, head2).value);
  }
}
