package graph;

import graph.util.Edge;
import graph.util.Graph;
import graph.util.Vertex;

import java.util.LinkedList;
import java.util.Stack;

public class GraphSearch {

    public static void main(String[] args){
        GraphSearch graphSearch = new GraphSearch();
        graphSearch.dfs();
    }

    private void dfs(){
        Graph graph = createGraph();

        Stack<Vertex> stack = new Stack<>();
        Vertex vertex  = graph.getVertex(1);
        stack.push(vertex);

        while(!stack.isEmpty()){
            Vertex s = stack.pop();
            if(!s.explored){
                s.explored = true;
                System.out.print(s.lbl+" ");
                for(Edge e : s.edges){
                    Vertex v = e.getOppositeVertex(s);
                    stack.push(v);
                }
            }
        }
    }

    private void bfs() {
        Graph graph = createGraph();
        Vertex v = graph.getVertex(1);
        v.explored=true;

        LinkedList<Vertex> queue = new LinkedList<>();
        queue.addLast(v);
        System.out.print(v.lbl+" ");
        while(!queue.isEmpty()){
            Vertex s = queue.removeFirst();
            for(Edge e: s.edges){
                Vertex t = e.getOppositeVertex(s);
                if(t != null && !t.explored){
                    t.explored=true;
                    queue.addLast(t);
                    System.out.print(t.lbl+" ");
                }
            }
        }
        System.out.println();
    }

    private Graph createGraph() {
        Graph graph = new Graph();

        Vertex one = new Vertex(1);
        Vertex three = new Vertex(3);
        Vertex five = new Vertex(5);
        Vertex seven = new Vertex(7);
        Vertex nine = new Vertex(9);

        Edge oneThree = new Edge(one,three);
        Edge oneFive = new Edge(one,five);
        Edge fiveSeven = new Edge(five,seven);
        Edge threeNine = new Edge(three,nine);
        Edge sevenNine = new Edge(seven,nine);

        one.addEdge(oneThree);
        three.addEdge(oneThree);

        one.addEdge(oneFive);
        five.addEdge(oneFive);

        seven.addEdge(sevenNine);
        nine.addEdge(sevenNine);

        five.addEdge(fiveSeven);
        seven.addEdge(fiveSeven);

        three.addEdge(threeNine);
        nine.addEdge(threeNine);

        graph.addVertex(one);
        graph.addVertex(three);
        graph.addVertex(five);
        graph.addVertex(seven);
        graph.addVertex(nine);

        graph.edges.add(oneThree);
        graph.edges.add(oneFive);
        graph.edges.add(sevenNine);
        graph.edges.add(fiveSeven);
        graph.edges.add(threeNine);

        return graph;
    }

}
