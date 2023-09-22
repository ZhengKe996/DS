package binary_tree;

import java.util.LinkedList;

class Solution16 {
  private static class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int v) {
      val = v;
    }
  }

  /**
   * 非递归实现 队列
   * 
   * @param head
   * @return
   */
  public static boolean isCompleteTree1(TreeNode head) {
    if (head == null)
      return true;

    LinkedList<TreeNode> queue = new LinkedList<>();
    boolean leaf = false;
    TreeNode l = null;
    TreeNode r = null;
    queue.add(head);
    while (!queue.isEmpty()) {
      head = queue.poll(); // 出对头;
      l = head.left;
      r = head.right;
      // 如果遇到了不双全的节点之后，又发现当前节点不是叶节点；
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

  // 二叉树递归套路实现;
  public static boolean isCompleteTree2(TreeNode head) {
    return process(head).isCBT;
  }

  public static class Info {
    public boolean isFull, isCBT;
    public int height;

    public Info(boolean full, boolean cbt, int h) {
      isFull = full;
      isCBT = cbt;
      height = h;
    }
  }

  public static Info process(TreeNode x) {
    if (x == null)
      return new Info(true, true, 0);

    Info lInfo = process(x.left);
    Info rInfo = process(x.right);
    int height = Math.max(lInfo.height, rInfo.height) + 1;
    boolean isFull = lInfo.isFull && rInfo.isFull && lInfo.height == rInfo.height;
    boolean isCBT = false;
    if (lInfo.isFull && rInfo.isFull && lInfo.height == rInfo.height)
      isCBT = true;
    else if (lInfo.isCBT && rInfo.isFull && lInfo.height == rInfo.height + 1)
      isCBT = true;
    else if (lInfo.isFull && rInfo.isFull && lInfo.height == rInfo.height + 1)
      isCBT = true;
    else if (lInfo.isFull && rInfo.isCBT && lInfo.height == rInfo.height)
      isCBT = true;

    return new Info(isFull, isCBT, height);
  }

  /**
   * 以下为对数器
   */
  public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
    return generate(1, maxLevel, maxValue);
  }

  public static TreeNode generate(int level, int maxLevel, int maxValue) {
    if (level > maxLevel || Math.random() < 0.5) {
      return null;
    }
    TreeNode head = new TreeNode((int) (Math.random() * maxValue));
    head.left = generate(level + 1, maxLevel, maxValue);
    head.right = generate(level + 1, maxLevel, maxValue);
    return head;
  }

  public static void main(String[] args) {
    int maxLevel = 5;
    int maxValue = 100;
    int testTimes = 1000000;
    for (int i = 0; i < testTimes; i++) {
      TreeNode head = generateRandomBST(maxLevel, maxValue);
      if (isCompleteTree1(head) != isCompleteTree2(head)) {
        System.out.println("Oops!");
      }
    }
    System.out.println("finish!");
  }
}
