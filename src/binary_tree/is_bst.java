package binary_tree;

import java.util.ArrayList;

class Solution11 {
  private static class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int data) {
      this.value = data;
    }
  }

  /**
   * 是否搜索二叉树 中序遍历
   * 
   * @param head
   * @return
   */
  public static boolean isBST1(Node head) {
    if (head == null)
      return true;
    ArrayList<Node> arr = new ArrayList<>();
    in(head, arr);
    for (int i = 1; i < arr.size(); i++) {
      if (arr.get(i).value <= arr.get(i - 1).value)
        return false;
    }
    return true;
  }

  private static void in(Node head, ArrayList<Node> arr) {
    if (head == null)
      return;
    in(head.left, arr);
    arr.add(head);
    in(head.right, arr);
  }

  public static boolean isBST2(Node head) {
    if (head == null)
      return true;
    return process(head).isBST;
  }

  private static class Info {
    public boolean isBST;
    public int max, min;

    public Info(boolean i, int max, int min) {
      isBST = i;
      this.max = max;
      this.min = min;
    }
  }

  private static Info process(Node x) {
    if (x == null)
      return null;

    Info lInfo = process(x.left);
    Info rInfo = process(x.right);

    // // 处理Info
    int max = x.value, min = x.value;

    if (lInfo != null) {
      max = Math.max(max, lInfo.max);
      min = Math.min(min, lInfo.min);
    }
    if (rInfo != null) {
      max = Math.max(max, rInfo.max);
      min = Math.min(min, rInfo.min);
    }

    boolean isBST = true;
    if (lInfo != null && !lInfo.isBST)
      isBST = false;
    if (rInfo != null && !rInfo.isBST)
      isBST = false;

    if (lInfo != null && lInfo.max >= x.value)
      isBST = false;
    if (rInfo != null && rInfo.min <= x.value)
      isBST = false;
    return new Info(isBST, max, min);
  }

  // ================== TEST ==================
  public static Node generateRandomBST(int maxLevel, int maxValue) {
    return generate(1, maxLevel, maxValue);
  }

  // for test
  public static Node generate(int level, int maxLevel, int maxValue) {
    if (level > maxLevel || Math.random() < 0.5) {
      return null;
    }
    Node head = new Node((int) (Math.random() * maxValue));
    head.left = generate(level + 1, maxLevel, maxValue);
    head.right = generate(level + 1, maxLevel, maxValue);
    return head;
  }

  public static void main(String[] args) {
    int maxLevel = 4;
    int maxValue = 100;
    int testTimes = 1000000;
    for (int i = 0; i < testTimes; i++) {
      Node head = generateRandomBST(maxLevel, maxValue);
      if (isBST1(head) != isBST2(head)) {
        System.out.println("Oops!");
      }
    }
    System.out.println("finish!");
  }
}
