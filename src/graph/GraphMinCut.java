package graph;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GraphMinCut {

    List<Edge> edges = new ArrayList<>();
    List<Vertex> vertices = new ArrayList<>();

    public GraphMinCut() {

    }

    public static void main(String[] args) throws Exception {
        GraphMinCut graph  = new GraphMinCut();

        graph.addVertex("1");
        graph.addVertex("2");
        graph.addVertex("3");
        graph.addVertex("4");
        graph.addVertex("5");
        graph.addVertex("6");

        graph.addEdge("1","2");
        //graph.addEdge("2","1");
        graph.addEdge("1","6");
        //graph.addEdge("6","1");
        graph.addEdge("2","5");
        //graph.addEdge("5","2");
        graph.addEdge("2","3");
        //graph.addEdge("3","2");
        graph.addEdge("3","4");
        //graph.addEdge("4","3");
        graph.addEdge("5","6");
        //graph.addEdge("6","5");
        graph.addEdge("5","4");
        //graph.addEdge("4","5");

        //graph = createGraph();

        //System.out.format("No of vertices: %d\nNo of edges: %d \n %s \n",graph.vertices.size(),graph.edges.size(),graph);

        graph.minimumCut();
        System.out.format(" Size: %d \nMinimum cut = %s\n",graph.edges.size(),graph.edges);
    }

    void addVertex(String a) {
        Vertex v = new Vertex(a);
        vertices.add(v);
    }

    void addEdge(String x, String y) {

        Vertex x1 = null;
        Vertex y1 = null;
        for(Vertex vertex :vertices){
            if(vertex.a.equals(x)){
                x1 = vertex;
            }else if(vertex.a.equals(y)){
                y1 = vertex;
            }
            if(x1 != null && y1 != null)
                break;
        }
        if(x1 == null){
            throw new IllegalArgumentException("Vertex: "+x+ " does not exist, please first add it");
        }
        if(y1 == null){
            throw new IllegalArgumentException("Vertex: "+y+ " does not exist, please first add it");
        }

        Edge edge = new Edge(x1, y1);
        x1.edges.add(y1);
        y1.edges.add(x1);
        edges.add(edge);

    }

    void contractEdge(Edge e1) {
        //fuse the vertices of edge e1 that needs to be contracted into one single ContractedEdgeVertex.
        String label =  String.join("-",e1.p1.a,e1.p2.a);
        ContractedEdgeVertex cev = new ContractedEdgeVertex(label);
        cev.fuseEdgeVertices(e1);

        for(Vertex vertex : vertices){
            if((vertex.equals(e1.p1) ||  vertex.equals(e1.p2))){
                //if vertex is equal to any of p1 or p2 of the contracted edge,
                // then ignore because those vertices will be eventually deleted at end of this method.
                continue;
            }
            if(vertex.edges.contains(e1.p1)  ){
                vertex.edges.remove(e1.p1);
                vertex.edges.add(cev);
            }
            if( vertex.edges.contains(e1.p2)){
                vertex.edges.remove(e1.p2);
                vertex.edges.add(cev);
            }
        }

        for(Edge edge : edges){
            if(edge.equals(e1)){
                continue;
            }
            if(edge.p1.equals(e1.p1) || edge.p1.equals(e1.p2)){
                edge.p1 = cev;
            }else if(edge.p2.equals(e1.p2) || edge.p2.equals(e1.p1)){
                edge.p2 = cev;
            }
        }

        //replace contracted edge vertices with newly constructed contractededgevertex
        vertices.remove(e1.p1);
        vertices.remove(e1.p2);
        vertices.add(cev);

        //remove the contracted edge
        edges.remove(e1);
    }

    @Override
    public String toString() {
        String verticesStr = vertices.stream().map(v -> String.valueOf(v.a)).collect(Collectors.joining(", "));
        String edgesStr = edges.stream().map(e -> String.format("{%s,%s} ", e.p1.a, e.p2.a)).collect(Collectors.joining());
        return String.format("Vertices: [ %s ] \n Edges: [ %s ] ", verticesStr, edgesStr);
    }

    private void minimumCut() throws Exception {

        Random random = new Random();
        int n = edges.size();
        while (vertices.size() > 2) {
            if(n <0 ) break;;
            int index = random.nextInt(n);
            Edge edge = edges.get(index);
            System.out.format("Edges = %d\t Vertices = %d\n",edges.size(),vertices.size());
            contractEdge(edge);
            n--;
        }
        //System.out.println(this);
    }

    private static GraphMinCut createGraph() throws IOException {
        String file = "C:\\Users\\Bharath\\IdeaProjects\\Developement\\ModuleOne\\src\\dev\\kargerMinCut.txt";
        FileInputStream fis = new FileInputStream(file);

        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line = null;
        GraphMinCut graph = new GraphMinCut();

        List<String>  edgeVertices = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            line = line.trim();
            edgeVertices.add(line);

            String label = line.substring(0,line.indexOf("\t"));
            Vertex v = new Vertex(label);
            graph.vertices.add(v);

        }

        for (String edgeVerticesStr : edgeVertices) {

            String[] values = edgeVerticesStr.split("\\t");
            String root = values[0];

            for(int i=1; i< values.length;i++){
                String otherVertexLabel = values[i].trim();
                graph.addEdge(root,otherVertexLabel);
            }
        }
        return graph;
    }

    static class Vertex {
        String a;
        List<Vertex> edges = new ArrayList<>();

        Vertex(String a) {
            this.a = a;
        }

        void addEdge(Vertex k) {
            edges.add(k);
        }

        @Override
        public String toString() {
            return String.format(" v = %3s\t edges = { %s } ", this.a,
                    edges.stream().map(v -> String.valueOf(v.a)).collect(Collectors.joining(", ")));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Vertex vertex = (Vertex) o;

            boolean isTrue =  a != null ? a.equals(vertex.a) : vertex.a == null;
            return  isTrue;
        }

        @Override
        public int hashCode() {
            return a != null ? a.hashCode() : 0;
        }
    }

    class ContractedEdgeVertex extends Vertex{

        List<Vertex> fusedEdgeVertices = new ArrayList<>();
        ContractedEdgeVertex(String a) {
            super(a);
        }

        void fuseEdgeVertices(Edge edge){

           /* if(edge.p1 instanceof ContractedEdgeVertex){
                ContractedEdgeVertex p1Cev = (ContractedEdgeVertex)edge.p1;
                fusedEdgeVertices.addAll(p1Cev.fusedEdgeVertices);
            }else{
                fusedEdgeVertices.add(edge.p1);
            }

            if(edge.p2 instanceof ContractedEdgeVertex){
                ContractedEdgeVertex p2Cev = (ContractedEdgeVertex)edge.p2;
                fusedEdgeVertices.addAll(p2Cev.fusedEdgeVertices);
            }else{
                fusedEdgeVertices.add(edge.p2);
            }*/

            //fusedEdgeVertices.add(edge.p1);
            //fusedEdgeVertices.add(edge.p2);

            //all p1 edges needs to be updated with this newly created ContractedEdgeVertex
          List<Vertex> p1Vertices =   new ArrayList<>(edge.p1.edges);
          List<Vertex> p2Vertices =   new ArrayList<>(edge.p2.edges);

          //remove the vertex that is involved in the edge that is going to be contracted.
          p1Vertices.remove(edge.p2);
          p2Vertices.remove(edge.p1);

          //List<Vertex> commonVertices = p1Vertices.stream().filter(p2Vertices::contains).collect(Collectors.toList());

          edges.addAll(p1Vertices);
          edges.addAll(p2Vertices);
        }
    }
    static class Edge {
        Vertex p1;
        Vertex p2;

        Edge(Vertex x, Vertex y) {
            this.p1 = x;
            this.p2 = y;
        }

        @Override
        public String toString() {
            return String.format("v1=%3s\tv2=%3s", p1.a, p2.a);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Edge edge = (Edge) o;

            //if (!p1.equals(edge.p1)) return false;
            //return p2.equals(edge.p2);

            if(p1.equals(edge.p1) && p2.equals(edge.p2))
                return true;

            if(p1.equals(edge.p2) && p2.equals(edge.p1))
                return true;

            return false;
        }

        @Override
        public int hashCode() {
            int result = p1.hashCode();
            result = 31 * result + p2.hashCode();
            return result;
        }
    }
}
