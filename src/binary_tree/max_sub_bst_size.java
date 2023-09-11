package binary_tree;

import java.util.ArrayList;

class Solution14 {
  private static class TreeNode {
    public int val;
    public TreeNode left, right;

    public TreeNode(int value) {
      val = value;
    }
  }

  public static int largestBSTSubtree(TreeNode head) {
    if (head == null) {
      return 0;
    }
    return process(head).maxBSTSubtreeSize;
  }

  private static class Info {
    public int maxBSTSubtreeSize, allSize, max, min;

    public Info(int maxBSTSubtreeSize, int allSize, int max, int min) {
      this.maxBSTSubtreeSize = maxBSTSubtreeSize;
      this.allSize = allSize;
      this.max = max;
      this.min = min;
    }
  }

  private static Info process(TreeNode x) {
    if (x == null)
      return null;

    Info lInfo = process(x.left);
    Info rInfo = process(x.right);
    int max = x.val, min = x.val;
    int allSize = 1;
    if (lInfo != null) {
      max = Math.max(max, lInfo.max);
      min = Math.min(min, lInfo.min);
      allSize += lInfo.allSize;
    }
    if (rInfo != null) {
      max = Math.max(max, rInfo.max);
      min = Math.min(min, rInfo.min);
      allSize += rInfo.allSize;
    }

    int p1 = -1;
    if (lInfo != null) {
      p1 = lInfo.maxBSTSubtreeSize;
    }
    int p2 = -1;
    if (rInfo != null) {
      p2 = rInfo.maxBSTSubtreeSize;
    }

    int p3 = -1;
    boolean leftBST = (lInfo == null ? true : (lInfo.maxBSTSubtreeSize == lInfo.allSize));
    boolean rightBST = (rInfo == null ? true : (rInfo.maxBSTSubtreeSize == rInfo.allSize));

    if (leftBST && rightBST) {
      boolean leftMaxLessX = (lInfo == null ? true : (lInfo.max < x.val));
      boolean rightMinMoreX = (rInfo == null ? true : (x.val < rInfo.min));
      if (leftMaxLessX && rightMinMoreX) {
        int leftSize = lInfo == null ? 0 : lInfo.allSize;
        int rightSize = rInfo == null ? 0 : rInfo.allSize;
        p3 = leftSize + rightSize + 1;
      }
    }
    return new Info(Math.max(p1, Math.max(p2, p3)), allSize, max, min);
  }

  // ============ TEST ============

  // 为了验证
  // 对数器方法
  public static int right(TreeNode head) {
    if (head == null) {
      return 0;
    }
    int h = getBSTSize(head);
    if (h != 0) {
      return h;
    }
    return Math.max(right(head.left), right(head.right));
  }

  // 为了验证
  // 对数器方法
  public static int getBSTSize(TreeNode head) {
    if (head == null) {
      return 0;
    }
    ArrayList<TreeNode> arr = new ArrayList<>();
    in(head, arr);
    for (int i = 1; i < arr.size(); i++) {
      if (arr.get(i).val <= arr.get(i - 1).val) {
        return 0;
      }
    }
    return arr.size();
  }

  // 为了验证
  // 对数器方法
  public static void in(TreeNode head, ArrayList<TreeNode> arr) {
    if (head == null) {
      return;
    }
    in(head.left, arr);
    arr.add(head);
    in(head.right, arr);
  }

  // 为了验证
  // 对数器方法
  public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
    return generate(1, maxLevel, maxValue);
  }

  // 为了验证
  // 对数器方法
  public static TreeNode generate(int level, int maxLevel, int maxValue) {
    if (level > maxLevel || Math.random() < 0.5) {
      return null;
    }
    TreeNode head = new TreeNode((int) (Math.random() * maxValue));
    head.left = generate(level + 1, maxLevel, maxValue);
    head.right = generate(level + 1, maxLevel, maxValue);
    return head;
  }

  // 为了验证
  // 对数器方法
  public static void main(String[] args) {
    int maxLevel = 4;
    int maxValue = 100;
    int testTimes = 1000000;
    System.out.println("测试开始");
    for (int i = 0; i < testTimes; i++) {
      TreeNode head = generateRandomBST(maxLevel, maxValue);
      if (largestBSTSubtree(head) != right(head)) {
        System.out.println("出错了！");
      }
    }
    System.out.println("测试结束");
  }

}