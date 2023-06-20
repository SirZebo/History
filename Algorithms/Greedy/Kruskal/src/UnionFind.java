import java.util.HashMap;
import java.util.Map;

public class UnionFind<T> {
    private final Map<Vertex<T>, Integer> size;
    private final Map<Vertex<T>, Vertex<T>> roots;

    public UnionFind() {
        size = new HashMap<>();
        roots = new HashMap<>();
    }

    public boolean union(Vertex<T> p, Vertex<T> q) {
        if (!roots.containsKey(p)) {
            roots.put(p, p);
            size.put(p, 1);
        }
        if (!roots.containsKey(q)) {
            roots.put(q, q);
            size.put(q, 1);
        }
        Vertex<T> rootP = getRoot(p), rootQ = getRoot(q);
        if (rootP.equals(rootQ)) return false;
        int sizeP = size.get(rootP), sizeQ = size.get(rootQ);
        if (sizeP > sizeQ) {
            size.put(rootP, sizeP + sizeQ);
            roots.put(rootQ, rootP);
        } else {
            size.put(rootQ, sizeP + sizeQ);
            roots.put(rootP, rootQ);
        }
        return true;
    }

    private Vertex<T> getRoot(Vertex<T> vertex) {
        return roots.get(vertex).equals(vertex) ? vertex : getRoot(roots.get(vertex));
    }
}
