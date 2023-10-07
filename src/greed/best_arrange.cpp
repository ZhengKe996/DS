#include <iostream>

using namespace std;

struct Program {
  int start, end;
  Program(int start, int end) : start(start), end(end){};
};

class Solution {
 private:
  int process(vector<Program*>& programs, int done, int timeLine) {
    if (programs.size() == 0) return done;

    int ma = done;
    for (int i = 0; i < programs.size(); i++) {
      vector<Program*> next = copyButExcept(programs, i);
      ma = max(ma, process(next, done + 1, programs[i]->end));
    }
    return ma;
  };

  vector<Program*> copyButExcept(vector<Program*>& programs, int i) {
    vector<Program*> ans(programs.size() - 1);
    int index = 0;
    for (int k = 0; k < programs.size(); k++) {
      if (k != i) {
        ans[index++] = programs[k];
      }
    }
    return ans;
  }
  static bool cmp(Program* x, Program* y) { return x->end - y->end; }

 public:
  // 暴力
  int bestArrange1(vector<Program*>& programs) {
    if (programs.empty() || programs.size() == 0) return 0;

    return process(programs, 0, 0);
  }
  int bestArrange2(vector<Program*>& programs) {
    sort(programs.begin(), programs.end(), cmp);

    int timeLine = 0;
    int result = 0;

    for (int i = 0; i < programs.size(); i++) {
      if (timeLine <= programs[i]->start) {
        result++;
        timeLine = programs[i]->end;
      }
    }
    return result;
  }

  // ==== test ====
  vector<Program*> generatePrograms(int programSize, int timeMax) {
    vector<Program*> ans(rand() % (programSize + 1), 0);
    for (int i = 0; i < ans.size(); i++) {
      int r1 = (int)(rand() % (timeMax + 1));
      int r2 = (int)(rand() % (timeMax + 1));
      if (r1 == r2) {
        ans[i] = new Program(r1, r1 + 1);
      } else {
        ans[i] = new Program(min(r1, r2), max(r1, r2));
      }
    }
    return ans;
  }
};

int main() {
  Solution solution;
  int programSize = 12;
  int timeMax = 20;
  int timeTimes = 10;
  for (int i = 0; i < timeTimes; i++) {
    vector<Program*> programs = solution.generatePrograms(programSize, timeMax);

    solution.bestArrange1(programs);
    // if (solution.bestArrange1(programs) != solution.bestArrange2(programs)) {
    //   cout << "Oops!" << endl;
    // }
  }
  cout << "finish!" << endl;
  return 0;
}