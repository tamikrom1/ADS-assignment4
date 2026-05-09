import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private final Map<Integer,Vertex> vertices;
    private final Map<Integer, List<Edge>> adjList;

    public Graph(){
        this.vertices = new HashMap<>();
        this.adjList = new HashMap<>();
    }

    public void addVertex(Vertex v){
        if(!vertices.containsKey(v.getId())){
            vertices.put(v.getId(), v);
            adjList.put(v.getId(), new ArrayList<>());
        }
    }

    public void addEdge(int from, int to){
        Vertex source = vertices.computeIfAbsent(from, Vertex::new);
        Vertex destination = vertices.computeIfAbsent(to, Vertex::new);

        addVertex(source);
        addVertex(destination);

        Edge edge = new Edge(source,destination);
        adjList.get(from).add(edge);
    }

    public void printGraph(){
        for(Map.Entry<Integer, List<Edge>> entry: adjList.entrySet()){
            for (Edge edge : entry.getValue()) {
                System.out.print(edge.getDestination().getId() + " ");
            }
            System.out.println();
        }
    }
}
