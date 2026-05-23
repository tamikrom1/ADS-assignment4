public class Main{
    static void main(String[] args) {
        Experiment experiment = new Experiment();
        experiment.runMultipleTests(); // Runs tests on Small, Medium, Large
        experiment.printResults();

        System.out.println("\n==========Dijkstra's Algorithm============\n");
        Graph weighted = new Graph();

        weighted.addEdge(1, 2, 4);
        weighted.addEdge(1, 3, 1);
        weighted.addEdge(3, 2, 2);
        weighted.addEdge(2, 4, 1);
        weighted.addEdge(4, 5, 3);
        weighted.addEdge(3, 6, 5);

        System.out.println("Weighted graph structure:");
        weighted.printGraph();

        weighted.dijkstra(1);
    }
}