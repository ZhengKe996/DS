package binary_tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Solution4 {
  private static class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int data) {
      this.value = data;
    }
  }

  /**
   * 前序序列化
   * 
   * @param head
   * @return
   */
  public static Queue<String> preSerial(Node head) {
    Queue<String> ans = new LinkedList<>();
    pres(head, ans);
    return ans;
  }

  private static void pres(Node head, Queue<String> ans) {
    if (head == null)
      ans.add("#");
    else {
      ans.add(String.valueOf(head.value));
      pres(head.left, ans);
      pres(head.right, ans);
    }
  }

  /**
   * 中遍历序列化（两颗不一样的树 中序遍历可能一样）
   * 
   * @param head
   * @return
   */
  public static Queue<String> inSerial(Node head) {
    Queue<String> ans = new LinkedList<>();
    ins(head, ans);
    return ans;
  }

  private static void ins(Node head, Queue<String> ans) {
    if (head == null)
      ans.add("#");
    else {
      ins(head.left, ans);
      ans.add(String.valueOf(head.value));
      ins(head.right, ans);
    }
  }

  /**
   * 后序遍历 序列化
   * 
   * @param head
   * @return
   */
  public static Queue<String> posSerial(Node head) {
    Queue<String> ans = new LinkedList<>();
    poss(head, ans);
    return ans;
  }

  private static void poss(Node head, Queue<String> ans) {
    if (head == null) {
      ans.add("#");
    } else {
      poss(head.left, ans);
      poss(head.right, ans);
      ans.add(String.valueOf(head.value));
    }
  }

  /**
   * 先序遍历 反序列化
   * 
   * @param list
   * @return
   */
  public static Node buildByPreQueue(Queue<String> list) {
    if (list == null || list.size() == 0)
      return null;

    return preb(list);
  }

  private static Node preb(Queue<String> list) {
    String value = list.poll();
    if (value == "#")
      return null;

    Node head = new Node(Integer.valueOf(value));
    head.left = preb(list);
    head.right = preb(list);
    return head;
  }

  /**
   * 后序遍历 反序列化
   * 
   * @param list
   * @return
   */
  public static Node buildByPosQueue(Queue<String> list) {
    if (list == null || list.size() == 0)
      return null;
    Stack<String> stack = new Stack<>();
    // 将 list(左右中) -> stack(中右左)
    while (!list.isEmpty()) {
      stack.push(list.poll());
    }
    return posb(stack);
  }

  private static Node posb(Stack<String> s) {
    String value = s.pop();
    if (value == "#") {
      return null;
    }
    Node head = new Node(Integer.valueOf(value));
    head.right = posb(s);
    head.left = posb(s);
    return head;
  }

  /**
   * 层序遍历 序列化
   * 
   * @param head
   * @return
   */
  public static Queue<String> levelSerial(Node head) {
    Queue<String> ans = new LinkedList<>();
    if (head == null)
      ans.add("#");
    else {
      ans.add(String.valueOf(head.value));
      Queue<Node> queue = new LinkedList<>();
      queue.add(head);
      while (!queue.isEmpty()) {
        head = queue.poll();
        if (head.left != null) {
          ans.add(String.valueOf(head.left.value));
          queue.add(head.left);
        } else {
          ans.add("#");
        }
        if (head.right != null) {
          ans.add(String.valueOf(head.right.value));
          queue.add(head.right);
        } else {
          ans.add("#");
        }
      }
    }
    return ans;
  }

  /**
   * 层序遍历 反序列化
   * 
   * @param list
   * @return
   */
  public static Node buildByLevelQueue(Queue<String> list) {
    if (list == null || list.size() == 0)
      return null;

    Node head = generateNode(list.poll());
    Queue<Node> queue = new LinkedList<Node>();
    if (head != null) {
      queue.add(head);
    }

    Node node = null;
    while (!queue.isEmpty()) {
      node = queue.poll();
      node.left = generateNode(list.poll());
      node.right = generateNode(list.poll());
      if (node.left != null) {
        queue.add(node.left);
      }
      if (node.right != null) {
        queue.add(node.right);
      }
    }
    return head;
  }

  private static Node generateNode(String val) {
    if (val == "#") {
      return null;
    }
    return new Node(Integer.valueOf(val));
  }

  // =============== TEST ===============

  public static Node generateRandomBST(int maxLevel, int maxValue) {
    return generate(1, maxLevel, maxValue);
  }

  public static Node generate(int level, int maxLevel, int maxValue) {
    if (level > maxLevel || Math.random() < 0.5) {
      return null;
    }
    Node head = new Node((int) (Math.random() * maxValue));
    head.left = generate(level + 1, maxLevel, maxValue);
    head.right = generate(level + 1, maxLevel, maxValue);
    return head;
  }

  public static boolean isSameValueStructure(Node head1, Node head2) {
    if (head1 == null && head2 != null) {
      return false;
    }
    if (head1 != null && head2 == null) {
      return false;
    }
    if (head1 == null && head2 == null) {
      return true;
    }
    if (head1.value != head2.value) {
      return false;
    }
    return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
  }

  public static void printTree(Node head) {
    System.out.println("Binary Tree:");
    printInOrder(head, 0, "H", 17);
    System.out.println();
  }

  public static void printInOrder(Node head, int height, String to, int len) {
    if (head == null) {
      return;
    }
    printInOrder(head.right, height + 1, "v", len);
    String val = to + head.value + to;
    int lenM = val.length();
    int lenL = (len - lenM) / 2;
    int lenR = len - lenM - lenL;
    val = getSpace(lenL) + val + getSpace(lenR);
    System.out.println(getSpace(height * len) + val);
    printInOrder(head.left, height + 1, "^", len);
  }

  public static String getSpace(int num) {
    String space = " ";
    StringBuffer buf = new StringBuffer("");
    for (int i = 0; i < num; i++) {
      buf.append(space);
    }
    return buf.toString();
  }

  public static void main(String[] args) {
    int maxLevel = 5, maxValue = 100, testTimes = 1000000;
    System.out.println("test begin");
    for (int i = 0; i < testTimes; i++) {
      Node head = generateRandomBST(maxLevel, maxValue);
      Queue<String> pre = preSerial(head);
      Queue<String> pos = posSerial(head);
      Queue<String> level = levelSerial(head);
      Node preBuild = buildByPreQueue(pre);
      Node posBuild = buildByPosQueue(pos);
      Node levelBuild = buildByLevelQueue(level);
      if (!isSameValueStructure(preBuild, posBuild) ||
          !isSameValueStructure(posBuild, levelBuild)) {
        System.out.println("Oops!");
      }
    }
    System.out.println("test finish!");
  }

}
