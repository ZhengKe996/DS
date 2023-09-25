package binary_tree;

import java.util.ArrayList;
import java.util.List;

class Solution18 {
  // employee struct
  private static class Employee {
    public int happys;
    public List<Employee> nexts;

    public Employee(int h) {
      this.happys = h;
      nexts = new ArrayList<>();
    }
  }

  /**
   * 
   * @param boss
   * @return
   */
  public static int maxHappy1(Employee boss) {
    if (boss == null)
      return 0;
    return process1(boss, false);
  }

  /**
   * 当前节点current， up == true 代表领导来，此时的current提供的最大快乐值？
   * up == false 代表领导不来，此时的current 提供的最大快乐值？
   * 
   * @param cur
   * @param up
   * @return
   */
  private static int process1(Employee cur, boolean up) {
    if (up) { // up 为 true，代表领导来；
      int ans = 0;
      for (Employee next : cur.nexts)
        ans += process1(next, false);
      return ans;
    } else {// up 为 false，代表领导不来；
      int p1 = cur.happys;
      int p2 = 0;
      for (Employee next : cur.nexts) {
        p1 += process1(next, true);
        p2 += process1(next, false);
      }

      return Math.max(p1, p2);
    }
  }

  public static int maxHappy2(Employee node) {
    Info info = process2(node);
    return Math.max(info.no, info.yes);
  }

  private static class Info {
    public int yes, no; // yes 代表当前节点领导来时的最大快乐值，no 代表领导不来的最大快来值；

    public Info(int y, int n) {
      this.yes = y;
      this.no = n;
    }
  }

  private static Info process2(Employee x) {
    if (x == null)
      return new Info(0, 0);

    int no = 0;
    int yes = x.happys;
    for (Employee next : x.nexts) {
      Info nextInfo = process2(next);
      no += Math.max(nextInfo.no, nextInfo.yes);
      yes += nextInfo.no;
    }

    return new Info(yes, no);
  }

  // =========== TEST ===========
  private static void generateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
    if (level > maxLevel)
      return;

    int nextSize = (int) (Math.random() * (maxNexts + 1));
    for (int i = 0; i < nextSize; i++) {
      Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
      e.nexts.add(next);
      generateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
    }
  }

  public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
    if (Math.random() < 0.02)
      return null;
    Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
    generateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
    return boss;
  }

  public static void main(String[] args) {
    int maxLevel = 4, maxNexts = 7, maxHappy = 100, testTimes = 100000;

    for (int i = 0; i < testTimes; i++) {
      Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
      if (maxHappy1(boss) != maxHappy2(boss)) {
        System.out.println("Oops!");
      }
    }
    System.out.println("finish!");
  }
}