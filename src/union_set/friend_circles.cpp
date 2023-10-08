#include <iostream>
using namespace std;

class Solution {
 private:
  struct UnionFind {
   private:
    vector<int> parent, size, help;
    int sets;
    int find(int i) {
      int hi = 0;
      while (i != parent[i]) {
        help[hi++] = i;
        i = parent[i];
      }
      for (hi--; hi >= 0; hi--) {
        parent[help[hi]] = i;
      }
      return i;
    }

   public:
    UnionFind(int N) {
      this->parent = vector<int>(N, 0);
      this->size = vector<int>(N, 0);
      this->help = vector<int>(N, 0);
      this->sets = N;
      for (int i = 0; i < N; i++) {
        parent[i] = i;
        size[i] = 1;
      }
    };

    void unions(int i, int j) {
      int f1 = find(i);
      int f2 = find(j);
      if (f1 != f2) {
        if (size[f1] >= size[f2]) {
          size[f1] += size[f2];
          parent[f2] = f1;
        } else {
          size[f2] += size[f1];
          parent[f1] = f2;
        }
        sets--;
      }
    }
    int setn() { return sets; }
  };

 public:
  int findCircleNum(vector<vector<int>>& isConnected) {
    int N = isConnected.size();
    UnionFind* unionFind = new UnionFind(N);
    for (int i = 0; i < N; i++) {
      for (int j = i + 1; j < N; j++) {
        if (isConnected[i][j] == 1) {  // i和j互相认识
          unionFind->unions(i, j);
        }
      }
    }
    return unionFind->setn();
  }
};