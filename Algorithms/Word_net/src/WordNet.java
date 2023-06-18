import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordNet {
    /*
     * The WordNet digraph is a rooted DAG : it is acyclic and has one vertex—the root—that is an ancestor of every other vertex.
     * However, it is not necessarily a tree because a synset can have more than one hypernym.
     * A small subgraph of the WordNet digraph appears below.
     */
    // constructor takes the name of the two input files

    Map<Integer, String> idNounMap;
    Map<String, List<Integer>> nounMap;
    Digraph digraph;
    SAP sap;
    int count;

    public WordNet(String synsets, String hypernyms) {
        this.count = 0;
        readSynsets(synsets);
        this.digraph = new Digraph(count);
        readHypernyms(hypernyms);

        if (hasCycle(digraph)) throw new IllegalArgumentException("Digraph has cycle");

        int roots = 0;
        for (int i = 0; i < count; i++) {
            if (digraph.outdegree(i) == 0) {
                roots++;
            }
            if (roots > 1) throw new IllegalArgumentException("Digraph contains more than 1 root");
        }

        sap = new SAP(digraph);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nounMap.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null) throw new IllegalArgumentException("Method isNoun(): String word cannot be null!");
        return nounMap.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB))
            throw new IllegalArgumentException("Method distance(): String nounA / String nounB cannot be null!");

        return sap.length(nounMap.get(nounA), nounMap.get(nounB));
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB))
            throw new IllegalArgumentException("Method sap(): String nounA / String nounB cannot be null!");
        int idAncestorNoun = sap.ancestor(nounMap.get(nounA), nounMap.get(nounB));
        return idNounMap.get(idAncestorNoun);
    }

    private void readSynsets(String synsets) {
        if (synsets == null) throw new IllegalArgumentException("Method readSynsets(): String synsets cannot be null!");
        In in = new In(synsets);
        this.idNounMap = new HashMap<>();
        this.nounMap = new HashMap<>();
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] fields = line.split(","); // fields[0] = synset_id, fields[1] = synet, fields[2] = gloss -> not relevant;
            int id = Integer.parseInt(fields[0]);
            this.idNounMap.put(id, fields[1]);

            //noun may appear in more than 1 synsets. Eg: A = A alphabet, A blood type
            String[] nouns = fields[1].split(" ");
            for (String noun : nouns) {
                if (nounMap.containsKey(noun)) {
                    this.nounMap.get(noun).add(id);
                } else {
                    List<Integer> ids = new ArrayList<>();
                    ids.add(id);
                    this.nounMap.put(noun, ids);
                }
            }
            this.count++;
        }
    }

    private void readHypernyms(String hypernyms) {
        if (hypernyms == null) throw new IllegalArgumentException("readHypernyms: String hypernyms cannot be null!");
        In in = new In(hypernyms);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] fields = line.split(",");
            int nounID = Integer.parseInt(fields[0]);
            for (int i = 1; i < fields.length; i++) {
                int hypernymID = Integer.parseInt(fields[i]);
                this.digraph.addEdge(nounID, hypernymID);
            }
        }
    }

    private boolean hasCycle(Digraph digraph) {
        boolean[] isVisited = new boolean[digraph.V()];
        boolean[] isVisiting = new boolean[digraph.V()];
        for (int i = 0; i < digraph.V(); i++) {
            if (!isVisited[i] && hasCycle(i, isVisited, isVisiting)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasCycle(int i, boolean[] isVisited, boolean[] isVisiting) {
        if (isVisited[i]) return false;
        if (isVisiting[i]) return true;
        isVisiting[i] = true;
        for (int adjVertices : digraph.adj(i)) {
            if (hasCycle(adjVertices, isVisited, isVisiting)) {
                return true;
            }
        }
        isVisited[i] = true;
        return false;
    }


    // do unit testing of this class
    public static void main(String[] args) {
        String synsets = "https://coursera.cs.princeton.edu/algs4/assignments/wordnet/files/synsets.txt";
        String hypernyms = "https://coursera.cs.princeton.edu/algs4/assignments/wordnet/files/hypernyms.txt";
        WordNet wordnet = new WordNet(synsets, hypernyms);

    }
}
