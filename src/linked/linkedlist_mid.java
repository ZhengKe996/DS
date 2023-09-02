package linked;

import java.util.ArrayList;

class Node {
  public int value;
  public Node next;

  public Node(int v) {
    this.value = v;
  }
}

class Solution3 {

  /**
   * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
   * 
   * @param head
   * @return
   */
  public Node midOrUpMidNode(Node head) {
    if (head == null || head.next == null || head.next.next == null)
      return head;

    Node slow = head.next, fast = head.next.next;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  /**
   * 输入链表头节点，奇数长度返回中点，偶数长度返回中下点
   * 
   * @param head
   * @return
   */
  public Node midOrDownMidNode(Node head) {
    if (head == null || head.next == null)
      return head;

    Node slow = head.next, fast = head.next;

    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  /**
   * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
   * 
   * @param head
   * @return
   */
  public Node midOrUpMidPreNode(Node head) {
    if (head == null || head.next == null || head.next.next == null)
      return head;

    Node slow = head, fast = head.next.next;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  /**
   * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
   * 
   * @param head
   * @return
   */
  public Node midOrDownMidPreNode(Node head) {
    if (head == null || head.next == null)
      return null;

    if (head.next.next == null)
      return head;

    Node slow = head, fast = head.next;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    return slow;
  }

  // ================= TEST =================
  /**
   * TEST: 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
   * 
   * @param head
   * @return
   */
  public Node right1(Node head) {
    if (head == null)
      return null;

    Node cur = head;
    ArrayList<Node> arr = new ArrayList<>();
    while (cur != null) {
      arr.add(cur);
      cur = cur.next;
    }
    return arr.get((arr.size() - 1) / 2);
  }

  /**
   * TEST: 输入链表头节点，奇数长度返回中点，偶数长度返回中下点
   * 
   * @param head
   * @return
   */
  public Node right2(Node head) {
    if (head == null) {
      return null;
    }
    Node cur = head;
    ArrayList<Node> arr = new ArrayList<>();
    while (cur != null) {
      arr.add(cur);
      cur = cur.next;
    }
    return arr.get(arr.size() / 2);
  }

  /**
   * TEST: 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
   * 
   * @param head
   * @return
   */
  public Node right3(Node head) {
    if (head == null || head.next == null || head.next.next == null) {
      return null;
    }
    Node cur = head;
    ArrayList<Node> arr = new ArrayList<>();
    while (cur != null) {
      arr.add(cur);
      cur = cur.next;
    }
    return arr.get((arr.size() - 3) / 2);
  }

  /**
   * TEST: 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
   * 
   * @param head
   * @return
   */
  public Node right4(Node head) {
    if (head == null || head.next == null) {
      return null;
    }
    Node cur = head;
    ArrayList<Node> arr = new ArrayList<>();
    while (cur != null) {
      arr.add(cur);
      cur = cur.next;
    }
    return arr.get((arr.size() - 2) / 2);
  }

  public static void main(String[] args) {
    Solution3 solution = new Solution3();
    Node test = null;

    test = new Node(0);
    test.next = new Node(1);
    test.next.next = new Node(2);
    test.next.next.next = new Node(3);
    test.next.next.next.next = new Node(4);
    test.next.next.next.next.next = new Node(5);
    test.next.next.next.next.next.next = new Node(6);
    test.next.next.next.next.next.next.next = new Node(7);
    test.next.next.next.next.next.next.next.next = new Node(8);
    test.next.next.next.next.next.next.next.next.next = new Node(9);

    Node ans1 = null;
    Node ans2 = null;

    ans1 = solution.midOrUpMidNode(test);
    ans2 = solution.right1(test);

    System.out.print(ans1 != null ? ans1.value : "无");
    System.out.print(ans2 != null ? ans2.value : "无");
    System.out.println();

    ans1 = solution.midOrDownMidNode(test);
    ans2 = solution.right2(test);
    System.out.print(ans1 != null ? ans1.value : "无");
    System.out.print(ans2 != null ? ans2.value : "无");
    System.out.println();
    ans1 = solution.midOrUpMidPreNode(test);
    ans2 = solution.right3(test);
    System.out.print(ans1 != null ? ans1.value : "无");
    System.out.print(ans2 != null ? ans2.value : "无");
    System.out.println();

    ans1 = solution.midOrDownMidPreNode(test);
    ans2 = solution.right4(test);
    System.out.print(ans1 != null ? ans1.value : "无");
    System.out.print(ans2 != null ? ans2.value : "无");
  }
}
