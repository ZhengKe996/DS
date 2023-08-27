package heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution3 {
  /**
   * 线段树（暴力）
   * 时间复杂度 O((max−min)*N)
   * 
   * @param lines
   * @return
   */
  public int maxCover1(int[][] lines) {
    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    for (int i = 0; i < lines.length; i++) {
      min = Math.min(min, lines[i][0]); // lines[i][0] Start 位置
      max = Math.max(max, lines[i][1]); // lines[i][1] End 位置
    }

    int cover = 0;
    for (double p = min + 0.5; p < max; p += 1) {
      int cur = 0;
      for (int i = 0; i < lines.length; i++) {
        if (lines[i][0] < p && lines[i][1] > p)
          cur++;
      }
      cover = Math.max(cover, cur);
    }
    return cover;
  }

  public class Line {
    public int start, end;

    public Line(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

  public class StartComparator implements Comparator<Line> {

    @Override
    public int compare(Line o1, Line o2) {
      return o1.start - o2.start;
    }

  }

  /**
   * 最小堆实现
   * 
   * @param m
   * @return
   */
  public int maxCover2(int[][] m) {
    Line[] lines = new Line[m.length];
    for (int i = 0; i < m.length; i++)
      lines[i] = new Line(m[i][0], m[i][1]);

    Arrays.sort(lines, new StartComparator());

    PriorityQueue<Integer> heap = new PriorityQueue<>();
    int max = 0;
    for (int i = 0; i < lines.length; i++) {
      while (!heap.isEmpty() && heap.peek() <= lines[i].start)
        heap.poll();// 把堆里 所有小于 cur.start 的东西都弹出来
      heap.add(lines[i].end);
      max = Math.max(max, heap.size());
    }
    return max;
  }

  public int maxCover3(int[][] lines) {
    Arrays.sort(lines, (a, b) -> (a[0] - b[0]));

    PriorityQueue<Integer> heap = new PriorityQueue<>();

    int max = 0;
    for (int[] line : lines) {
      while (!heap.isEmpty() && heap.peek() <= line[0])
        heap.poll();
      heap.add(line[1]);
      max = Math.max(max, heap.size());
    }
    return max;
  }

  // ================== TEST ==================
  public int[][] generateLines(int N, int L, int R) {
    int size = (int) (Math.random() * N) + 1;
    int[][] ans = new int[size][2];
    for (int i = 0; i < size; i++) {
      int a = L + (int) (Math.random() * (R - L + 1));
      int b = L + (int) (Math.random() * (R - L + 1));
      if (a == b) {
        b = a + 1;
      }
      ans[i][0] = Math.min(a, b);
      ans[i][1] = Math.max(a, b);
    }
    return ans;
  }

  public static void main(String[] args) {
    Solution3 solution = new Solution3();

    System.out.println("TEST Begin");
    int N = 100, L = 0, R = 200, testTimes = 200000;
    for (int i = 0; i < testTimes; i++) {
      int[][] lines = solution.generateLines(N, L, R);
      int ans1 = solution.maxCover1(lines);
      int ans2 = solution.maxCover2(lines);
      int ans3 = solution.maxCover3(lines);
      if (ans1 != ans2 || ans1 != ans3 || ans2 != ans3) {
        System.out.println("Oops!");
      }
    }
    System.out.println("TEST End");
  }
}
