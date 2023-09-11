package binary_tree;

class Solution12 {
  private static class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int data) {
      this.value = data;
    }
  }

  /**
   * 是否平衡二叉树
   * 
   * @param head
   * @return
   */
  public static boolean isBalanced1(Node head) {
    boolean[] ans = new boolean[1];
    ans[0] = true;
    process1(head, ans);
    return ans[0];
  }

  private static int process1(Node head, boolean[] ans) {
    if (!ans[0] || head == null)
      return -1;
    int lHeight = process1(head.left, ans);
    int rHeight = process1(head.right, ans);
    if (Math.abs(lHeight - rHeight) > 1)
      ans[0] = false;

    return Math.max(lHeight, rHeight) + 1;
  }

  public static boolean isBalanced2(Node head) {
    return process(head).isBalanced;
  }

  private static class Info {
    public boolean isBalanced;
    public int height;

    public Info(boolean i, int h) {
      isBalanced = i;
      height = h;
    }
  }

  private static Info process(Node x) {
    if (x == null)
      return new Info(true, 0);
    Info lInfo = process(x.left);
    Info rInfo = process(x.right);

    int height = Math.max(lInfo.height, rInfo.height) + 1;
    boolean isBalanced = true;
    if (!lInfo.isBalanced || !rInfo.isBalanced)
      isBalanced = false;

    if (Math.abs(lInfo.height - rInfo.height) > 1)
      isBalanced = false;
    return new Info(isBalanced, height);
  }

  // ============ TEST ============
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
    for (int i = 0; i < testTimes; i++) {
      Node head = generateRandomBST(maxLevel, maxValue);
      if (isBalanced1(head) != isBalanced2(head)) {
        System.out.println("Oops!");
      }
    }
    System.out.println("finish!");
  }
}
