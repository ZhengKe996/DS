#include <iostream>

using namespace std;

class Solution {
 private:
  int f1(vector<int>& arr, int L, int R) {
    if (L == R) return arr[L];
    int p1 = arr[L] + g1(arr, L + 1, R);
    int p2 = arr[R] + g1(arr, L, R - 1);
    return max(p1, p2);
  }

  // 后手
  int g1(vector<int>& arr, int L, int R) {
    if (L == R) return 0;
    int p1 = f1(arr, L + 1, R);
    int p2 = f1(arr, L, R - 1);
    return min(p1, p2);
  }

  int f2(vector<int>& arr, int L, int R, vector<vector<int>>& fmap,
         vector<vector<int>> gmap) {
    if (fmap[L][R] != -1) return fmap[L][R];
    int ans = 0;
    if (L == R)
      ans = arr[L];
    else {
      int p1 = arr[L] + g2(arr, L + 1, R, fmap, gmap);
      int p2 = arr[R] + g2(arr, L, R - 1, fmap, gmap);
      ans = max(p1, p2);
    }
    fmap[L][R] = ans;
    return ans;
  }

  // 后手
  int g2(vector<int>& arr, int L, int R, vector<vector<int>>& fmap,
         vector<vector<int>> gmap) {
    if (gmap[L][R] != -1) return gmap[L][R];

    int ans = 0;
    if (L != R) {
      int p1 = f2(arr, L + 1, R, fmap, gmap);
      int p2 = f2(arr, L, R - 1, fmap, gmap);
      ans = min(p1, p2);
    }
    gmap[L][R] = ans;
    return ans;
  }

 public:
  int win1(vector<int>& arr) {
    if (arr.empty() || arr.size() == 0) return 0;
    int frist = f1(arr, 0, arr.size() - 1);
    int second = g1(arr, 0, arr.size() - 1);
    return max(frist, second);
  }

  int win2(vector<int>& arr) {
    if (arr.empty() || arr.size() == 0) return 0;
    int N = arr.size();

    vector<vector<int>> fmap(N, vector<int>(N, 0));
    vector<vector<int>> gmap(N, vector<int>(N, 0));

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        fmap[i][j] = -1;
        gmap[i][j] = -1;
      }
    }

    int frist = f2(arr, 0, arr.size() - 1, fmap, gmap);
    int second = g2(arr, 0, arr.size() - 1, fmap, gmap);
    return max(frist, second);
  }
};

int main() {
  Solution s;
  vector<int> arr = {7, 4, 16, 15, 1};
  cout << s.win1(arr) << endl;
  cout << s.win2(arr) << endl;
  return 0;
}