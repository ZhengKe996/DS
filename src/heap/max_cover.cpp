#include <iostream>
#include <queue>

using namespace std;

class Solution {
 private:
 public:
  int max_cover1(vector<vector<int>>& lines) {
    int mi = INT32_MAX, ma = INT32_MIN;
    for (int i = 0; i < lines.size(); i++) {
      mi = min(mi, lines[i][0]);
      ma = max(ma, lines[i][1]);
    }

    int cover = 0;
    for (double p = mi + 0.5; p < ma; p += 1) {
      int cur = 0;
      for (int i = 0; i < lines.size(); i++) {
        if (lines[i][0] < p && lines[i][1] > p) cur++;
      }
      cover = max(cover, cur);
    }
    return cover;
  }

  int max_cover2(vector<vector<int>>& lines) {
    // sort(lines.begin(), lines.end(),
    //      [](vector<int>& l, vector<int>& r) { return l < r; });

    // priority_queue<int> heap;
    // int ma = 0;
    // for (vector<int>& line : lines) {
    //   while (!heap.empty() && heap.top() <= line[0]) {
    //     heap.pop();
    //   }
    //   heap.push(line[1]);
    //   ma = max(ma, (int)heap.size());
    // }
    // return ma;

    sort(lines.begin(), lines.end(),
         [](vector<int>& l, vector<int>& r) { return l < r; });

    // 小根堆
    int max_cover = 0;
    priority_queue<int, vector<int>, greater<int>> heap;

    for (int i = 0; i < lines.size(); ++i) {
      // lines[i] -> cur 在黑盒中，把<=cur.start 东西都弹出
      while (!heap.empty() && heap.top() <= lines[i][0]) {
        heap.pop();
      }
      heap.emplace(lines[i][1]);
      max_cover = max(max_cover, (int)heap.size());
    }

    return max_cover;
  }

  vector<vector<int>> generateLines(int N, int L, int R) {
    int size = (int)(rand() % N) + 1;
    vector<vector<int>> ans(size, vector<int>(2, 0));
    for (int i = 0; i < size; i++) {
      int a = L + (int)(rand() % (R - L + 1));
      int b = L + (int)(rand() % (R - L + 1));
      if (a == b) {
        b = a + 1;
      }
      ans[i][0] = min(a, b);
      ans[i][1] = max(a, b);
    }
    return ans;
  }
};

int main() {
  Solution solution;
  cout << "TEST Begin" << endl;
  int N = 100, L = 0, R = 200, testTimes = 200000;
  for (int i = 0; i < testTimes; i++) {
    vector<vector<int>> lines = solution.generateLines(N, L, R);
    int ans1 = solution.max_cover1(lines);
    int ans2 = solution.max_cover2(lines);

    if (ans1 != ans2) {
      cout << "Oops!" << endl;
    }
  }
  cout << "TEST End" << endl;
  return 0;
}