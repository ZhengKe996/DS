package graph;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import graph.struct.Edge;
import graph.struct.Graph;
import graph.struct.Node;

public class Prim {
  public static Set<Edge> primMST(Graph graph) {
    // 解锁的边进入小根堆
    PriorityQueue<Edge> priorityQueue = new PriorityQueue<>((o1, o2) -> {
      return o1.weight - o2.weight;
    });

    HashSet<Node> nodeSet = new HashSet<>();
    Set<Edge> result = new HashSet<>(); // 依次挑选的边在Result里
    for (Node node : graph.nodes.values()) {
      // Node 是开始点
      if (!nodeSet.contains(node)) {
        nodeSet.add(node);
        for (Edge edge : node.edges)
          priorityQueue.add(edge); // 由一个点，解锁所有相连的边；

        while (!priorityQueue.isEmpty()) {
          Edge edge = priorityQueue.poll(); // 弹出解锁的边中，最小的边
          Node toNode = edge.to;// 可能是一个新的点
          if (!nodeSet.contains(toNode)) {
            // 不含有的时候，就是新的点
            nodeSet.add(toNode);
            result.add(edge);
            for (Edge nextEdge : toNode.edges)
              priorityQueue.add(nextEdge);
          }
        }
      }
    }
    return result;
  }
}
