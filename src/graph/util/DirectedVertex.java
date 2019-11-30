package graph.util;

import java.util.ArrayList;
import java.util.List;

public class DirectedVertex {

    public  Object lbl;
    public  int curr;
    public int scc;

    public boolean explored =false;

    public   List<DirectedEdge> incomingEdges = new ArrayList<>();
    public   List<DirectedEdge> outgoingEdges = new ArrayList<>();

    public DirectedVertex(Object lbl){
        this.lbl = lbl;
    }

    @Override
    public String toString() {
        return String.format("%s",lbl);
    }

    public void addIncomingEdge(DirectedEdge edge){
        incomingEdges.add(edge);
    }

    public void addOutgoingEdge(DirectedEdge edge){
        outgoingEdges.add(edge);
    }

    public boolean hasOutgoingEdgeTo(DirectedVertex head){
        for(DirectedEdge edge : outgoingEdges){
            if(edge.head == head){
                return true;
            }
        }
        return false;
    }
}
