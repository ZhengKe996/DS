package binary_tree;

import java.util.LinkedList;
import java.util.Queue;

class Solution3 {
  private static class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int v) {
      value = v;
    }
  }

  /**
   * 二叉树的层序遍历
   * 
   * @param head
   */
  public static void level(Node head) {
    if (head == null)
      return;
    Queue<Node> queue = new LinkedList<>();
    queue.add(head);
    while (!queue.isEmpty()) {
      Node cur = queue.poll();
      System.out.print(cur.value + " ");
      if (cur.left != null)
        queue.add(cur.left);
      if (cur.right != null)
        queue.add(cur.right);
    }
  }

  public static void main(String[] args) {
    Node head = new Node(1);
    head.left = new Node(2);
    head.right = new Node(3);
    head.left.left = new Node(4);
    head.left.right = new Node(5);
    head.right.left = new Node(6);
    head.right.right = new Node(7);

    level(head);
    System.out.println("\n==================");
  }

}
