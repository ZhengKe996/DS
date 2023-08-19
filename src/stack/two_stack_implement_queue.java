package stack;

import java.util.Stack;

class Solution {

  private static class TwoStacksQueue {
    public Stack<Integer> push;
    public Stack<Integer> pop;

    public TwoStacksQueue() {
      push = new Stack<>();
      pop = new Stack<>();
    }

    /**
     * push 栈向pop栈倒数据
     * 【注】：倒数据的过程中 不能进行任何插入、删除操作; Pop栈不为空 不能进行倒数据操作！
     */
    private void pushToPop() {
      if (pop.empty()) {
        while (!push.empty()) {
          pop.push(push.pop());
        }
      }
    }

    /**
     * 入队
     * 
     * @param newValue
     */
    public void add(int newValue) {
      push.push(newValue);
      pushToPop();
    }

    /**
     * 出队
     * 
     * @return
     */
    public int poll() {
      if (pop.empty() && push.empty()) {
        throw new RuntimeException("Queue is empty");
      }
      pushToPop();
      return pop.pop();
    }

    /**
     * 返回队头
     * 
     * @return
     */
    public int peek() {
      if (pop.empty() && push.empty()) {
        throw new RuntimeException("Queue is empty");
      }
      pushToPop();
      return pop.peek();
    }
  }

  public static void main(String[] args) {
    TwoStacksQueue test = new TwoStacksQueue();
    test.add(1);
    test.add(2);
    test.add(3);
    System.out.println(test.peek());
    System.out.println(test.poll());
    System.out.println(test.peek());
    System.out.println(test.poll());
    System.out.println(test.peek());
    System.out.println(test.poll());
  }

}
