package recursion;

import java.util.ArrayList;
import java.util.List;

public class print_all_permutations {
  public static List<String> permutation1(String s) {
    List<String> ans = new ArrayList<>();
    if (s == null || s.length() == 0)
      return ans;
    char[] str = s.toCharArray();
    ArrayList<Character> rest = new ArrayList<Character>();
    for (char cha : str)
      rest.add(cha);

    String path = "";
    f(rest, path, ans);
    return ans;
  }

  private static void f(ArrayList<Character> rest, String path, List<String> ans) {
    if (rest.isEmpty())
      ans.add(path);
    else {
      int N = rest.size();
      for (int i = 0; i < N; i++) {
        char cur = rest.get(i);
        rest.remove(i);
        f(rest, path + cur, ans);
        rest.add(i, cur); // 恢复现场
      }
    }
  }

  // 比第一种稍微好一点的递归
  public static List<String> permutation2(String s) {
    List<String> ans = new ArrayList<>();
    if (s == null || s.length() == 0)
      return ans;
    char[] str = s.toCharArray();
    g(str, 0, ans);
    return ans;

  }

  private static void g(char[] str, int index, List<String> ans) {
    if (index == str.length) {
      ans.add(String.valueOf(str));
    } else {
      for (int i = index; i < str.length; i++) {
        swap(str, index, i);
        g(str, index + 1, ans);
        swap(str, i, index);// 恢复现场
      }
    }
  }

  // 特殊情况去重的算法
  public static List<String> permutation3(String s) {
    List<String> ans = new ArrayList<>();
    if (s == null || s.length() == 0)
      return ans;
    char[] str = s.toCharArray();
    g2(str, 0, ans);
    return ans;

  }

  private static void g2(char[] str, int index, List<String> ans) {
    if (index == str.length) {
      ans.add(String.valueOf(str));
    } else {
      boolean[] visited = new boolean[256];
      for (int i = index; i < str.length; i++) {
        if (!visited[str[i]]) { // 去重操作
          visited[str[i]] = true;
          swap(str, index, i);
          g2(str, index + 1, ans);
          swap(str, i, index);// 恢复现场
        }
      }
    }
  }

  private static void swap(char[] chs, int i, int j) {
    char temp = chs[i];
    chs[i] = chs[j];
    chs[j] = temp;
  }

  public static void main(String[] args) {

    String s = "acc";
    List<String> ans1 = permutation1(s);
    for (String str : ans1) {
      System.out.println(str);
    }
    System.out.println("===============================");
    List<String> ans2 = permutation2(s);
    for (String str : ans2) {
      System.out.println(str);
    }

    System.out.println("===============================");
    List<String> ans3 = permutation3(s);
    for (String str : ans3) {
      System.out.println(str);
    }
  }
}
