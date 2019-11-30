package graph.util;

import java.util.HashSet;
import java.util.Set;

public class Vertex {

    public  Set<Edge> edges = new HashSet<>();
    public boolean explored = false;
    public int shortestDistance = 1000000; // default is when there is no path

    public  Object lbl;
    public boolean x = false;

    public Vertex(Object label) {
        this.lbl = label;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public Edge getEdgeTo(Vertex v2) {
        for (Edge edge : edges) {
            if (edge.contains(this, v2))
                return edge;
        }
        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(lbl);
    }
}
