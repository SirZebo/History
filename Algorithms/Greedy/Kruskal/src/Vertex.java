import java.util.HashSet;
import java.util.Set;

public class Vertex<T> {

    private final T data;
    private Vertex<T> parent;
    private boolean visited;
    private boolean beingVisited;
    private Set<Vertex<T>> neighbors = new HashSet<>();

    public Vertex(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setNeighbors(Set<Vertex<T>> neighbors) {
        this.neighbors = neighbors;
    }

    public Set<Vertex<T>> getNeighbors() {
        return neighbors;
    }

    public void addNeighbors(Vertex<T> vertex) {
        neighbors.add(vertex);
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setBeingVisited(boolean beingVisited) {
        this.beingVisited = beingVisited;
    }

    public boolean isBeingVisited() {
        return beingVisited;
    }

    @Override
    public String toString() {
        return "" + data;
    }


}
