package trie;

import java.util.HashMap;

class Solution {

  /**
   * Trie1 使用数组实现前缀树
   */
  class Trie1 {
    class Node {
      public int pass, end;
      public Node[] nexts;

      public Node() {
        pass = 0;
        end = 0;
        nexts = new Node[26];
      }
    }

    private Node root;

    public Trie1() {
      root = new Node();
    }

    /**
     * 前缀树新增操作：
     * 遍历word字符数组，通过ascii码找到地址 在树上进行新增操作
     * 
     * @param word
     */
    public void insert(String word) {
      if (word == null)
        return;

      char[] chs = word.toCharArray();
      Node node = root;
      node.pass++;
      int path = 0;
      for (int i = 0; i < chs.length; i++) {
        path = chs[i] - 'a';
        if (node.nexts[path] == null)
          node.nexts[path] = new Node();
        node = node.nexts[path];
        node.pass++;
      }
    }

    /**
     * 输入的单词在树种出现了几次
     * 
     * @param word
     * @return
     */
    public int countWordsEqualTo(String word) {
      if (word == null)
        return 0;
      char[] chs = word.toCharArray();
      Node node = root;
      int index = 0;
      for (int i = 0; i < chs.length; i++) {
        index = chs[i] - 'a';
        if (node.nexts[index] == null)
          return 0;
        node = node.nexts[index];
      }
      return node.end;
    }

    /**
     * 前缀树：删除操作
     * pass-- end --;
     * 如果pass=0；节点断连 jvm回收
     * 
     * @param word
     */
    public void erase(String word) {
      if (countWordsEqualTo(word) == 0)
        return;

      char[] chs = word.toCharArray();
      Node node = root;
      node.pass--;
      int path = 0;
      for (int i = 0; i < chs.length; i++) {
        path = chs[i] - 'a';
        if (--node.nexts[path].pass == 0) {
          node.nexts[path] = null;
          return;
        }

        node = node.nexts[path];
      }
      node.end--;
    }

    /**
     * 前缀树：
     * 返回多少个以 输入的字符开头的字符
     * 
     * @param pre
     * @return
     */
    public int countWordsStartingWith(String pre) {
      if (pre == null)
        return 0;
      char[] chs = pre.toCharArray();
      Node node = root;
      int index = 0;
      for (int i = 0; i < chs.length; i++) {
        index = chs[i] - 'a';
        if (node.nexts[index] == null)
          return 0;
        node = node.nexts[index];
      }
      return node.pass;
    }
  }

  /**
   * Trie2 使用哈希表实现前缀树
   */
  class Trie2 {
    class Node {
      public int pass, end;
      public HashMap<Integer, Node> nexts;

      public Node() {
        pass = 0;
        end = 0;
        nexts = new HashMap<>();
      }
    }

    private Node root;

    public Trie2() {
      root = new Node();
    }

    /**
     * 前缀树插入
     * 
     * @param word
     */
    public void insert(String word) {
      if (word == null)
        return;
      char[] chs = word.toCharArray();
      Node node = root;
      node.pass++;
      int index = 0;
      for (int i = 0; i < chs.length; i++) {
        index = (int) chs[i];
        if (!node.nexts.containsKey(index)) {
          node.nexts.put(index, new Node());
        }
        node = node.nexts.get(index);
        node.pass++;
      }
      node.end++;
    }

    /**
     * 前缀树计数
     * 
     * @param word
     * @return
     */
    public int countWordsEqualTo(String word) {
      if (word == null)
        return 0;
      char[] chs = word.toCharArray();
      Node node = root;
      int index = 0;
      for (int i = 0; i < chs.length; i++) {
        index = (int) chs[i];
        if (!node.nexts.containsKey(index))
          return 0;
        node = node.nexts.get(index);
      }
      return node.end;
    }

    /**
     * 前缀树删除操作
     * 
     * @param word
     */
    public void erase(String word) {
      if (countWordsEqualTo(word) == 0)
        return;

      char[] chs = word.toCharArray();
      Node node = root;
      node.pass--;
      int index = 0;
      for (int i = 0; i < chs.length; i++) {
        index = (int) chs[i];
        if (--node.nexts.get(index).pass == 0) {
          node.nexts.remove(index);
          return;
        }
        node = node.nexts.get(index);
      }
      node.end--;
    }

    /**
     * 返回多少个以 pre为前缀的树
     * 
     * @param pre
     * @return
     */
    public int countWordsStartingWith(String pre) {
      if (pre == null)
        return 0;
      char[] chs = pre.toCharArray();
      Node node = root;
      int index = 0;
      for (int i = 0; i < chs.length; i++) {
        index = (int) chs[i];
        if (!node.nexts.containsKey(index))
          return 0;

        node = node.nexts.get(index);
      }
      return node.pass;
    }

  }
}
