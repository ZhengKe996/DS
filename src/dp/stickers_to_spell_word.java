package dp;

import java.util.HashMap;

public class stickers_to_spell_word {
  /**
   * 暴力解(超时)
   * 
   * @param stickers 给定的数组
   * @param target   目标字符串
   * @return 返回最小张数
   */
  public static int minStickers1(String[] stickers, String target) {
    int ans = process1(stickers, target);
    return ans == Integer.MAX_VALUE ? -1 : ans;
  }

  private static int process1(String[] stickers, String target) {
    if (target.length() == 0)
      return 0;
    int min = Integer.MAX_VALUE;
    for (String first : stickers) {
      String rest = minus(target, first);
      if (rest.length() != target.length())
        min = Math.min(min, process1(stickers, rest));

    }
    return min + (min == Integer.MAX_VALUE ? 0 : 1);
  }

  private static String minus(String s1, String s2) {
    char[] str1 = s1.toCharArray();
    char[] str2 = s2.toCharArray();
    int[] count = new int[26];
    for (char cha : str1)
      count[cha - 'a']++;
    for (char cha : str2)
      count[cha - 'a']--;

    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < 26; i++) {
      if (count[i] > 0) {
        for (int j = 0; j < count[i]; j++)
          builder.append((char) (i + 'a'));
      }
    }
    return builder.toString();
  }

  /**
   * 暴力解（使用词频表优化）
   * 
   * @param stickers
   * @param target
   * @return
   */
  public static int minStickers2(String[] stickers, String target) {
    int N = stickers.length;
    int[][] counts = new int[N][26]; // 关键优化(用词频表替代贴纸数组)
    for (int i = 0; i < N; i++) {
      char[] str = stickers[i].toCharArray();
      for (char ch : str)
        counts[i][ch - 'a']++;
    }
    int ans = process2(counts, target);
    return ans == Integer.MAX_VALUE ? -1 : ans;
  }

  private static int process2(int[][] stickers, String t) {
    if (t.length() == 0)
      return 0;

    char[] target = t.toCharArray();
    int[] tcounts = new int[26];
    for (char ch : target)
      tcounts[ch - 'a']++;
    int N = stickers.length;
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < N; i++) {
      int[] sticker = stickers[i];
      // 核心 剪枝（贪心）
      if (sticker[target[0] - 'a'] > 0) {
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < 26; j++) {
          if (tcounts[j] > 0) {
            int nums = tcounts[j] - sticker[j];
            for (int k = 0; k < nums; k++)
              builder.append((char) (j + 'a'));
          }
        }
        String rest = builder.toString();
        min = Math.min(min, process2(stickers, rest));
      }
    }
    return min + (min == Integer.MAX_VALUE ? 0 : 1);
  }

  /**
   * DP 傻缓存
   * 
   * @param stickers
   * @param target
   * @return
   */
  public static int minStickers3(String[] stickers, String target) {
    int N = stickers.length;
    int[][] counts = new int[N][26];
    for (int i = 0; i < N; i++) {
      char[] str = stickers[i].toCharArray();
      for (char cha : str) {
        counts[i][cha - 'a']++;
      }
    }
    HashMap<String, Integer> dp = new HashMap<>();
    dp.put("", 0);
    int ans = process3(counts, target, dp);
    return ans == Integer.MAX_VALUE ? -1 : ans;
  }

  private static int process3(int[][] stickers, String t, HashMap<String, Integer> dp) {
    if (dp.containsKey(t))
      return dp.get(t);

    char[] target = t.toCharArray();
    int[] tcounts = new int[26];
    for (char ch : target)
      tcounts[ch - 'a']++;
    int N = stickers.length;
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < N; i++) {
      int[] sticker = stickers[i];
      if (sticker[target[0] - 'a'] > 0) {
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < 26; j++) {
          if (tcounts[j] > 0) {
            int nums = tcounts[j] - sticker[j];
            for (int k = 0; k < nums; k++)
              builder.append((char) (j + 'a'));
          }
        }
        String rest = builder.toString();
        min = Math.min(min, process3(stickers, rest, dp));
      }
    }
    int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
    dp.put(t, ans);
    return ans;
  }
}
