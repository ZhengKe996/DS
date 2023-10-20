package recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class print_all_sub_squences2 {
  public static List<String> subNoRepeat(String s) {
    char[] str = s.toCharArray();
    String path = "";
    HashSet<String> set = new HashSet<>();
    process(str, 0, set, path);
    List<String> ans = new ArrayList<>();
    for (String cur : set) {
      ans.add(cur);
    }
    return ans;
  }

  private static void process(char[] str, int index, HashSet<String> set, String path) {
    if (index == str.length) {
      set.add(path);
      return;
    }
    String no = path;
    process(str, index + 1, set, no);
    String yes = path + String.valueOf(str[index]);
    process(str, index + 1, set, yes);
  }

  public static void main(String[] args) {
    String test = "acccc";
    List<String> ans1 = subNoRepeat(test);

    for (String str : ans1) {
      System.out.println(str);
    }
    System.out.println("=================");
  }
}
