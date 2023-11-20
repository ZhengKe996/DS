#include <iostream>
#include <stack>

using namespace std;

class Solution {
 public:
  vector<vector<int>> getNearLessNoRepeat(vector<int>& arr) {
    vector<vector<int>> res(arr.size(), vector<int>(2, 0));
    stack<int> s;
    for (int i = 0; arr.size(); i++) {
      while (!s.empty() && arr[s.top()] > arr[i]) {
        int j = s.top();
        s.pop();
        int leftLessIndex = s.empty() ? -1 : s.top();
        res[j][0] = leftLessIndex;
        res[j][1] = i;
      }
      s.push(i);
    }
    while (!s.empty()) {
      int j = s.top();
      s.pop();
      int leftLessIndex = s.empty() ? -1 : s.top();
      res[j][0] = leftLessIndex;
      res[j][1] = -1;
    }
    return res;
  }
  vector<vector<int>> getNearLess(vector<int>& arr) {
    vector<vector<int>> res(arr.size(), vector<int>(2, 0));
    stack<vector<int>> s;
    for (int i = 0; arr.size(); i++) {
      while (!s.empty() && arr[s.top()[0]] > arr[i]) {
        vector<int> pops = s.top();
        s.pop();
        int leftLessIndex = s.empty() ? -1 : s.top()[s.top().size() - 1];

        for (int pop : pops) {
          res[pop][0] = leftLessIndex;
          res[pop][1] = i;
        }
      }

      if (!s.empty() && arr[s.top()[0]] == arr[i]) {
        s.top().push_back(i);
      } else {
        vector<int> list;
        list.push_back(i);
        s.push(list);
      }
    }

    while (!s.empty()) {
      vector<int> pops = s.top();
      s.pop();
      int leftLessIndex = s.empty() ? -1 : s.top()[s.top().size() - 1];
      for (int pop : pops) {
        res[pop][0] = leftLessIndex;
        res[pop][1] = -1;
      }
    }
    return res;
  }
};
