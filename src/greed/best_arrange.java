package greed;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
  private static class Program {
    public int start, end;

    public Program(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

  /**
   * 暴力解 尝试所有 返回最优解
   * 
   * @param programs
   * @return
   */
  public static int bestArrange1(Program[] programs) {
    if (programs == null || programs.length == 0)
      return 0;

    return process(programs, 0, 0);
  }

  /**
   * 
   * @param programs 还剩下的会议都放在programs里
   * @param done     done之前已经安排了多少会议的数量
   * @param timeLine timeLine目前来到的时间点是什么
   * @return
   */
  private static int process(Program[] programs, int done, int timeLine) {
    if (programs.length == 0)
      return done;

    // 还剩下的会议
    int max = done;
    for (int i = 0; i < programs.length; i++) {
      if (programs[i].start >= timeLine) {
        Program[] next = copyButExcept(programs, i);
        max = Math.max(max, process(next, done + 1, programs[i].end));
      }
    }
    return max;
  }

  private static Program[] copyButExcept(Program[] programs, int i) {
    Program[] ans = new Program[programs.length - 1];
    int index = 0;
    for (int k = 0; k < programs.length; k++) {
      if (k != i) {
        ans[index++] = programs[k];
      }
    }
    return ans;
  }

  /**
   * 贪心：
   * 思路（有效）：选结束时间最早
   * 
   * @param programs
   * @return
   */
  public static int bestArrange2(Program[] programs) {
    Arrays.sort(programs, (Program o1, Program o2) -> o1.end - o2.end);
    int timeLine = 0;
    int result = 0;
    for (int i = 0; i < programs.length; i++) {
      if (timeLine <= programs[i].start) {
        result++;
        timeLine = programs[i].end;
      }
    }
    return result;
  }

  public static class ProgramComparator implements Comparator<Program> {

    @Override
    public int compare(Program o1, Program o2) {
      return o1.end - o2.end;
    }
  }

  public static Program[] generatePrograms(int programSize, int timeMax) {
    Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
    for (int i = 0; i < ans.length; i++) {
      int r1 = (int) (Math.random() * (timeMax + 1));
      int r2 = (int) (Math.random() * (timeMax + 1));
      if (r1 == r2) {
        ans[i] = new Program(r1, r1 + 1);
      } else {
        ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    int programSize = 12;
    int timeMax = 20;
    int timeTimes = 1000000;
    for (int i = 0; i < timeTimes; i++) {
      Program[] programs = generatePrograms(programSize, timeMax);
      if (bestArrange1(programs) != bestArrange2(programs)) {
        System.out.println("Oops!");
      }
    }
    System.out.println("finish!");
  }
}
