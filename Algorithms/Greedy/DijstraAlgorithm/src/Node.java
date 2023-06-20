import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node<T> implements Comparable<Node<T>> {
    private final T data;
    private Integer distance = Integer.MAX_VALUE;
    private List<Node<T>> shortestPath = new ArrayList<>();
    private Map<Node<T>, Integer> neighbors = new HashMap<>();

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<Node<T>, Integer> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Node<T> node, int weight) {
        neighbors.put(node, weight);
    }

    public List<Node<T>> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node<T>> shortestPath) {
        this.shortestPath = shortestPath;
    }

    @Override
    public int compareTo(Node<T> that) {
        return Integer.compare(this.distance, that.distance);
    }

    @Override
    public String toString() {
        return "" + data;
    }
}
