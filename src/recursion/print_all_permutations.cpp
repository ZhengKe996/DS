#include <iostream>

using namespace std;

class Solution {
 private:
  void f(vector<char>& rest, string path, vector<string>& ans) {
    if (rest.empty())
      ans.push_back(path);
    else {
      int N = rest.size();
      for (int i = 0; i < N; i++) {
        char cur = rest[i];
        rest.erase(rest.begin() + i);
        f(rest, path + cur, ans);
        rest.insert(rest.begin() + i, cur);  // 恢复现场
      }
    }
  };

  void g(string strs, int index, vector<string>& ans) {
    if (index == strs.size())
      ans.push_back(strs);
    else {
      for (int i = index; i < strs.size(); i++) {
        swap(strs, index, i);
        g(strs, index + 1, ans);
        swap(strs, i, index);
      }
    }
  }

  void swap(string& chs, int i, int j) {
    char tmp = chs[i];
    chs[i] = chs[j];
    chs[j] = tmp;
  }

  void g2(string strs, int index, vector<string>& ans) {
    if (index == strs.size())
      ans.push_back(strs);
    else {
      vector<bool> is_visited = vector<bool>(256, 0);
      for (int i = index; i < strs.size(); i++) {
        if (!is_visited[strs[i]]) {
          is_visited[strs[i]] = true;
          swap(strs, index, i);
          g2(strs, index + 1, ans);
          swap(strs, i, index);
        }
      }
    }
  }

 public:
  vector<string> permutation1(string s) {
    vector<string> ans;
    if (s.empty() || s.size() == 0) return ans;
    vector<char> rest;
    for (char cha : s) {
      rest.push_back(cha);
    }
    string path = "";
    f(rest, path, ans);
    return ans;
  }

  vector<string> permutation2(string s) {
    vector<string> ans;
    if (s.empty() || s.size() == 0) return ans;
    g(s, 0, ans);
    return ans;
  }

  vector<string> permutation3(string s) {
    vector<string> ans;
    if (s.empty() || s.size() == 0) return ans;
    g2(s, 0, ans);
    return ans;
  }
};

int main() {
  Solution s;

  string str = "acc";
  vector<string> ans1 = s.permutation1(str);
  for (string s : ans1) {
    cout << s << endl;
  }
  cout << "=================" << endl;
  vector<string> ans2 = s.permutation2(str);
  for (string s : ans2) {
    cout << s << endl;
  }
  cout << "=================" << endl;

  vector<string> ans3 = s.permutation3(str);
  for (string s : ans3) {
    cout << s << endl;
  }
  cout << "=================" << endl;
  return 0;
}