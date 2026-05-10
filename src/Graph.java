import java.util.*;

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
            System.out.print("Vertex " + entry.getKey() + " is connected to: ");
            for (Edge edge : entry.getValue()) {
                System.out.print(edge.getDestination().getId() + " ");
            }
            System.out.println();
        }
    }

    public void bfs(int start){
        if(!vertices.containsKey(start)){
            System.out.println("Start vertex: " + start + "not found");
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while(!queue.isEmpty()){
            int current = queue.poll();
            System.out.println("Current: " + current);

            List<Edge> edges = adjList.getOrDefault(current, Collections.emptyList());
            for(Edge edge : edges){
                int neighbor = edge.getDestination().getId();
                if(!visited.contains(neighbor)){
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public void dfs(int start){
        if(!vertices.containsKey(start)){
            System.out.println("Start vertex: " + start + "not found");
            return;
        }

        Set<Integer> visited = new HashSet<>();
        dfsHelper(start,visited);
        System.out.println();
    }

    private void dfsHelper(int current, Set<Integer> visited){
        visited.add(current);
        System.out.println("Current: " + current);

        List<Edge> edges = adjList.getOrDefault(current,Collections.emptyList());
        for(Edge edge: edges){
            int neighbor = edge.getDestination().getId();
            if(!visited.contains(neighbor)){
                dfsHelper(neighbor,visited);
            }
        }
    }

    public List<Integer> getNeighbors(int vertexId) {
        List<Integer> neighbors = new ArrayList<>();
        List<Edge> edges = adjList.get(vertexId);
        if (edges != null) {
            for (Edge edge : edges) {
                neighbors.add(edge.getDestination().getId());
            }
        }
        return neighbors;
    }
}
