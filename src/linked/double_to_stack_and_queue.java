package linked;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Solution {

  private static class Node<T> {
    public T value;
    public Node<T> pre;
    public Node<T> next;

    public Node(T value) {
      this.value = value;
    }
  }

  /**
   * 双向链表实现双端队列
   */
  public static class DoubleEndsQueue<T> {
    public Node<T> head, tail;

    /**
     * 插入数据到队列头部
     * 
     * @param value
     */
    public void addFromHead(T value) {
      Node<T> cur = new Node<T>(value);
      if (head == null) {
        head = cur;
        tail = cur;
      } else {
        cur.next = head;
        head.pre = cur;

        head = cur;
      }
    }

    /**
     * 插入数据到队列尾部
     * 
     * @param value
     */
    public void addFromBottom(T value) {
      Node<T> cur = new Node<T>(value);
      if (head == null) {
        head = cur;
        tail = cur;
      } else {
        cur.pre = tail;
        tail.next = cur;

        tail = cur;
      }
    }

    /**
     * 双端队列 对头出队
     * 
     * @return
     */
    public T popFromHead() {
      if (head == null)
        return null;
      Node<T> cur = head;
      // 队列中只有一个节点
      if (head == tail) {
        head = null;
        tail = null;
      } else {
        head = head.next;

        cur.next = null;
        head.pre = null;
      }
      return cur.value;
    }

    /**
     * 双端队列 对尾出队
     * 
     * @return
     */
    public T popFromBottom() {
      if (head == null)
        return null;

      Node<T> cur = head;
      // 队列只有一个元素的情况
      if (head == tail) {
        head = null;
        tail = null;
      } else {
        tail = tail.pre;

        tail.next = null;
        cur.pre = null;
      }
      return cur.value;
    }

    public boolean isEmpty() {
      return head == null;
    }

  }

  public static class MyStack<T> {
    private DoubleEndsQueue<T> queue;

    public MyStack() {
      queue = new DoubleEndsQueue<T>();
    }

    public void push(T value) {
      queue.addFromHead(value);
    }

    public T pop() {
      return queue.popFromHead();
    }

    public boolean isEmpty() {
      return queue.isEmpty();
    }
  }

  public static class MyQueue<T> {
    private DoubleEndsQueue<T> queue;

    public MyQueue() {
      queue = new DoubleEndsQueue<T>();
    }

    public void push(T value) {
      queue.addFromBottom(value);
    }

    public T poll() {
      return queue.popFromHead();
    }

    public boolean isEmpty() {
      return queue.isEmpty();
    }
  }

  // ============ TEST ============
  public boolean isEqual(Integer o1, Integer o2) {
    if (o1 == null && o2 != null) {
      return false;
    }
    if (o1 != null && o2 == null) {
      return false;
    }
    if (o1 == null && o2 == null) {
      return true;
    }
    return o1.equals(o2);
  }

  public static void main(String[] args) {
    Solution solution = new Solution();

    int oneTestDataNum = 100;
    int value = 10000;
    int testTimes = 100000;

    for (int i = 0; i < testTimes; i++) {

      MyStack<Integer> myStack = new MyStack<>();
      MyQueue<Integer> myQueue = new MyQueue<>();
      Stack<Integer> stack = new Stack<>();
      Queue<Integer> queue = new LinkedList<>();
      for (int j = 0; j < oneTestDataNum; j++) {
        int nums = (int) (Math.random() * value);
        if (stack.isEmpty()) {
          myStack.push(nums);
          stack.push(nums);
        } else {
          if (Math.random() < 0.5) {
            myStack.push(nums);
            stack.push(nums);
          } else {
            if (!solution.isEqual(myStack.pop(), stack.pop())) {
              System.out.println("oops!");
            }
          }
        }
        int numq = (int) (Math.random() * value);
        if (queue.isEmpty()) {
          myQueue.push(numq);
          queue.offer(numq);
        } else {
          if (Math.random() < 0.5) {
            myQueue.push(numq);
            queue.offer(numq);
          } else {
            if (!solution.isEqual(myQueue.poll(), queue.poll())) {
              System.out.println("oops!");
            }
          }
        }
      }
    }
    System.out.println("finish!");
  }
}
