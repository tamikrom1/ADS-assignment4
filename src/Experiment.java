import java.util.*;

public class Experiment {
    private Graph smallGraph;
    private Graph mediumGraph;
    private Graph largeGraph;

    private long smallBfsTime, smallDfsTime;
    private long mediumBfsTime, mediumDfsTime;
    private long largeBfsTime, largeDfsTime;

    public void runMultipleTests(){
        smallGraph = generateRandomGraph(10, 15);
        mediumGraph = generateRandomGraph(30, 60);
        largeGraph = generateRandomGraph(100, 250);

        smallGraph.printGraph();

        System.out.println("\nSmall Graph Traversal Orders");
        long start = System.nanoTime();
        System.out.print("BFS Order: ");
        smallGraph.bfs(1);
        smallBfsTime = System.nanoTime() - start;

        start = System.nanoTime();
        System.out.print("DFS Order: ");
        smallGraph.dfs(1);
        smallDfsTime = System.nanoTime() - start;

        mediumGraph.printGraph();
        System.out.println("\nMedium Graph Traversal Orders");
        start = System.nanoTime();
        System.out.print("BFS Order: ");
        mediumGraph.bfs(1);
        mediumBfsTime = System.nanoTime() - start;

        start = System.nanoTime();
        System.out.print("DFS Order: ");
        mediumGraph.dfs(1);
        mediumDfsTime = System.nanoTime() - start;

        largeGraph.printGraph();
        System.out.println("\nLarge Graph Traversal Orders");
        start = System.nanoTime();
        System.out.print("BFS Order: ");
        largeGraph.bfs(1);
        largeBfsTime = System.nanoTime() - start;

        start = System.nanoTime();
        System.out.print("DFS Order: ");
        largeGraph.dfs(1);
        largeDfsTime = System.nanoTime() - start;

    }

    private Graph generateRandomGraph(int numVertices, int numEdges) {
        Graph g = new Graph();
        for (int i = 1; i <= numVertices; i++) {
            g.addVertex(new Vertex(i));
        }
        Random rand = new Random(1); // Seeded for reproducibility
        int edgesAdded = 0;
        while (edgesAdded < numEdges) {
            int from = rand.nextInt(numVertices) + 1;
            int to = rand.nextInt(numVertices) + 1;
            if (from != to) {
                g.addEdge(from, to);
                edgesAdded++;
            }
        }
        return g;
    }

    public void printResults() {
        System.out.println("=============Execution Time Comparison===============");
        System.out.println("Graph Size: Small(10) " + "BFS Time: " + smallBfsTime + " DFS Time: " + smallDfsTime);
        System.out.println("Graph Size: Medium(30) " + "BFS Time: " + mediumBfsTime + " DFS Time: " + mediumDfsTime);
        System.out.println("Graph Size: Large(100) " + "BFS Time: " + largeBfsTime + " DFS Time: " + largeDfsTime);
        System.out.println("====================================================");
    }

    public void runTraversals(Graph g) { //
        System.out.println("BFS Execution:");
        g.bfs(1);
        System.out.println("DFS Execution:");
        g.dfs(1);
    }

}
