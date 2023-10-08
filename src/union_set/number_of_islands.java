package union_set;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

class Solution3 {
  /**
   * 暴力递归 O(m*N)
   * 
   * @param grid
   * @return
   */
  public int numIslands1(char[][] grid) {
    int islands = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == '1') {
          islands++;
          infect(grid, i, j); // 感染
        }
      }
    }
    return islands;
  }

  // 从(i,j)这个位置出发，把所有连成一片的'1'字符，变成0（acsii）
  private void infect(char[][] grid, int i, int j) {
    if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || grid[i][j] != '1') {
      return;
    }
    grid[i][j] = 0;
    infect(grid, i - 1, j);
    infect(grid, i + 1, j);
    infect(grid, i, j - 1);
    infect(grid, i, j + 1);
  }

  /**
   * 表实现的并查集（常数时间较大）
   * 
   * @param grid
   * @return
   */
  public int numIslands2(char[][] grid) {
    int row = grid.length;
    int col = grid[0].length;
    Dot[][] dots = new Dot[row][col];
    List<Dot> dotList = new ArrayList<>();

    // 将字符表 化为 其他类型的表（0处为null）
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (grid[i][j] == '1') {
          dots[i][j] = new Dot();
          dotList.add(dots[i][j]);
        }
      }
    }

    // 建并查集
    UnionFind1<Dot> uf = new UnionFind1<>(dotList);

    for (int j = 1; j < col; j++) {
      // (0,j) (0,0)跳过了 (0,1) (0,2) (0,3)
      if (grid[0][j - 1] == '1' && grid[0][j] == '1') {
        uf.union(dots[0][j - 1], dots[0][j]);
      }
    }

    for (int i = 1; i < row; i++) {
      if (grid[i - 1][0] == '1' && grid[i][0] == '1') {
        uf.union(dots[i - 1][0], dots[i][0]);
      }
    }

    for (int i = 1; i < row; i++) {
      for (int j = 1; j < col; j++) {
        if (grid[i][j] == '1') {
          if (grid[i][j - 1] == '1') {
            uf.union(dots[i][j - 1], dots[i][j]);
          }
          if (grid[i - 1][j] == '1') {
            uf.union(dots[i - 1][j], dots[i][j]);
          }
        }
      }
    }
    return uf.sets();
  }

  private static class Dot {

  }

  private static class Node<V> {
    V value;

    public Node(V v) {
      value = v;
    }

  }

  private static class UnionFind1<V> {
    public HashMap<V, Node<V>> nodes;
    public HashMap<Node<V>, Node<V>> parents;
    public HashMap<Node<V>, Integer> sizeMap;

    public UnionFind1(List<V> values) {
      nodes = new HashMap<>();
      parents = new HashMap<>();
      sizeMap = new HashMap<>();
      for (V cur : values) {
        Node<V> node = new Node<>(cur);
        nodes.put(cur, node);
        parents.put(node, node);
        sizeMap.put(node, 1);
      }
    }

    public Node<V> findFather(Node<V> cur) {
      Stack<Node<V>> path = new Stack<>();
      while (cur != parents.get(cur)) {
        path.push(cur);
        cur = parents.get(cur);
      }
      while (!path.isEmpty()) {
        parents.put(path.pop(), cur);
      }
      return cur;
    }

    public void union(V a, V b) {
      Node<V> aHead = findFather(nodes.get(a));
      Node<V> bHead = findFather(nodes.get(b));
      if (aHead != bHead) {
        int aSetSize = sizeMap.get(aHead);
        int bSetSize = sizeMap.get(bHead);
        Node<V> big = aSetSize >= bSetSize ? aHead : bHead;
        Node<V> small = big == aHead ? bHead : aHead;
        parents.put(small, big);
        sizeMap.put(big, aSetSize + bSetSize);
        sizeMap.remove(small);
      }
    }

    public int sets() {
      return sizeMap.size();
    }

  }

  /**
   * 数组形式的并查集 优化HashMap的常数时间
   * 
   * @param grid
   * @return
   */
  public static int numIslands3(char[][] grid) {
    int row = grid.length;
    int col = grid[0].length;
    UnionFind2 uf = new UnionFind2(grid);
    for (int j = 1; j < col; j++) {
      if (grid[0][j - 1] == '1' && grid[0][j] == '1') {
        uf.union(0, j - 1, 0, j);
      }
    }
    for (int i = 1; i < row; i++) {
      if (grid[i - 1][0] == '1' && grid[i][0] == '1') {
        uf.union(i - 1, 0, i, 0);
      }
    }
    for (int i = 1; i < row; i++) {
      for (int j = 1; j < col; j++) {
        if (grid[i][j] == '1') {
          if (grid[i][j - 1] == '1') {
            uf.union(i, j - 1, i, j);
          }
          if (grid[i - 1][j] == '1') {
            uf.union(i - 1, j, i, j);
          }
        }
      }
    }
    return uf.sets();
  }

  private static class UnionFind2 {
    private int[] parent;
    private int[] size;
    private int[] help;
    private int col;
    private int sets;

    public UnionFind2(char[][] board) {
      col = board[0].length;
      sets = 0;
      int row = board.length;
      int len = row * col;
      parent = new int[len];
      size = new int[len];
      help = new int[len];
      for (int r = 0; r < row; r++) {
        for (int c = 0; c < col; c++) {
          if (board[r][c] == '1') {
            int i = index(r, c);
            parent[i] = i;
            size[i] = 1;
            sets++;
          }
        }
      }
    }

    // (r,c) -> i
    private int index(int r, int c) {
      return r * col + c;
    }

    // 原始位置 -> 下标
    private int find(int i) {
      int hi = 0;
      while (i != parent[i]) {
        help[hi++] = i;
        i = parent[i];
      }
      for (hi--; hi >= 0; hi--) {
        parent[help[hi]] = i;
      }
      return i;
    }

    public void union(int r1, int c1, int r2, int c2) {
      int i1 = index(r1, c1);
      int i2 = index(r2, c2);
      int f1 = find(i1);
      int f2 = find(i2);
      if (f1 != f2) {
        if (size[f1] >= size[f2]) {
          size[f1] += size[f2];
          parent[f2] = f1;
        } else {
          size[f2] += size[f1];
          parent[f1] = f2;
        }
        sets--;
      }
    }

    public int sets() {
      return sets;
    }

  }
}
