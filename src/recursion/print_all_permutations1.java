package recursion;

import java.util.ArrayList;
import java.util.List;

public class print_all_permutations1 {
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
        rest.add(i, cur);
      }
    }
  }

  public static void main(String[] args) {

    String s = "acc";
    List<String> ans1 = permutation1(s);
    for (String str : ans1) {
      System.out.println(str);
    }
  }
}
