package binary_tree;

import java.util.LinkedList;

class Solution10 {
  private static class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int data) {
      this.value = data;
    }
  }

  /**
   * 是否完全二叉树 非递归
   * 
   * @param head
   * @return
   */
  public static boolean isCBT1(Node head) {
    if (head == null)
      return true;
    LinkedList<Node> queue = new LinkedList<>();
    boolean leaf = false;
    Node l = null, r = null;
    queue.add(head);
    while (!queue.isEmpty()) {
      head = queue.poll();
      l = head.left;
      r = head.right;
      if ((leaf && (l != null || r != null)) || (l == null && r != null))
        return false;

      if (l != null)
        queue.add(l);
      if (r != null)
        queue.add(r);

      if (l == null || r == null)
        leaf = true;
    }
    return true;
  }

  /**
   * 是否完全二叉树 递归
   * 
   * @param head
   * @return
   */
  public static boolean isCBT2(Node head) {
    if (head == null)
      return true;
    return process(head).isCBT;
  }

  private static class Info {
    public boolean isFull, isCBT;
    public int height;

    public Info(boolean full, boolean cbt, int h) {
      isFull = full;
      isCBT = cbt;
      height = h;
    }
  }

  private static Info process(Node x) {
    if (x == null)
      return new Info(true, true, 0);
    Info lInfo = process(x.left);
    Info rInfo = process(x.right);

    int height = Math.max(lInfo.height, rInfo.height) + 1;
    boolean isFull = lInfo.isFull && rInfo.isFull && lInfo.height == rInfo.height;
    boolean isCBT = false;

    if (isFull)
      isCBT = true;
    else { // 以x为头整棵树，不满
      if (lInfo.isCBT && rInfo.isCBT) {
        if (lInfo.isCBT && rInfo.isFull && lInfo.height == rInfo.height + 1)
          isCBT = true;
        if (lInfo.isFull && rInfo.isFull && lInfo.height == rInfo.height + 1)
          isCBT = true;
        if (lInfo.isFull && rInfo.isCBT && lInfo.height == rInfo.height)
          isCBT = true;
      }
    }
    return new Info(isFull, isCBT, height);
  }

  // ============== TEST ==============
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

  public static void main(String[] args) {
    int maxLevel = 5;
    int maxValue = 100;
    int testTimes = 1000000;
    for (int i = 0; i < testTimes; i++) {
      Node head = generateRandomBST(maxLevel, maxValue);
      if (isCBT1(head) != isCBT2(head)) {
        System.out.println("Oops!");
      }
    }
    System.out.println("finish!");
  }
}
