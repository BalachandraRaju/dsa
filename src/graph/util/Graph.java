package graph.util;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {

    public final Map<Object,Vertex> vertices = new LinkedHashMap<>();
    public final List<Edge> edges = new ArrayList<>();

    public void addVertex(Vertex v){
        vertices.put( v.lbl, v );
    }

    public Vertex getVertex( int lbl ) {
        Vertex v;
        if( ( v = vertices.get( lbl )) == null ) {
            v = new Vertex( lbl );
            addVertex( v );
        }
        return v;
    }

    @Override
    public String toString() {
        return vertices.values().stream()
                .map(v -> String.format("Vertex:%s \t Edges:\n%s",
                        v.lbl,v.edges.toString().replaceAll("\\[|\\]","")))
                .collect(Collectors.joining("\n"));
    }
}
