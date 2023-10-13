package graph;

import java.util.ArrayList;
import java.util.HashMap;

class Topology3 {
  public static class DirectedGraphNode {
    public int label;
    public ArrayList<DirectedGraphNode> neighbors;

    public DirectedGraphNode(int x) {
      label = x;
      neighbors = new ArrayList<DirectedGraphNode>();
    }
  }

  public static class Record {
    public DirectedGraphNode node;
    public int deep;

    public Record(DirectedGraphNode n, int o) {
      node = n;
      deep = o;
    }
  }

  public static Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {

    if (order.containsKey(cur)) {
      return order.get(cur);
    }
    int follow = 0;
    for (DirectedGraphNode next : cur.neighbors) {
      follow = Math.max(follow, f(next, order).deep);
    }
    Record ans = new Record(cur, follow + 1);
    order.put(cur, ans);
    return ans;
  }

  public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
    HashMap<DirectedGraphNode, Record> order = new HashMap<>();
    for (DirectedGraphNode cur : graph) {
      f(cur, order);
    }
    ArrayList<Record> recordArr = new ArrayList<>();
    for (Record r : order.values()) {
      recordArr.add(r);
    }
    recordArr.sort((o1, o2) -> {
      return o2.deep - o1.deep;
    });
    ArrayList<DirectedGraphNode> ans = new ArrayList<DirectedGraphNode>();
    for (Record r : recordArr) {
      ans.add(r.node);
    }
    return ans;
  }
}
