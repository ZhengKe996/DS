#include <iostream>

using namespace std;

class hanoi {
 private:
  void left_to_right(int n) {
    if (n == 1) {
      cout << "Move 1 from left to right" << endl;
      return;
    }
    left_to_mid(n - 1);
    cout << "Move " << n << " from left to right" << endl;
    mid_to_right(n - 1);
  };
  void left_to_mid(int n) {
    if (n == 1) {
      cout << "Move 1 from left to mid" << endl;
      return;
    }
    left_to_right(n - 1);
    cout << "Move " << n << " from left to mid" << endl;
    right_to_mid(n - 1);
  };

  void right_to_mid(int n) {
    if (n == 1) {
      cout << "Move 1 from right to mid" << endl;
      return;
    }
    right_to_left(n - 1);
    cout << "Move " << n << " from right to mid" << endl;
    left_to_mid(n - 1);
  };
  void right_to_left(int n) {
    if (n == 1) {
      cout << "Move 1 from right to left" << endl;
      return;
    }
    right_to_mid(n - 1);
    cout << "Move " << n << " from right to left" << endl;
    mid_to_left(n - 1);
  };
  void mid_to_right(int n) {
    if (n == 1) {
      cout << "Move 1 from mid to right" << endl;
      return;
    }
    mid_to_left(n - 1);
    cout << "Move " << n << " from mid to right" << endl;
    left_to_right(n - 1);
  };
  void mid_to_left(int n) {
    if (n == 1) {
      cout << "Move 1 from mid to left" << endl;
      return;
    }
    mid_to_right(n - 1);
    cout << "Move " << n << " from mid to left" << endl;
    right_to_left(n - 1);
  };

  void func(int N, string from, string to, string other) {
    if (N == 1)
      cout << "Move 1 from " << from << " to " << to << endl;
    else {
      func(N - 1, from, other, to);
      cout << "Move " << N << " from " << from << " to " << to << endl;
      func(N - 1, other, to, from);
    }
  };

 public:
  void hanoi1(int n) { left_to_right(n); }
  void hanoi2(int n) {
    if (n > 0) func(n, "left", "right", "mid");
  }
};

int main() {
  hanoi h;
  h.hanoi1(3);
  cout << "=================" << endl;
  h.hanoi2(3);
  return 0;
}