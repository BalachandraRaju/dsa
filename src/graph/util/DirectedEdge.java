package graph.util;

public class DirectedEdge {

    public  DirectedVertex tail;
    public  DirectedVertex head;

    public DirectedEdge(DirectedVertex tail, DirectedVertex head){
        this.tail = tail;
        this.head = head;
    }

    @Override
    public String toString() {
        return String.format("{%s -> %s}",tail.lbl,head.lbl);
    }
}
