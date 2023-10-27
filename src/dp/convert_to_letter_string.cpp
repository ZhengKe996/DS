#include <iostream>

using namespace std;

class Solution {
 private:
  int process(string &str, int i) {
    if (i == str.length()) return 1;
    if (str[i] == '0') return 0;

    int ways = process(str, i + 1);
    if (i + 1 < str.length() && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
      ways += process(str, i + 2);
    }
    return ways;
  }

 public:
  int number(string &str) {
    if (str.empty() || str.length() == 0) return 0;
    return process(str, 0);
  }

  int dp(string &str) {
    if (str.empty() || str.length() == 0) return 0;
    int N = str.length();
    vector<int> dp(N + 1, 0);
    dp[N] = 1;
    for (int i = N - 1; i >= 0; i--) {
      if (str[i] != '0') {
        int ways = dp[i + 1];
        if (i + 1 < str.length() &&
            (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
          ways += dp[i + 2];
        }
        dp[i] = ways;
      }
    }
    return dp[0];
  }

  // TEST
  string randomString(int len) {
    vector<char> str(len, 0);
    for (int i = 0; i < len; i++) {
      str[i] = (char)((int)(rand() % 10) + '0');
    }
    string res = "";
    for (int i = 0; i < len; i++) {
      res += str[i];
    }

    return res;
  }
};

int main() {
  int N = 30;
  int testTime = 1000000;
  Solution s;

  cout << "测试开始" << endl;
  for (int i = 0; i < testTime; i++) {
    int len = (int)(rand() % N);
    string str = s.randomString(len);
    int ans0 = s.number(str);
    int ans1 = s.dp(str);
    if (ans0 != ans1) {
      cout << str << endl;
      cout << ans0 << endl;
      cout << ans1 << endl;
      cout << "Oops!" << endl;
      break;
    }
  }

  cout << "测试结束" << endl;
  return 0;
}