public class Edge<T> implements Comparable<Edge<T>> {

    private final Vertex<T> src;
    private final Vertex<T> dstn;
    private final int weight;

    public Edge(Vertex<T> src, Vertex<T> dstn, int weight) {
        this.src = src;
        this.dstn = dstn;
        this.weight = weight;
    }

    public Vertex<T> getSrc() {
        return src;
    }

    public Vertex<T> getDstn() {
        return dstn;
    }

    public int getWeight() {
        return weight;
    }

    public int compareTo(Edge<T> that) {
        return Integer.compare(this.weight, that.getWeight());
    }
}
