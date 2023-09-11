package linked;

import java.util.Stack;

class Solution4 {
  /**
   * 额外空间复杂度 O(N)
   * 
   * @param head
   * @return
   */
  public boolean isPalindrome1(Node head) {
    Stack<Node> stack = new Stack<>();
    Node cur = head;
    while (cur != null) {
      stack.push(cur);
      cur = cur.next;
    }

    while (head != null) {
      if (head.value != stack.pop().value)
        return false;

      head = head.next;
    }
    return true;
  }

  /**
   * 额外空间复杂度 O(N/2)
   * 
   * @param head
   * @return
   */
  public boolean isPalindrome2(Node head) {
    if (head == null || head.next == null)
      return true;
    Node right = head.next, cur = head;
    while (cur.next != null && cur.next.next != null) {
      right = right.next; // --> mid
      cur = cur.next.next;// --> end
    }

    Stack<Node> stack = new Stack<>();
    while (right != null) {
      stack.push(right);
      right = right.next;
    }
    while (!stack.isEmpty()) {
      if (head.value != stack.pop().value)
        return false;
      head = head.next;
    }
    return true;
  }

  /**
   * 额外空间复杂度 O(1)
   * 
   * @param head
   * @return
   */
  public boolean isPalindrome3(Node head) {
    if (head == null || head.next == null) {
      return true;
    }
    Node n1 = head, n2 = head;

    while (n2.next != null && n2.next.next != null) {
      n1 = n1.next; // n1 -> mid
      n2 = n2.next.next; // n2 -> end
    }

    n2 = n1.next; // n2 是 中点右侧的第一个节点
    n1.next = null; // 中点右侧与原链表断链
    Node n3 = null;

    while (n2 != null) { // 反转 中点右侧的所有节点
      n3 = n2.next;// 暂存 中点右边的next节点
      n2.next = n1; // 右节点的下一步转换
      n1 = n2;
      n2 = n3;
    }

    n3 = n1; // n3暂存n1节点；n1节点是 中点右侧翻转过后的头节点
    n2 = head;// 左侧开始的头节点
    boolean res = true;

    while (n1 != null && n2 != null) {
      if (n1.value != n2.value) {
        res = false;
        break;
      }
      n1 = n1.next;
      n2 = n2.next;
    }

    n1 = n3.next;
    n3.next = null;
    while (n1 != null) { // 将原链表恢复
      n2 = n1.next;
      n1.next = n3;
      n3 = n1;
      n1 = n2;
    }

    return res;
  }

  // ============ TEST ============
  public void printLinkedList(Node node) {
    System.out.print("Linked List: ");
    while (node != null) {
      System.out.print(node.value + " ");
      node = node.next;
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Solution4 solution = new Solution4();
    Node head = null;
    solution.printLinkedList(head);
    System.out.print(solution.isPalindrome1(head) + " | ");
    System.out.print(solution.isPalindrome2(head) + " | ");
    System.out.println(solution.isPalindrome3(head) + " | ");
    solution.printLinkedList(head);
    System.out.println("=========================");

    head = new Node(1);
    solution.printLinkedList(head);
    System.out.print(solution.isPalindrome1(head) + " | ");
    System.out.print(solution.isPalindrome2(head) + " | ");
    System.out.println(solution.isPalindrome3(head) + " | ");
    solution.printLinkedList(head);
    System.out.println("=========================");

    head = new Node(1);
    head.next = new Node(2);
    solution.printLinkedList(head);
    System.out.print(solution.isPalindrome1(head) + " | ");
    System.out.print(solution.isPalindrome2(head) + " | ");
    System.out.println(solution.isPalindrome3(head) + " | ");
    solution.printLinkedList(head);
    System.out.println("=========================");

    head = new Node(1);
    head.next = new Node(1);
    solution.printLinkedList(head);
    System.out.print(solution.isPalindrome1(head) + " | ");
    System.out.print(solution.isPalindrome2(head) + " | ");
    System.out.println(solution.isPalindrome3(head) + " | ");
    solution.printLinkedList(head);
    System.out.println("=========================");

    head = new Node(1);
    head.next = new Node(2);
    head.next.next = new Node(3);
    solution.printLinkedList(head);
    System.out.print(solution.isPalindrome1(head) + " | ");
    System.out.print(solution.isPalindrome2(head) + " | ");
    System.out.println(solution.isPalindrome3(head) + " | ");
    solution.printLinkedList(head);
    System.out.println("=========================");

    head = new Node(1);
    head.next = new Node(2);
    head.next.next = new Node(1);
    solution.printLinkedList(head);
    System.out.print(solution.isPalindrome1(head) + " | ");
    System.out.print(solution.isPalindrome2(head) + " | ");
    System.out.println(solution.isPalindrome3(head) + " | ");
    solution.printLinkedList(head);
    System.out.println("=========================");

    head = new Node(1);
    head.next = new Node(2);
    head.next.next = new Node(3);
    head.next.next.next = new Node(1);
    solution.printLinkedList(head);
    System.out.print(solution.isPalindrome1(head) + " | ");
    System.out.print(solution.isPalindrome2(head) + " | ");
    System.out.println(solution.isPalindrome3(head) + " | ");
    solution.printLinkedList(head);
    System.out.println("=========================");

    head = new Node(1);
    head.next = new Node(2);
    head.next.next = new Node(2);
    head.next.next.next = new Node(1);
    solution.printLinkedList(head);
    System.out.print(solution.isPalindrome1(head) + " | ");
    System.out.print(solution.isPalindrome2(head) + " | ");
    System.out.println(solution.isPalindrome3(head) + " | ");
    solution.printLinkedList(head);
    System.out.println("=========================");

    head = new Node(1);
    head.next = new Node(2);
    head.next.next = new Node(3);
    head.next.next.next = new Node(2);
    head.next.next.next.next = new Node(1);
    solution.printLinkedList(head);
    System.out.print(solution.isPalindrome1(head) + " | ");
    System.out.print(solution.isPalindrome2(head) + " | ");
    System.out.println(solution.isPalindrome3(head) + " | ");
    solution.printLinkedList(head);
    System.out.println("=========================");

  }
}