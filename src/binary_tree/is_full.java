package binary_tree;

class Solution13 {

  private static class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int data) {
      this.value = data;
    }
  }

  /**
   * 收集整棵树的高度h，和节点数n
   * 只有满二叉树满足 : 2 ^ h - 1 == n
   * 
   * @param head
   * @return
   */
  public static boolean isFull1(Node head) {
    if (head == null)
      return true;
    Info1 all = process1(head);
    return (1 << all.height) - 1 == all.nodes;
  }

  private static class Info1 {
    public int height;
    public int nodes;

    public Info1(int h, int n) {
      height = h;
      nodes = n;
    }
  }

  private static Info1 process1(Node head) {
    if (head == null)
      return new Info1(0, 0);
    Info1 lInfo = process1(head.left);
    Info1 rInfo = process1(head.right);

    int height = Math.max(lInfo.height, rInfo.height) + 1;
    int nodes = lInfo.nodes + rInfo.nodes + 1;
    return new Info1(height, nodes);
  }

  /**
   * 收集子树是否是满二叉树
   * 收集子树的高度
   * 左树满 && 右树满 && 左右树高度一样 -> 整棵树是满的
   * 
   * @param head
   * @return
   */
  public static boolean isFull2(Node head) {
    if (head == null) {
      return true;
    }
    return process2(head).isFull;
  }

  private static class Info2 {
    public boolean isFull;
    public int height;

    public Info2(boolean f, int h) {
      isFull = f;
      height = h;
    }
  }

  private static Info2 process2(Node head) {
    if (head == null)
      return new Info2(true, 0);
    Info2 lInfo = process2(head.left);
    Info2 rInfo = process2(head.right);

    boolean isFull = lInfo.isFull && rInfo.isFull && lInfo.height == rInfo.height;
    int height = Math.max(lInfo.height, rInfo.height) + 1;
    return new Info2(isFull, height);
  }

  // ====================== TEST ======================
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
    int maxLevel = 5;
    int maxValue = 100;
    int testTimes = 1000000;
    System.out.println("测试开始");
    for (int i = 0; i < testTimes; i++) {
      Node head = generateRandomBST(maxLevel, maxValue);
      if (isFull1(head) != isFull2(head)) {
        System.out.println("出错了!");
      }
    }
    System.out.println("测试结束");
  }

}
