package stack;

import java.util.Stack;

class Solution {
  public static class MyStack1 {
    private Stack<Integer> data;
    private Stack<Integer> min;

    public MyStack1() {
      data = new Stack<>();
      min = new Stack<>();
    }

    public int getmin() {
      if (min.isEmpty()) {
        throw new RuntimeException("Your stack is empty.");
      }
      return min.peek();
    }

    public void push(int value) {
      if (min.isEmpty() || value <= this.getmin()) {
        min.push(value);
      }
      data.push(value);
    }

    public int pop() {
      if (data.isEmpty()) {
        throw new RuntimeException("Your stack is empty.");
      }
      int value = data.pop();
      if (value == getmin()) {
        min.pop();
      }
      return value;
    }

  }

  public static class MyStack2 {
    private Stack<Integer> data;
    private Stack<Integer> min;

    public MyStack2() {
      data = new Stack<>();
      min = new Stack<>();
    }

    public int getmin() {
      if (min.isEmpty()) {
        throw new RuntimeException("Your stack is empty.");
      }
      return min.peek();
    }

    public void push(int value) {
      if (min.empty() || value < getmin()) {
        min.push(value);
      } else {
        min.push(min.peek());
      }
      data.push(value);
    }

    public int pop() {
      if (data.isEmpty()) {
        throw new RuntimeException("Your stack is empty.");
      }
      min.pop();
      return data.pop();
    }
  }

  public static void main(String[] args) {
    MyStack1 stack1 = new MyStack1();
    stack1.push(3);
    System.out.println(stack1.getmin());
    stack1.push(4);
    System.out.println(stack1.getmin());
    stack1.push(1);
    System.out.println(stack1.getmin());
    System.out.println(stack1.pop());
    System.out.println(stack1.getmin());

    System.out.println("=============");

    MyStack2 stack2 = new MyStack2();
    stack2.push(3);
    System.out.println(stack2.getmin());
    stack2.push(4);
    System.out.println(stack2.getmin());
    stack2.push(1);
    System.out.println(stack2.getmin());
    System.out.println(stack2.pop());
    System.out.println(stack2.getmin());
  }
}