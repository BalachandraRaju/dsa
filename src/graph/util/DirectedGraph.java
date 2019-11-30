package graph.util;

import java.util.*;

public class DirectedGraph {

    public List<DirectedEdge> edges = new ArrayList<>();
    public Map<String,DirectedVertex> vertices = new LinkedHashMap<>();

    public DirectedVertex addVertex(String lbl){
        DirectedVertex directedVertex = new DirectedVertex(lbl);
        vertices.put(lbl,directedVertex);
        return  directedVertex;
    }

    public DirectedVertex getVertex(String lbl){
        DirectedVertex directedVertex;
        if((directedVertex = vertices.get(lbl)) == null){
             directedVertex = addVertex(lbl);
        }
        return directedVertex;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        for(Map.Entry<String,DirectedVertex> entry : vertices.entrySet()){
            joiner.add(String.format("%s %s",entry.getKey(),entry.getValue().outgoingEdges));
        }
        return joiner.toString();
    }
}
