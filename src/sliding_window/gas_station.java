package sliding_window;

import java.util.LinkedList;

public class gas_station {

  public static int right(int[] gas, int[] cost) {
    int n = gas.length;
    int i = 0;
    while (i < n) {
      int sumOfGas = 0, sumOfCost = 0;
      int cnt = 0;
      while (cnt < n) {
        int j = (i + cnt) % n;
        sumOfGas += gas[j];
        sumOfCost += cost[j];
        if (sumOfCost > sumOfGas) {
          break;
        }
        cnt++;
      }

      if (cnt == n) {
        return i;
      } else {
        i = i + cnt + 1;
      }
    }
    return -1;

  }

  public static int canCompleteCircuit(int[] gas, int[] cost) {
    boolean[] good = goodArray(gas, cost);
    for (int i = 0; i < gas.length; i++) {
      if (good[i]) {
        return i;
      }
    }
    return -1;
  }

  public static boolean[] goodArray(int[] g, int[] c) {
    int N = g.length;
    int M = N << 1;
    int[] arr = new int[M];
    for (int i = 0; i < N; i++) {
      arr[i] = g[i] - c[i];
      arr[i + N] = g[i] - c[i];
    }
    for (int i = 1; i < M; i++) {
      arr[i] += arr[i - 1];
    }

    LinkedList<Integer> w = new LinkedList<>();
    for (int i = 0; i < N; i++) {
      while (!w.isEmpty() && arr[w.peekLast()] >= arr[i]) {
        w.pollLast();
      }
      w.addLast(i);
    }

    boolean[] ans = new boolean[N];
    for (int offset = 0, i = 0, j = N; j < M; offset = arr[i++], j++) {
      if (arr[w.peekFirst()] - offset >= 0) {
        ans[i] = true;
      }
      if (w.peekFirst() == i) {
        w.pollFirst();
      }
      while (!w.isEmpty() && arr[w.peekLast()] >= arr[j]) {
        w.pollLast();
      }
      w.addLast(j);
    }
    return ans;
  }

  public static void main(String[] args) {
    int[] gas = { 1, 2, 3, 4, 5 };
    int[] cost = { 3, 4, 5, 1, 2 };
    int res = canCompleteCircuit(gas, cost);
    int res2 = right(gas, cost);
    System.out.println(res + " " + res2);
  }
}
