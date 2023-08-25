package quick_sort;

import java.util.ArrayList;
import java.util.Comparator;

class Solution3 {

  public class Node {
    public int value;
    public Node last, next;

    public Node(int v) {
      this.value = v;
    }
  }

  public class Info {
    public Node lh;
    public Node lt;
    public int ls;
    public Node rh;
    public Node rt;
    public int rs;
    public Node eh;
    public Node et;

    public Info(Node lH, Node lT, int lS, Node rH, Node rT, int rS, Node eH, Node eT) {
      lh = lH;
      lt = lT;
      ls = lS;
      rh = rH;
      rt = rT;
      rs = rS;
      eh = eH;
      et = eT;
    }
  }

  public Info partition(Node L, Node pivot) {
    Node lh = null;
    Node lt = null;
    int ls = 0;
    Node rh = null;
    Node rt = null;
    int rs = 0;
    Node eh = pivot;
    Node et = pivot;
    Node tmp = null;
    while (L != null) {
      tmp = L.next;
      L.next = null;
      L.last = null;
      if (L.value < pivot.value) {
        ls++;
        if (lh == null) {
          lh = L;
          lt = L;
        } else {
          lt.next = L;
          L.last = lt;
          lt = L;
        }
      } else if (L.value > pivot.value) {
        rs++;
        if (rh == null) {
          rh = L;
          rt = L;
        } else {
          rt.next = L;
          L.last = rt;
          rt = L;
        }
      } else {
        et.next = L;
        L.last = et;
        et = L;
      }
      L = tmp;
    }
    return new Info(lh, lt, ls, rh, rt, rs, eh, et);
  }

  public Node quickSort(Node h) {
    if (h == null)
      return null;
    int N = 0;
    Node c = h, e = null;
    while (c != null) {
      N++;
      e = c;
      c = c.next;
    }
    return process(h, e, N).h;
  }

  public class HeadTail {
    private Node h, t;

    public HeadTail(Node head, Node tail) {
      h = head;
      t = tail;
    }
  }

  public HeadTail process(Node l, Node r, int N) {
    if (l == null)
      return null;
    if (l == r)
      return new HeadTail(l, r);
    int randomIndex = (int) (Math.random() * N);
    Node randomNode = l;
    while (randomIndex-- != 0)
      randomNode = randomNode.next;

    if (randomNode == l || randomNode == r) {
      if (randomNode == l) {
        l = randomNode.next;
        l.last = null;
      } else {
        randomNode.last.next = null;
      }
    } else {
      randomNode.last.next = randomNode.next;
      randomNode.next.last = randomNode.last;
    }
    randomNode.last = null;
    randomNode.next = null;
    Info info = partition(l, randomNode);
    HeadTail lht = process(info.lh, info.lt, info.ls);
    HeadTail rht = process(info.rh, info.rt, info.rs);

    if (lht != null) {
      lht.t.next = info.eh;
      info.eh.last = lht.t;
    }
    if (rht != null) {
      info.et.next = rht.h;
      rht.h.last = info.et;
    }

    Node h = lht != null ? lht.h : info.eh;
    Node t = rht != null ? rht.t : info.et;
    return new HeadTail(h, t);
  }

  // ================ TEST ================
  public class NodeComp implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
      return o1.value - o2.value;
    }

  }

  // 为了测试
  public Node sort(Node head) {
    if (head == null) {
      return null;
    }
    ArrayList<Node> arr = new ArrayList<>();
    while (head != null) {
      arr.add(head);
      head = head.next;
    }
    arr.sort(new NodeComp());
    Node h = arr.get(0);
    h.last = null;
    Node p = h;
    for (int i = 1; i < arr.size(); i++) {
      Node c = arr.get(i);
      p.next = c;
      c.last = p;
      c.next = null;
      p = c;
    }
    return h;
  }

  // 为了测试
  public Node generateRandomDoubleLinkedList(int n, int v) {
    if (n == 0) {
      return null;
    }
    Node[] arr = new Node[n];
    for (int i = 0; i < n; i++) {
      arr[i] = new Node((int) (Math.random() * v));
    }
    Node head = arr[0];
    Node pre = head;
    for (int i = 1; i < n; i++) {
      pre.next = arr[i];
      arr[i].last = pre;
      pre = arr[i];
    }
    return head;
  }

  // 为了测试
  public Node cloneDoubleLinkedList(Node head) {
    if (head == null) {
      return null;
    }
    Node h = new Node(head.value);
    Node p = h;
    head = head.next;
    while (head != null) {
      Node c = new Node(head.value);
      p.next = c;
      c.last = p;
      p = c;
      head = head.next;
    }
    return h;
  }

  // 为了测试
  public boolean equal(Node h1, Node h2) {
    return doubleLinkedListToString(h1).equals(doubleLinkedListToString(h2));
  }

  // 为了测试
  public String doubleLinkedListToString(Node head) {
    Node cur = head;
    Node end = null;
    StringBuilder builder = new StringBuilder();
    while (cur != null) {
      builder.append(cur.value + " ");
      end = cur;
      cur = cur.next;
    }
    builder.append("| ");
    while (end != null) {
      builder.append(end.value + " ");
      end = end.last;
    }
    return builder.toString();
  }

  // 为了测试
  public static void main(String[] args) {
    Solution3 solution3 = new Solution3();
    int N = 500, V = 500, testTime = 10000;
    System.out.println("测试开始");
    for (int i = 0; i < testTime; i++) {
      int size = (int) (Math.random() * N);
      Node head1 = solution3.generateRandomDoubleLinkedList(size, V);
      Node head2 = solution3.cloneDoubleLinkedList(head1);
      Node sort1 = solution3.quickSort(head1);
      Node sort2 = solution3.sort(head2);
      if (!solution3.equal(sort1, sort2)) {
        System.out.println("出错了!");
        break;
      }
    }
    System.out.println("测试结束");
  }
}
