package graph;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

class Topology2 {
  class DirectedGraphNode {
    int label;
    List<DirectedGraphNode> neighbors;

    DirectedGraphNode(int x) {
      label = x;
      neighbors = new ArrayList<DirectedGraphNode>();
    }
  }

  public static class Record {
    public DirectedGraphNode node;
    public long nodes;

    public Record(DirectedGraphNode n, long o) {
      node = n;
      nodes = o;
    }
  }

  public static Record f(DirectedGraphNode cur, HashMap<DirectedGraphNode, Record> order) {
    if (order.containsKey(cur)) {
      return order.get(cur);
    }
    // cur的点次之前没算过！
    long nodes = 0;
    for (DirectedGraphNode next : cur.neighbors) {
      nodes += f(next, order).nodes;
    }
    Record ans = new Record(cur, nodes + 1);
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
      return o1.nodes == o2.nodes ? 0 : (o1.nodes > o2.nodes ? -1 : 1);
    });
    ArrayList<DirectedGraphNode> ans = new ArrayList<DirectedGraphNode>();
    for (Record r : recordArr) {
      ans.add(r.node);
    }
    return ans;
  }
}
