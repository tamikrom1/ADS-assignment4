public class Edge {
    private final Vertex source;
    private final Vertex destination;

    public Edge(Vertex source, Vertex destination){
        this.source = source;
        this.destination = destination;
    }

    public Vertex getDestination() {
        return destination;
    }

    public Vertex getSource() {
        return source;
    }

    @Override
    public String toString() {
        return source.getId() + " -> " + destination.getId();
    }
}
