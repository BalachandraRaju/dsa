package graph.util;

import java.util.ArrayList;
import java.util.List;

public class Edge {

    List<Vertex> ends = new ArrayList<>();
    public int length = 1000000;

    public Edge(Vertex s,Vertex v){
        if( s == null || v == null ) {
            throw new IllegalArgumentException( "Both vertices are required" );
        }
        ends.add(s);
        ends.add(v);
    }

    public boolean contains( Vertex v1, Vertex v2 ) {
        return ends.contains( v1 ) && ends.contains( v2 );
    }

    public Vertex getOppositeVertex( Vertex v ) {
        if( !ends.contains( v ) ) {
            throw new IllegalArgumentException( "Vertex " + v.lbl );
        }
        return ends.get( 1 - ends.indexOf( v ) );
    }


    @Override
    public String toString() {
        return String.format("{%s,%s}\n",ends.get(0),ends.get(1));
    }
}
