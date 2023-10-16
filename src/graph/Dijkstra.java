package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import graph.struct.Edge;
import graph.struct.Node;

public class Dijkstra {

  public static HashMap<Node, Integer> dijksttra(Node from) {
    HashMap<Node, Integer> distanceMap = new HashMap<>();
    distanceMap.put(from, 0);
    // 打过对号的点
    HashSet<Node> selectedNodes = new HashSet<>();
    Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes); // 找到最小距离的条点（未打钩
    while (minNode != null) {
      int distance = distanceMap.get(minNode);
      for (Edge edge : minNode.edges) {
        Node toNode = edge.to;
        if (!distanceMap.containsKey(toNode))
          distanceMap.put(toNode, distance + edge.weight);
        else
          distanceMap.put(edge.to, Math.min(distanceMap.get(toNode), distance + edge.weight));
      }
      selectedNodes.add(minNode);
      minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes); // 找到最小距离的条点（未打钩
    }

    return distanceMap;
  }

  public static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> touchedNodes) {
    Node minNode = null;
    int minDistance = Integer.MAX_VALUE;
    for (Entry<Node, Integer> entry : distanceMap.entrySet()) {
      Node node = entry.getKey();
      int distance = entry.getValue();
      if (!touchedNodes.contains(node) && distance < minDistance) {
        minDistance = distance;
        minNode = node;
      }
    }
    return minNode;
  }
}
