import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class KruskalAlgorithm<T> {
    final int N;
    final List<Vertex<T>> vertices;
    final PriorityQueue<Edge<T>> graph;

    public KruskalAlgorithm(List<Edge<T>> graph) {
        this.graph = new PriorityQueue<>(graph);
        this.vertices = getVertices(graph);
        this.N = vertices.size();
    }

    public List<Edge<T>> getMST() {
        List<Edge<T>> spanningTree = new ArrayList<>();
        UnionFind<T> uf = new UnionFind<>();

        while (spanningTree.size() < this.N - 1) {
            Edge<T> curr = graph.poll();
            if (uf.union(curr.getSrc(), curr.getDstn())) {
                spanningTree.add(curr);
            }
        }
        printTreeInfo(spanningTree);
        return spanningTree;
    }

    private List<Vertex<T>> getVertices(List<Edge<T>> edges) {
        return Stream.concat(
                edges.stream().map(Edge::getSrc),
                edges.stream().map(Edge::getDstn)
        ).distinct().toList();
    }

    private void printTreeInfo(List<Edge<T>> spanningTree) {
        Integer min = spanningTree.stream()
                .map(Edge::getWeight)
                .reduce(0, Integer::sum);

        spanningTree.forEach(System.out::println);
        System.out.println("Minimum weight: " + min);
    }
}
