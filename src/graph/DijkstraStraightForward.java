package graph;

import graph.util.Edge;
import graph.util.Graph;
import graph.util.Vertex;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class DijkstraStraightForward {

    public static void main(String[] args) throws  Exception{
        DijkstraStraightForward dijkstraStraightForward = new DijkstraStraightForward();
        dijkstraStraightForward.search();
    }

    private void search() throws Exception{
        Graph graph = createGraph();
        //System.out.println(graph.edges.size()+" "+graph.vertices.size());

        List<VertexEdge> toBeScanned = new ArrayList<>();

        Vertex vertex = graph.getVertex(1);
        vertex.x = true;
        vertex.shortestDistance = 0;

        findShortestDistance(toBeScanned,vertex);

        StringJoiner joiner = new StringJoiner(",");
        for(Integer vlbl : Arrays.asList(7,37,59,82,99,115,133,165,188,197)){
            Vertex vertex1 = graph.vertices.get(vlbl);
            System.out.format("Shortest path of %s from %s : %d\n",vertex1.lbl,"1",vertex1.shortestDistance);
            joiner.add(String.valueOf(vertex1.shortestDistance));
        }
        System.out.println(joiner.toString());
    }

    class VertexEdge {
        public Vertex source;
        public Edge edge;

       public VertexEdge(Vertex source,Edge edge){
           this.source = source;
           this.edge = edge;
       }
    }

    private void buildHeap(Vertex fromV){
        if(!fromV.x){
            throw new IllegalArgumentException("fromV is not in X or already visited");
        }

    }
    private void findShortestDistance(List<VertexEdge> toBeScanned, Vertex fromV) {

        if(!fromV.x){
            throw new IllegalArgumentException("fromV is not in X or already visited");
        }

        for(Edge toEdge: fromV.edges){
            toBeScanned.add(new VertexEdge(fromV,toEdge));
        }

        Vertex selectedToVertex = null;
        VertexEdge vertexEdge = null;
        int min = Integer.MAX_VALUE;

        Iterator<VertexEdge> iterator = toBeScanned.listIterator();

        while(iterator.hasNext()) {
            VertexEdge entry = iterator.next();
            Edge edge = entry.edge;
            Vertex source = entry.source;

            Vertex toV = edge.getOppositeVertex(source);
            if (!toV.x) {
                int score = source.shortestDistance + edge.length;
                if (score < min) {
                    min = score;
                    selectedToVertex = toV;
                    vertexEdge = entry; //this needs to be removed
                }
            }else{
                iterator.remove();// because source is x, and toV is x hence the edge could be removed from scanner
            }
        }
        if(selectedToVertex == null){
            //there is  no  vertex ("toV") from vertex "fromV" which is NOT part of X.
            //this means nothing to scan ( a vertex from X to a vertex in V-X)
            return;
        }

        selectedToVertex.x = true;
        selectedToVertex.shortestDistance = min;

        toBeScanned.remove(vertexEdge);

        findShortestDistance(toBeScanned,selectedToVertex);
    }

    private Graph createGraph() throws  Exception {
        Graph graph = new Graph();
        String file =
                "C:\\Users\\Bharath\\IdeaProjects\\Developement\\ModuleOne\\src\\graph\\util\\dijkstraData.txt";

        BufferedReader br  = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line ;
        while( (line = br.readLine()) != null){
            int firstSpaceIdx = line.trim().indexOf('\t');
            String source = line.substring(0,firstSpaceIdx);
            Vertex sourceVertex = graph.getVertex(Integer.parseInt(source));

            line = line.substring(firstSpaceIdx+1);
            String[] vertices = line.trim().split("\\s");

            for(String toVertexLength : vertices){
                String[] vertexLength = toVertexLength.split(",");

                String toVertexStr = vertexLength[0];
                int length =   Integer.parseInt(vertexLength[1]);

                Vertex toVertex =     graph.getVertex(Integer.parseInt(toVertexStr));
                Edge edge;
                if( (edge = toVertex.getEdgeTo( sourceVertex )) == null ) {
                    edge = new Edge(sourceVertex,toVertex);
                    edge.length = length;
                    graph.edges.add(edge);
                    //undirected edge to be added to both the vertices (source and toVertex)
                    sourceVertex.addEdge(edge);
                    toVertex.addEdge(edge);
                }
            }
        }

        return graph;
    }
}
