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


}
