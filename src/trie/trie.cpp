#include <iostream>
#include <unordered_map>

using namespace std;

struct Trie1 {
  struct Node {
    int pass, end;
    vector<Node*> nexts;

    Node() : pass(0), end(0), nexts(vector<Node*>(26)){};
  };

 private:
  Node* root;

 public:
  Trie1() { root = new Node(); }

  void insert(string word) {
    if (word.empty() || word.begin() == word.end()) return;
    Node* node = root;
    node->pass++;
    int path = 0;
    for (int i = 0; i < word.size(); i++) {
      path = word[i] - 'a';
      if (node->nexts[path] == nullptr) node->nexts[path] = new Node();
      node = node->nexts[path];
      node->pass++;
    }
    node->end++;
  }

  int countWordsEqualTo(string word) {
    if (word.empty() || word.begin() == word.end()) return 0;

    Node* node = root;
    int index = 0;
    for (int i = 0; i < word.size(); i++) {
      index = word[i] - 'a';
      if (node->nexts[index] == nullptr) return 0;
      node = node->nexts[index];
    }
    return node->end;
  }

  void erase(string word) {
    if (countWordsEqualTo(word) == 0) return;
    Node* node = root;
    node->pass--;
    int path = 0;
    for (int i = 0; i < word.size(); i++) {
      path = word[i] - 'a';
      if (--node->nexts[path]->pass == 0) {
        node->nexts[path] = nullptr;
        return;
      }
      node = node->nexts[path];
    }
    node->end--;
  }
  int countWordsStartingWith(string pre) {
    if (pre.empty() || pre.begin() == pre.end()) return 0;
    Node* node = root;
    int index = 0;
    for (int i = 0; i < pre.size(); i++) {
      index = pre[i] - 'a';
      if (node->nexts[index] == nullptr) return 0;
      node = node->nexts[index];
    }
    return node->pass;
  }
};

struct Trie2 {
  struct Node {
    int pass, end;
    unordered_map<int, Node*> nexts;

    Node() : pass(0), end(0){};
  };

 private:
  Node* root;

 public:
  Trie2() { root = new Node(); }

  void insert(string word) {
    if (word.empty() || word.begin() == word.end()) return;
    Node* node = root;
    node->pass++;
    int index = 0;
    for (int i = 0; i < word.size(); i++) {
      index = (int)word[i];
      if (!(node->nexts.find(index) != node->nexts.end())) {
        node->nexts[index] = new Node();
      }

      node = node->nexts[index];
      node->pass++;
    }
    node->end++;
  }

  int countWordsEqualTo(string word) {
    if (word.empty() || word.begin() == word.end()) return 0;
    Node* node = root;
    int index = 0;
    for (int i = 0; i < word.size(); i++) {
      index = (int)word[i];
      if (!(node->nexts.find(index) != node->nexts.end())) return 0;
      node = node->nexts[index];
    }
    return node->end;
  }

  void erase(string word) {
    if (word.empty() || word.begin() == word.end()) return;
    Node* node = root;
    node->pass--;
    int index = 0;
    for (int i = 0; i < word.size(); i++) {
      index = (int)word[i];
      if (--node->nexts[index]->pass == 0) {
        node->nexts[index] = nullptr;
        return;
      }
      node = node->nexts[index];
    }
    node->end--;
  }

  int countWordsStartingWith(string pre) {
    if (pre.empty() || pre.begin() == pre.end()) return 0;
    Node* node = root;
    int index = 0;
    for (int i = 0; i < pre.size(); i++) {
      index = (int)pre[i];
      if (!(node->nexts.find(index) != node->nexts.end())) return 0;
      node = node->nexts[index];
    }
    return node->pass;
  }
};
