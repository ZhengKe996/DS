#include <iostream>

using namespace std;

class Solution {
 private:
  void process(string s, int index, vector<string>& ans, string path) {
    if (index == s.size()) {
      ans.push_back(path);
      return;
    }
    process(s, index + 1, ans, path);
    process(s, index + 1, ans, path + s[index]);
  };

 public:
  vector<string> subs(string s) {
    string path = "";
    vector<string> ans;
    process(s, 0, ans, path);
    return ans;
  }
};

int main() {
  Solution s;
  string test = "acccc";
  vector<string> ans = s.subs(test);

  for (string str : ans) {
    cout << str << endl;
  }
}