#include <iostream>
#include <queue>
using namespace std;

class Solution {
 public:
  int canCompleteCircuit(vector<int>& gas, vector<int>& cost) {
    vector<bool> good = goodArray(gas, cost);
    for (int i = 0; i < gas.size(); i++) {
      if (good[i]) {
        return i;
      }
    }
    return -1;
  }

 private:
  vector<bool> goodArray(vector<int>& g, vector<int>& c) {
    int N = g.size();
    int M = N << 1;
    vector<int> arr(M, 0);
    for (int i = 0; i < N; i++) {
      arr[i] = g[i] - c[i];
      arr[i + N] = g[i] - c[i];
    }
    for (int i = 1; i < M; i++) {
      arr[i] += arr[i - 1];
    }

    deque<int> w;
    for (int i = 0; i < N; i++) {
      while (!w.empty() && arr[w.back()] >= arr[i]) {
        w.pop_back();
      }
      w.push_back(i);
    }

    vector<bool> ans(N, 0);
    for (int offset = 0, i = 0, j = N; j < M; offset = arr[i++], j++) {
      if (arr[w.front()] - offset >= 0) {
        ans[i] = true;
      }
      if (w.front() == i) {
        w.pop_front();
      }
      while (!w.empty() && arr[w.back()] >= arr[j]) {
        w.pop_back();
      }
      w.push_back(j);
    }
    return ans;
  }
};
