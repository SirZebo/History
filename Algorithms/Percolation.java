import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        int n = 20;
        Percolation nodeState = new Percolation(n); // Create a n by n array
        while (!nodeState.percolates()) { //  The program keeps opening new nodes until the top touches the bottom
            nodeState.open(Random(n - 1), Random(n - 1)); //  opens random nodes 
            System.out.println("Open " + nodeState.numberOfOpenSites());
        }
        float openSites = nodeState.numberOfOpenSites();
        float total = n * n;
        float p = openSites / total;
        System.out.printf("%.2f", p);
    }

    public static int Random(int n) {
        return ThreadLocalRandom.current().nextInt(0, n + 1);
    }
}

class Percolation {

    private boolean[][] nodeState;
    private int openSites = 0;
    private int n;
    private WeightedQU uf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        this.n = n;
        nodeState = new boolean[n][n]; // all nodes blocked
        uf = new WeightedQU(n * n + 2);
        for (int j = 0; j < n; j++) {
            uf.union(0, coordToNodeNum(0, j));
            uf.union((n * n) + 1, coordToNodeNum(n - 1, j)); //n-1, that minus N is j
        }

    }

    public int coordToNodeNum(int i, int j) //grid > QU's tree nodes
    {
        return (n * i) + j + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (!isOpen(row, col)) {
            nodeState[row][col] = true;
            openSites++;
        }
        if (row < (n - 1)) {
            if (isOpen(row + 1, col) == true) {
                uf.union(coordToNodeNum(row + 1, col), coordToNodeNum(row, col));
            }
        }
        if (col < (n - 1)) {
            if (nodeState[row][col + 1] == true) {
                uf.union(coordToNodeNum(row, col + 1), coordToNodeNum(row, col));
            }
        }
        if (row > 0) {
            if (nodeState[row - 1][col] == true) {
                uf.union(coordToNodeNum(row - 1, col), coordToNodeNum(row, col));
            }
        }
        if (col > 0) {
            if (nodeState[row][col - 1] == true) {
                uf.union(coordToNodeNum(row, col - 1), coordToNodeNum(row, col));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return nodeState[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isOpen(row, col)) {
            return false;
        } //check if open, duh
        return (uf.connected(0, coordToNodeNum(row, col))); //Check if node == source which is 0
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return (uf.connected(0, (n * n) + 1)); //  source == sink
    }

    // test client (optional)
    //public static void main(String[] args)
}

class WeightedQU //lg* N (only works when group is more than double). So at worst case scenario (each group is weighted equal), only works at 1,2,4,16,65536, 2^65536
{
    private int[] id;
    private int[] sz;

    public WeightedQU(int N) // Creates an array for id, input id size
    {
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < id.length; i++) {
            sz[i] = 1;
            id[i] = i;
        }
    }

    public int Root(int i) {
        while (i != id[i]) // While not at root (not pointing to itself).
        {
            id[i] = id[id[i]];   // this line represents "path compression". See whats inside(contains reference to the parent and then see inside of parent) and move to the parent.
            i = id[i];  // See whats inside (contains reference to the parent) and move to the parent
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return Root(p) == Root(q);
    }

    public void union(int p, int q)   // here sz[] is used to "weighting"
    {
        int i = Root(p);
        int j = Root(q);
        if (sz[i] < sz[j]) {
            id[i] = j; //  Change the root container of p to the root of q
            sz[j] += sz[i]; //  add the count of the smaller array to the count of the larger array
        } else // if j is smaller than i
        {
            id[j] = i;
            sz[i] += sz[j];
        }
    }
}
