#include <iostream>

using namespace std;

struct Employee {
  int happys;
  vector<Employee*> nexts;
  Employee(int h) : happys(h){};
};

class Solution {
 private:
  int process1(Employee* cur, bool up) {
    if (up) {
      int ans = 0;
      for (Employee* next : cur->nexts) ans += process1(next, false);
      return ans;
    } else {
      int p1 = cur->happys;
      int p2 = 0;
      for (Employee* next : cur->nexts) {
        p1 += process1(next, true);
        p2 += process1(next, false);
      }
      return max(p1, p2);
    }
  }

  // 二叉树递归
  struct Info {
    int yes, no;
    Info(int yes, int no) : yes(yes), no(no){};
  };

  Info* process(Employee* x) {
    if (x == nullptr) return new Info(0, 0);
    int no = 0, yes = x->happys;
    for (Employee* next : x->nexts) {
      Info* nextInfo = process(next);
      no += max(nextInfo->no, nextInfo->yes);
      yes += nextInfo->no;
    }

    return new Info(yes, no);
  }

 public:
  int maxHappy1(Employee* boss) {
    if (boss == nullptr) return 0;
    return process1(boss, false);
  }
  int maxHappy2(Employee* boss) {
    Info* info = process(boss);
    return max(info->no, info->yes);
  }

  // =========== TEST ===========

  void generateNexts(Employee* e, int level, int maxLevel, int maxNexts,
                     int maxHappy) {
    if (level > maxLevel) return;

    int nextSize = (int)(rand() % (maxNexts + 1));

    for (int i = 0; i < nextSize; i++) {
      Employee* next = new Employee(rand() % (maxHappy + 1));
      e->nexts.push_back(next);
      generateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
    }
  }

  Employee* genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
    if (rand() < 0.02) return nullptr;
    Employee* boss = new Employee(rand() % (maxHappy + 1));
    generateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
    return boss;
  }
};

int main() {
  Solution solution;
  int maxLevel = 4, maxNexts = 7, maxHappy = 100, testTimes = 100000;
  for (int i = 0; i < testTimes; i++) {
    Employee* boss = solution.genarateBoss(maxLevel, maxNexts, maxHappy);
    if (solution.maxHappy1(boss) != solution.maxHappy2(boss))
      cout << "Oops!" << endl;
  }
  cout << "finish!" << endl;
}