package linked;

import java.util.ArrayList;
import java.util.List;

class Solution {
  private class Node {
    public int value;
    public Node next;

    public Node(int value) {
      this.value = value;
    }
  }

  private class DoubleNode {
    public int value;
    public DoubleNode next;
    public DoubleNode pre;

    public DoubleNode(int value) {
      this.value = value;
    }
  }

  /**
   * 反转单向链表
   * 
   * @param head
   * @return
   */
  public Node ReverseLinkedList(Node head) {
    Node pre = null, next = null;
    while (head != null) {
      next = head.next;
      head.next = pre;
      pre = head;
      head = next;
    }

    return pre;
  }

  /**
   * 反转双向链表
   * 
   * @param head
   * @return
   */
  public DoubleNode ReverseDoubleLinkedList(DoubleNode head) {
    DoubleNode pre = null, next = null;
    while (head != null) {
      next = head.next;
      head.next = pre;
      head.pre = next;
      pre = head;
      head = next;
    }
    return pre;
  }

  /**
   * 系统自带反转单向链表（TEST）
   * 
   * @param head
   * @return
   */
  public Node TestReverseLinkedList(Node head) {
    if (head == null)
      return null;

    ArrayList<Node> list = new ArrayList<>();
    while (head != null) {
      list.add(head);
      head = head.next;
    }

    list.get(0).next = null; // 头结点的next节点设置为空
    int N = list.size();
    for (int i = 1; i < N; i++) {
      list.get(i).next = list.get(i - 1);// 反转操作
    }
    return list.get(N - 1);
  }

  /**
   * 反转双向链表（TEST）
   * 
   * @param head
   * @return
   */
  public DoubleNode TestReverseDoubleList(DoubleNode head) {
    if (head == null)
      return null;

    ArrayList<DoubleNode> list = new ArrayList<>();
    while (head != null) {
      list.add(head);
      head = head.next;
    }

    list.get(0).next = null;
    DoubleNode pre = list.get(0);
    int N = list.size();
    for (int i = 1; i < N; i++) {
      DoubleNode cur = list.get(i); // 接下来反转操作
      cur.pre = null;
      cur.next = pre;
      pre.pre = cur;
      pre = cur;
    }
    return list.get(N - 1);
  }

  /**
   * 生成单向链表
   * 
   * @param len
   * @param value
   * @return
   */
  public Node GenerateRandomLinkedList(int len, int value) {
    int size = (int) (Math.random() * (len + 1));
    if (size == 0) {
      return null;
    }
    size--;
    Node head = new Node((int) (Math.random() * (value + 1)));
    Node pre = head;
    while (size != 0) {
      Node cur = new Node((int) (Math.random() * (value + 1)));
      pre.next = cur;
      pre = cur;
      size--;
    }
    return head;
  }

  /**
   * 生成双向链表
   * 
   * @param len
   * @param value
   * @return
   */
  public DoubleNode GenerateRandomDoubleList(int len, int value) {
    int size = (int) (Math.random() * (len + 1));
    if (size == 0) {
      return null;
    }
    size--;
    DoubleNode head = new DoubleNode((int) (Math.random() * (value + 1)));
    DoubleNode pre = head;
    while (size != 0) {
      DoubleNode cur = new DoubleNode((int) (Math.random() * (value + 1)));
      pre.next = cur;
      cur.pre = pre;
      pre = cur;
      size--;
    }
    return head;
  }

  /**
   * 遍历单向链表
   * 
   * @param head
   * @return
   */
  public List<Integer> GetLinkedListOriginOrder(Node head) {
    List<Integer> ans = new ArrayList<>();
    while (head != null) {
      ans.add(head.value);
      head = head.next;
    }
    return ans;
  }

  /**
   * 检查单向链表反转是否正确
   * 
   * @param origin
   * @param head
   * @return
   */
  public boolean CheckLinkedListReverse(List<Integer> origin, Node head) {
    for (int i = origin.size() - 1; i >= 0; i--) {
      if (!origin.get(i).equals(head.value)) {
        return false;
      }
      head = head.next;
    }
    return true;
  }

  /**
   * 遍历双向链表
   * 
   * @param head
   * @return
   */
  public List<Integer> GetDoubleListOriginOrder(DoubleNode head) {
    List<Integer> ans = new ArrayList<>();
    while (head != null) {
      ans.add(head.value);
      head = head.next;
    }
    return ans;
  }

  /**
   * 检查双向链表反转是否正确
   * 
   * @param origin
   * @param head
   * @return
   */
  public boolean CheckDoubleListReverse(List<Integer> origin, DoubleNode head) {
    DoubleNode end = null;
    for (int i = origin.size() - 1; i >= 0; i--) {
      if (!origin.get(i).equals(head.value)) {
        return false;
      }
      end = head;
      head = head.next;
    }
    for (int i = 0; i < origin.size(); i++) {
      if (!origin.get(i).equals(end.value)) {
        return false;
      }
      end = end.pre;
    }
    return true;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int len = 50;
    int value = 100;
    int testTime = 100000;
    System.out.println("TEST Begin!");
    for (int i = 0; i < testTime; i++) {
      Node node1 = solution.GenerateRandomLinkedList(len, value);
      List<Integer> list1 = solution.GetLinkedListOriginOrder(node1);
      node1 = solution.ReverseLinkedList(node1);
      if (!solution.CheckLinkedListReverse(list1, node1)) {
        System.out.println("Oops1!");
      }

      Node node2 = solution.GenerateRandomLinkedList(len, value);
      List<Integer> list2 = solution.GetLinkedListOriginOrder(node2);
      node2 = solution.TestReverseLinkedList(node2);
      if (!solution.CheckLinkedListReverse(list2, node2)) {
        System.out.println("Oops2!");
      }

      DoubleNode node3 = solution.GenerateRandomDoubleList(len, value);
      List<Integer> list3 = solution.GetDoubleListOriginOrder(node3);
      node3 = solution.ReverseDoubleLinkedList(node3);
      if (!solution.CheckDoubleListReverse(list3, node3)) {
        System.out.println("Oops3!");
      }

      DoubleNode node4 = solution.GenerateRandomDoubleList(len, value);
      List<Integer> list4 = solution.GetDoubleListOriginOrder(node4);
      node4 = solution.TestReverseDoubleList(node4);
      if (!solution.CheckDoubleListReverse(list4, node4)) {
        System.out.println("Oops4!");
      }
    }
    System.out.println("TEST Finish!");

  }
}