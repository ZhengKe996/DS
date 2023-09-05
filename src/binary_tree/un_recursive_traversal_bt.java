package binary_tree;

import java.util.Stack;

class Solution2 {
  private static class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int v) {
      value = v;
    }
  }

  /**
   * 单栈 前序遍历（非递归）
   * 
   * @param head
   */
  public static void pre(Node head) {
    System.out.print("pre-order: ");
    if (head != null) {
      Stack<Node> stack = new Stack<>();
      stack.push(head);
      while (!stack.isEmpty()) {
        head = stack.pop();
        System.out.print(head.value + " ");
        if (head.right != null)
          stack.push(head.right);
        if (head.left != null)
          stack.push(head.left);
      }
    }
    System.out.println();
  }

  /**
   * 单栈 中序遍历（非递归）
   * 
   * @param head
   */
  public static void in(Node head) {
    System.out.print("in-order: ");
    if (head != null) {
      Stack<Node> stack = new Stack<Node>();
      while (!stack.isEmpty() || head != null) {
        if (head != null) {
          stack.push(head);
          head = head.left;
        } else {
          head = stack.pop();
          System.out.print(head.value + " ");
          head = head.right;
        }
      }
    }
    System.out.println();
  }

  /**
   * 单栈 后序遍历（非递归）
   * 
   * @param head
   */
  public static void pos(Node head) {
    System.out.print("pos-order: ");

    if (head != null) {
      Stack<Node> stack = new Stack<>();
      stack.push(head);
      Node cur = null;
      while (!stack.isEmpty()) {
        cur = stack.peek();
        if (cur.left != null && head != cur.right && head != cur.left)
          stack.push(cur.left);
        else if (cur.right != null && head != cur.right)
          stack.push(cur.right);
        else {
          System.out.print(stack.pop().value + " ");
          head = cur;
        }
      }
    }
    System.out.println();

  }

  /**
   * 双栈 后序遍历（非递归）
   * 
   * @param head
   */
  public static void pos1(Node head) {
    System.out.print("pos-order: ");
    if (head != null) {
      Stack<Node> s1 = new Stack<Node>();
      Stack<Node> s2 = new Stack<Node>();
      s1.push(head);
      while (!s1.isEmpty()) {
        head = s1.pop(); // 头 右 左
        s2.push(head);
        if (head.left != null) {
          s1.push(head.left);
        }
        if (head.right != null) {
          s1.push(head.right);
        }
      }
      // 左 右 头
      while (!s2.isEmpty()) {
        System.out.print(s2.pop().value + " ");
      }
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Node head = new Node(1);
    head.left = new Node(2);
    head.right = new Node(3);
    head.left.left = new Node(4);
    head.left.right = new Node(5);
    head.right.left = new Node(6);
    head.right.right = new Node(7);

    pre(head);
    System.out.println("========");
    in(head);
    System.out.println("========");
    pos1(head);
    System.out.println("========");
    pos(head);
    System.out.println("========");
  }
}
