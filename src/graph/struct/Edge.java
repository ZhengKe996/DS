package graph.struct;

public class Edge {
  public int weight;
  public Node form;
  public Node to;

  public Edge(int weight, Node from, Node to) {
    this.weight = weight;
    this.form = from;
    this.to = to;
  }
}
