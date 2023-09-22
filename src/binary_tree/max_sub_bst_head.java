package binary_tree;

import java.util.ArrayList;

class Solution17 {
  private static class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int data) {
      this.value = data;
    }
  }

  /**
   * 中序遍历 实现
   * 
   * @param head
   * @return
   */
  private static int getBSTSize(Node head) {
    if (head == null)
      return 0;

    ArrayList<Node> arr = new ArrayList<>();
    in(head, arr);
    for (int i = 1; i < arr.size(); i++) {
      if (arr.get(i).value <= arr.get(i - 1).value) {
        return 0;
      }
    }
    return arr.size();
  }

  private static void in(Node head, ArrayList<Node> arr) {
    if (head == null) {
      return;
    }
    in(head.left, arr);
    arr.add(head);
    in(head.right, arr);
  }

  public static Node maxSubBSTHead1(Node head) {
    if (head == null)
      return null;

    if (getBSTSize(head) != 0)
      return head;

    Node lANS = maxSubBSTHead1(head.left);
    Node rANS = maxSubBSTHead1(head.right);
    return getBSTSize(lANS) >= getBSTSize(rANS) ? lANS : rANS;
  }

  public static Node maxSubBSTHead2(Node head) {
    if (head == null)
      return null;

    return process(head).maxSubBSTHead;
  }

  private static class Info {
    public Node maxSubBSTHead;
    public int maxSubBSTSize;
    public int min;
    public int max;

    public Info(Node h, int size, int mi, int ma) {
      maxSubBSTHead = h;
      maxSubBSTSize = size;
      min = mi;
      max = ma;
    }
  }

  public static Info process(Node x) {
    if (x == null)
      return null;

    Info lInfo = process(x.left);
    Info rInfo = process(x.right);
    int min = x.value;
    int max = x.value;
    Node maxSubBSTHead = null;
    int maxSubBSTSize = 0;
    if (lInfo != null) {
      min = Math.min(min, lInfo.min);
      max = Math.max(max, lInfo.max);
      maxSubBSTHead = lInfo.maxSubBSTHead;
      maxSubBSTSize = lInfo.maxSubBSTSize;
    }
    if (rInfo != null) {
      min = Math.min(min, rInfo.min);
      max = Math.max(max, rInfo.max);
      if (rInfo.maxSubBSTSize > maxSubBSTSize) {
        maxSubBSTHead = rInfo.maxSubBSTHead;
        maxSubBSTSize = rInfo.maxSubBSTSize;
      }
    }
    if ((lInfo == null ? true : (lInfo.maxSubBSTHead == x.left && lInfo.max < x.value))
        && (rInfo == null ? true : (rInfo.maxSubBSTHead == x.right && rInfo.min > x.value))) {
      maxSubBSTHead = x;
      maxSubBSTSize = (lInfo == null ? 0 : lInfo.maxSubBSTSize)
          + (rInfo == null ? 0 : rInfo.maxSubBSTSize) + 1;
    }
    return new Info(maxSubBSTHead, maxSubBSTSize, min, max);
  }

  /**
   * 以下为测试~~~~~
   * 
   */
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
      if (maxSubBSTHead1(head) != maxSubBSTHead2(head)) {
        System.out.println("Oops!");
      }
    }
    System.out.println("finish!");
  }
}
