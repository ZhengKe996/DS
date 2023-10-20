#include <iostream>
#include <unordered_set>
using namespace std;

class Solution {
 private:
  void process(string s, int index, unordered_set<string>& set, string path) {
    if (index == s.size()) {
      set.insert(path);
      return;
    }

    string no = path;
    process(s, index + 1, set, no);
    string yes = path + s[index];
    process(s, index + 1, set, yes);
  };

 public:
  vector<string> subNoRepeat(string s) {
    string path = "";
    unordered_set<string> set;
    process(s, 0, set, path);
    vector<string> ans;
    for (string cur : set) ans.push_back(cur);
    return ans;
  }
};

int main() {
  Solution s;
  string test = "acccc";
  vector<string> ans = s.subNoRepeat(test);

  for (string str : ans) {
    cout << str << endl;
  }
}