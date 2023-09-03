package linked;

import java.util.HashMap;

class Node {
  int val;
  Node next;
  Node random;

  public Node(int val) {
    this.val = val;
    this.next = null;
    this.random = null;
  }
}

class Solution6 {
  public Node copyRandomList1(Node head) {
    HashMap<Node, Node> map = new HashMap<>();// key 老节点；value 新节点
    Node cur = head;
    while (cur != null) {
      map.put(cur, new Node(cur.val));
      cur = cur.next;
    }
    cur = head;
    while (cur != null) {
      /**
       * cur 老
       * map.get(cur) 新
       * 新.next -> cur.next克隆节点找到
       */
      map.get(cur).next = map.get(cur.next);
      map.get(cur).random = map.get(cur.random);
      cur = cur.next;
    }
    return map.get(head);
  }

  public Node copyRandomList2(Node head) {
    if (head == null) {
      return null;
    }
    Node cur = head;
    Node next = null;
    while (cur != null) {
      next = cur.next;
      cur.next = new Node(cur.val);
      cur.next.next = next;
      cur = next;
    }
    cur = head;
    Node copy = null;
    // 依次设置 1' 2' 3' random指针
    while (cur != null) {
      next = cur.next.next;
      copy = cur.next;
      copy.random = cur.random != null ? cur.random.next : null;
      cur = next;
    }
    Node res = head.next;
    cur = head;

    // 分离新老链表
    while (cur != null) {
      next = cur.next.next;
      copy = cur.next;
      cur.next = next;
      copy.next = next != null ? next.next : null;
      cur = next;
    }
    return res;
  }

}
