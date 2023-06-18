import java.util.*;

public class SAP {
    final Digraph G;
    Map<Map.Entry<Integer, Integer>, Map.Entry<Integer, Integer>> sapMap; // Map< Pair<v,w>, Pair<Length, Ancestor>>

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null) throw new IllegalArgumentException("SAP: digraph cannot be null!");
        this.G = G; // maybe use copy contructor?
        this.sapMap = new HashMap<>();
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        doesVertexExist(v);
        doesVertexExist(w);

        if (!sapMap.containsKey(Map.entry(v, w))) {
            sap(v, w);
        }
        return sapMap.get(Map.entry(v, w)).getKey(); // Map< Pair<v,w>, Pair<Length, Ancestor>>
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        doesVertexExist(v);
        doesVertexExist(w);

        if (!this.sapMap.containsKey(Map.entry(v, w))) {
            sap(v, w);
        }
        return this.sapMap.get(Map.entry(v, w)).getValue(); // Map< Pair<v,w>, Pair<Length, Ancestor>>
    }


    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> vSet, Iterable<Integer> wSet) {
        doesVertexExist(vSet);
        doesVertexExist(wSet);

        return sap(vSet, wSet)[0];
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> vSet, Iterable<Integer> wSet) {
        doesVertexExist(vSet);
        doesVertexExist(wSet);

        return sap(vSet, wSet)[1];
    }

    private void sap(int v, int w) {
        int sapLength = Integer.MAX_VALUE;
        int sap = -1;
        int[] vDistances = breadthFirstSearch(v);
        int[] wDistances = breadthFirstSearch(w);

        for (int i = 0; i < vDistances.length; i++) {
            if (vDistances[i] != -1 && wDistances[i] != -1) {
                int apLength = vDistances[i] + wDistances[i];
                if (apLength < sapLength) {
                    sapLength = apLength;
                    sap = i;
                }
            }
        }
        // Map< Pair<v,w>, Pair<Length, Ancestor>>
        this.sapMap.put(Map.entry(v, w), Map.entry(sapLength, sap));
    }

    private int[] sap(Iterable<Integer> v, Iterable<Integer> w) {
        int sapLength = Integer.MAX_VALUE;
        int sap = -1;
        int[] vDistances = breadthFirstSearch(v);
        int[] wDistances = breadthFirstSearch(w);

        for (int i = 0; i < vDistances.length; i++) {
            if (vDistances[i] != -1 && wDistances[i] != -1) {
                int apLength = vDistances[i] + wDistances[i];
                if (apLength < sapLength) {
                    sapLength = apLength;
                    sap = i;
                }
            }
        }
        return new int[]{sapLength, sap};
    }

    private int[] breadthFirstSearch(int v) {
        int distance = 0;
        int[] vDistances = new int[this.G.V()];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);

        // Initialize array with -1;
        Arrays.fill(vDistances, -1);

        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int curr = queue.remove();
                vDistances[curr] = distance;
                for (int next : this.G.adj(curr)) {
                    if (vDistances[next] == -1) {
                        queue.add(next);
                    }
                }
            }
            distance++;
        }
        return vDistances;
    }

    private int[] breadthFirstSearch(Iterable<Integer> vSet) {
        int distance = 0;
        int[] vSetDistances = new int[this.G.V()];
        Queue<Integer> queue = new LinkedList<>();
        for (int v : vSet) {
            queue.add(v);
        }

        // Initialize array with -1;
        Arrays.fill(vSetDistances, -1);

        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int curr = queue.remove();
                vSetDistances[curr] = distance;
                for (int next : this.G.adj(curr)) {
                    if (vSetDistances[next] == -1) {
                        queue.add(next);
                    }
                }
            }
            distance++;
        }
        return vSetDistances;
    }

    private void doesVertexExist(int vertex) {
        if (vertex < 0 || vertex >= G.V())
            throw new IllegalArgumentException("Vertex argument is outside its prescribed range");
    }

    private void doesVertexExist(Iterable<Integer> vertexSet) {
        if (vertexSet == null) throw new IllegalArgumentException("Vertex Set is null");
        for (Integer integer : vertexSet) {
            if (integer == null) throw new IllegalArgumentException("Vertex Set contains a null item");
        }
    }


    // do unit testing of this class
    public static void main(String[] args) {

        Digraph G = new Digraph(25);
        G.addEdge(1, 0);
        G.addEdge(2, 0);
        G.addEdge(3, 1);
        G.addEdge(4, 1);
        G.addEdge(5, 2);
        G.addEdge(6, 2);
        G.addEdge(10, 5);
        G.addEdge(11, 5);
        G.addEdge(12, 5);
        G.addEdge(17, 10);
        G.addEdge(18, 10);
        G.addEdge(19, 12);
        G.addEdge(20, 12);
        G.addEdge(23, 20);
        G.addEdge(24, 20);
        G.addEdge(7, 3);
        G.addEdge(8, 3);
        G.addEdge(9, 3);
        G.addEdge(13, 7);
        G.addEdge(14, 7);
        G.addEdge(15, 9);
        G.addEdge(16, 9);
        G.addEdge(21, 16);
        G.addEdge(22, 16);

        SAP sap = new SAP(G);
        List<Integer> v = new ArrayList<>();
        v.add(13);
        v.add(23);
        v.add(24);
        List<Integer> w = new ArrayList<>();
        w.add(6);
        w.add(16);
        w.add(17);

        int commonAncestor = sap.ancestor(v, w);
        int sapLength = sap.length(v, w);
        System.out.println("length: " + sapLength + ", ancestor: " + commonAncestor);
        assert sapLength == 4;
        assert commonAncestor == 3;

    }
}

