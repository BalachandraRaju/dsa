package graph;

import graph.util.DirectedEdge;
import graph.util.DirectedGraph;
import graph.util.DirectedVertex;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class SCC {

    int numSCC = 0;

    private int curr;
    Stack<DirectedVertex> firstDFSPassResults = new Stack<>();
    Map<Integer,List<DirectedVertex>> sccVertexMap = new LinkedHashMap<>();

    public static void main(String[] args) throws Exception {
        SCC scc = new SCC();
        scc.findSccs();
    }

    private void findSccs() throws Exception {
        DirectedGraph graph = createGraph();
       /* graph.vertices.entrySet().stream().filter( e -> e.getKey().equals("52218"))
                .forEach(e -> System.out.format("%s %s %s",e.getKey(),e.getValue().incomingEdges,e.getValue().outgoingEdges));
        */
        //System.out.println(graph.edges.toString().replaceAll(",\\s+","\n"));
        topSort(graph);

       /* while(!firstDFSPassResults.empty()) {
            DirectedVertex dv = firstDFSPassResults.pop();
            System.out.println(String.format("Vertex:%s\tLabel:%s\tExplored:%s",dv,dv.curr,dv.explored ));
        }*/
        //System.exit(1);
        setAllVerticesUnexplored(graph);
        dfsSecondPass();
        List<Integer> list =  sccVertexMap.values().stream().mapToInt(List::size).boxed()
                .sorted(Comparator.reverseOrder()).limit(5).collect(Collectors.toList());
        System.out.println(list.toString().replaceAll(",\\s+","\r\n"));
        for(Map.Entry<Integer,List<DirectedVertex>> entry: sccVertexMap.entrySet()){
            for(DirectedVertex v : entry.getValue()){
                if(v.lbl.equals("52218") || v.lbl.equals("52219") || v.lbl.equals("809804")){
                    System.out.format("Vertex:%s Size:%d SCClabel:%s OutgoingEdges:%s IncomingEdges:%s\n"
                            ,v,entry.getValue().size(), entry.getKey(),v.outgoingEdges,v.incomingEdges);
                }
            }
        }
        //sccVertexMap.entrySet().forEach(e -> System.out.format("%s %s \n",e.getKey(),e.getValue()));

    }


    private void topSort(DirectedGraph graph) {
        curr = graph.vertices.size();
        for(Map.Entry<String,DirectedVertex> entry : graph.vertices.entrySet()){
            DirectedVertex vertex = entry.getValue();
            if(!vertex.explored){
                dfsTopoRecursive(vertex);
                //dfsTopoIterative(vertex);
            }
        }
    }

    private void dfsSecondPass() {
        while(!firstDFSPassResults.empty()){
            DirectedVertex dv = firstDFSPassResults.pop();
            //System.out.println(String.format("%s : %s : %s",dv,dv.curr,dv.explored ));
            if(!dv.explored){
                ++numSCC;
                //dffSccRecursive(dv);
                dffSccIterative(dv);
                /*sccVertexMap.entrySet().stream().filter(e -> e.getKey() == numSCC)
                        .forEach(e -> System.out.format("***%s [%s]***\n",e.getKey(),
                                e.getValue().stream().map(d -> String.format("%s(%s) \t",d.lbl,d.curr))
                                        .collect(Collectors.joining(", "))));*/
            }
        }
    }

    private void dffSccRecursive(DirectedVertex dv) {
        dv.explored = true;
        dv.scc = numSCC;
        List<DirectedVertex> list;
        if( (list = sccVertexMap.get(numSCC))== null){
            list = new LinkedList<>();
        }
        list.add(dv);
        sccVertexMap.put(numSCC,list);

        for(DirectedEdge outgoingEdge: dv.outgoingEdges){
            DirectedVertex head = outgoingEdge.head;
            if(!head.explored){
                dffSccRecursive(head);
            }
        }
    }
    private void dffSccIterative(DirectedVertex dv){
        Stack<DirectedVertex> stack = new Stack<>();
        stack.push(dv);

        while(!stack.isEmpty()){
            DirectedVertex directedVertex = stack.pop();
            if(!directedVertex.explored){
                directedVertex.explored =true;
                directedVertex.scc = numSCC;
                List<DirectedVertex> list;
                if( (list = sccVertexMap.get(numSCC))== null){
                    list = new LinkedList<>();
                    sccVertexMap.put(numSCC,list);
                }
                list.add(directedVertex);
                for(DirectedEdge outgoingEdge: directedVertex.outgoingEdges){
                    stack.push(outgoingEdge.head);
                }
            }
        }
    }

    private void dfsTopoRecursive(DirectedVertex vertex) {
        vertex.explored = true;

        for (DirectedEdge incomingEdge : vertex.incomingEdges) {
            if (!incomingEdge.tail.explored) {
                dfsTopoRecursive(incomingEdge.tail);
            }
        }
        vertex.curr = curr;
        firstDFSPassResults.push(vertex);
        curr = curr-1;
    }

    private void dfsTopoIterative(DirectedVertex vertex){
        Stack<DirectedVertex> stack = new Stack<>();
        stack.push(vertex);

        while(!stack.isEmpty()){
            DirectedVertex ver = stack.pop();
            if(!ver.explored){
                ver.explored = true;
                ver.curr = curr;
                firstDFSPassResults.push(ver);
                curr = curr-1;
                for(DirectedEdge incomingEdge : ver.incomingEdges){
                    stack.push(incomingEdge.tail);
                }
            }
        }
    }

    private void setAllVerticesUnexplored(DirectedGraph graph) {
        for(DirectedVertex vertex : graph.vertices.values()){
            vertex.explored = false;
        }
    }

    private DirectedGraph createGraph() throws  Exception {

        DirectedGraph graph = new DirectedGraph();

        String fileName = "C:\\Users\\Bharath\\IdeaProjects\\Developement\\ModuleOne\\src\\graph\\util\\SCC.txt";
                //"C:\\Users\\Bharath\\IdeaProjects\\Developement\\ModuleOne\\src\\graph\\util\\SCC.txt";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String line;
        while(( line = bufferedReader.readLine()) != null){
            String[] strVertices = line.split("\\s+");

            String tailStr = strVertices[0].trim();
            String headStr = strVertices[1].trim();

            DirectedVertex  tail = graph.getVertex(tailStr);
            DirectedVertex  head = graph.getVertex(headStr);

            DirectedEdge edge = new DirectedEdge(tail,head);
            graph.edges.add(edge);
            tail.addOutgoingEdge(edge);
            head.addIncomingEdge(edge);
        }

        return  graph;
    }

}
