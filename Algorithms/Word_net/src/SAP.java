import java.util.*;

public class SAP {
    Digraph G;
    final Ancestors[] ancestors;
    Map<Map.Entry<Integer, Integer>, Map.Entry<Integer, Integer>> sapLengthMap;


    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        this.G = G.reverse();
        sapLengthMap = new HashMap<>();
        this.ancestors = topologicalSort(this.G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (!sapLengthMap.containsKey(Map.entry(v, w))) {
            ancestor(v, w);
        }
        return sapLengthMap.get(Map.entry(v, w)).getValue();
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        Deque<Integer> ancestorsV = new ArrayDeque<>(ancestors[v].ancestors);
        Deque<Integer> ancestorsW = new ArrayDeque<>(ancestors[w].ancestors);
        int commonAncestor = -1;
        // Remove queue until ancestors are different
        while (ancestorsV.peek().equals(ancestorsW.peek())) {
            commonAncestor = ancestorsV.poll();
            ancestorsW.poll();
            if (ancestorsV.isEmpty() || ancestorsW.isEmpty()) {
                break;
            }
        }
        this.sapLengthMap.put(Map.entry(v, w), Map.entry(commonAncestor, commonAncestor == -1 ? -1 : ancestorsV.size() + ancestorsW.size() + 2));
        return commonAncestor;
    }


    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> vSet, Iterable<Integer> wSet) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int v : vSet) {
            for (int w : wSet) {
                if (!sapLengthMap.containsKey(Map.entry(v, w))) {
                    ancestor(v, w);
                }
                treeMap.put(sapLengthMap.get(Map.entry(v, w)).getValue(), sapLengthMap.get(Map.entry(v, w)).getKey());
            }
        }
        return treeMap.firstKey();
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> vSet, Iterable<Integer> wSet) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int v : vSet) {
            for (int w : wSet) {
                if (!sapLengthMap.containsKey(Map.entry(v, w))) {
                    ancestor(v, w);
                }
                treeMap.put(sapLengthMap.get(Map.entry(v, w)).getValue(), sapLengthMap.get(Map.entry(v, w)).getKey());
            }
        }
        return treeMap.firstEntry().getValue();
    }

    private Ancestors[] topologicalSort(Digraph G) {
        int[] inDegrees = new int[G.V()];
        Queue<Integer> queue = new LinkedList<>();
        Ancestors[] ancestors = new Ancestors[G.V()];

        // Get outDegrees[]
        for (int i = 0; i < G.V(); i++) {
            inDegrees[i] = G.indegree(i);
        }

        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
                ancestors[i] = new Ancestors(new ArrayDeque<>());
            }
        }

        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                int curr = queue.poll();
                for (int next : G.adj(curr)) {
                    inDegrees[next]--;
                    if (inDegrees[next] == 0) {
                        queue.add(next);
                        ancestors[next] = new Ancestors(new ArrayDeque<>(ancestors[curr].ancestors));
                        ancestors[next].addAncestor(curr);
                    }
                }
            }
        }
        return ancestors;
    }

    class Ancestors {
        Deque<Integer> ancestors;

        public Ancestors(Deque<Integer> ancestors) {
            this.ancestors = ancestors;
        }

        public void addAncestor(int ancestor) {
            ancestors.add(ancestor);
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

