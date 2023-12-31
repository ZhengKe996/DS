package graph;

import java.util.HashMap;

import graph.struct.Edge;
import graph.struct.Node;

public class Dijkstra2 {
  public static class NodeRecord {
    public Node node;
    public int distance;

    public NodeRecord(Node node, int distance) {
      this.node = node;
      this.distance = distance;
    }
  }

  public static class NodeHeap {
    private Node[] nodes;// 实际的堆结构
    private HashMap<Node, Integer> heapIndexMap;// key 某个node value 堆中的位置
    private HashMap<Node, Integer> distanceMap;// key某个节点 value 从源出发到该节点目前最小距离
    private int size;// 堆上有多少个点

    public NodeHeap(int size) {
      nodes = new Node[size];
      heapIndexMap = new HashMap<>();
      distanceMap = new HashMap<>();
      size = 0;
    }

    public boolean isEmpty() {
      return size == 0;
    };

    public void addOrUpdateOrIgnore(Node node, int distance) {
      if (inHeap(node)) {
        distanceMap.put(node, Math.min(distanceMap.get(node), distance));
        insertHeapify(heapIndexMap.get(node));
      }
      if (!isEntered(node)) {
        nodes[size] = node;
        heapIndexMap.put(node, size);
        distanceMap.put(node, distance);
        insertHeapify(size++);
      }
    }

    public NodeRecord pop() {
      NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
      swap(0, size - 1);
      heapIndexMap.put(nodes[size - 1], -1);
      distanceMap.remove(nodes[size - 1]);
      nodes[size - 1] = null;
      heapify(0, --size);
      return nodeRecord;
    }

    private void insertHeapify(int index) {
      while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
        swap(index, (index - 1) / 2);
        index = (index - 1) / 2;
      }
    }

    private void heapify(int index, int size) {
      int left = index * 2 + 1;
      while (left < size) {
        int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
            ? left + 1
            : left;
        smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
        if (smallest == index) {
          break;
        }
        swap(smallest, index);
        index = smallest;
        left = index * 2 + 1;
      }
    }

    private boolean isEntered(Node node) {
      return heapIndexMap.containsKey(node);
    }

    private boolean inHeap(Node node) {
      return isEntered(node) && heapIndexMap.get(node) != -1;
    };

    private void swap(int index1, int index2) {
      heapIndexMap.put(nodes[index1], index2);
      heapIndexMap.put(nodes[index2], index1);
      Node tmp = nodes[index1];
      nodes[index1] = nodes[index2];
      nodes[index2] = tmp;
    }
  }

  public static HashMap<Node, Integer> dijkstra(Node head, int size) {
    NodeHeap nodeHeap = new NodeHeap(size);
    nodeHeap.addOrUpdateOrIgnore(head, 0);
    HashMap<Node, Integer> result = new HashMap<>();
    while (!nodeHeap.isEmpty()) {
      NodeRecord record = nodeHeap.pop();
      Node cur = record.node;
      int distance = record.distance;
      for (Edge edge : cur.edges) {
        nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
      }
      result.put(cur, distance);
    }
    return result;
  }
}
