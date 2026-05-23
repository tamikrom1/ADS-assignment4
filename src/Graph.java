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

    public void addEdge(int from, int to) {
        addEdge(from, to, 1);
    }

    // New weighted addEdge
    public void addEdge(int from, int to, int weight) {
        Vertex source = vertices.computeIfAbsent(from, id -> { adjList.put(id, new ArrayList<>()); return new Vertex(id); });
        Vertex destination = vertices.computeIfAbsent(to, id -> { adjList.put(id, new ArrayList<>()); return new Vertex(id); });

        Edge edge = new Edge(source, destination, weight);
        adjList.get(from).add(edge);
    }

    public void printGraph() {
        for (Map.Entry<Integer, List<Edge>> entry : adjList.entrySet()) {
            System.out.print("Vertex " + entry.getKey() + " is connected to: ");
            for (Edge edge : entry.getValue()) {
                System.out.print(edge.getDestination().getId() + "(w=" + edge.getWeight() + ") ");
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

    public void dijkstra(int start) {
        if (!vertices.containsKey(start)) {
            System.out.println("Start vertex " + start + " not found.");
            return;
        }

        List<Integer> ids = new ArrayList<>(vertices.keySet());
        Collections.sort(ids);
        int n = ids.size();

        Map<Integer, Integer> idToIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            idToIndex.put(ids.get(i), i);
        }

        int startIdx = idToIndex.get(start);

        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startIdx] = 0;

        for (int iter = 0; iter < n; iter++) {
            int uIdx = -1;
            for (int i = 0; i < n; i++) {
                if (!visited[i] && (uIdx == -1 || dist[i] < dist[uIdx])) {
                    uIdx = i;
                }
            }

            if (uIdx == -1 || dist[uIdx] == Integer.MAX_VALUE) break;

            visited[uIdx] = true;
            int uId = ids.get(uIdx);

            List<Edge> edges = adjList.getOrDefault(uId, Collections.emptyList());
            for (Edge edge : edges) {
                int vId  = edge.getDestination().getId();
                int vIdx = idToIndex.get(vId);
                if (!visited[vIdx] && dist[uIdx] != Integer.MAX_VALUE) {
                    int newDist = dist[uIdx] + edge.getWeight();
                    if (newDist < dist[vIdx]) {
                        dist[vIdx] = newDist;
                    }
                }
            }
        }

        // Print results
        System.out.println("Dijkstra shortest distances from vertex " + start + ":");
        for (int i = 0; i < n; i++) {
            int vertexId = ids.get(i);
            String distStr = (dist[i] == Integer.MAX_VALUE) ? "Unreachable" : String.valueOf(dist[i]);
            System.out.println("  Vertex " + start + " -> Vertex " + vertexId + " : " + distStr);
        }
        System.out.println();
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
