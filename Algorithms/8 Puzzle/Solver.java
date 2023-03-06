import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solver {
    private SearchNode lastNode;
    private Board goal = new Board(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 0});
    // find a solution to the initial board (using the A* algorithm)
    private int minMoves;

    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();

        //First, insert the initial search node (the initial board, 0 moves, and a null previous search node) into a priority queue.
        int moves = 0;
        SearchNode searchNode = new SearchNode(initial, moves, null);
        MinimumHeap<SearchNode> searchNodes = new MinimumHeap<>();


        searchNodes.insert(searchNode);
        Queue<Board> neighbors = new LinkedList<Board>();

        boolean solved = false;
        boolean twinSolved = false;
        while (!solved && !twinSolved) {
            // Then, delete from the priority queue the search node with the minimum priority
            SearchNode current = searchNodes.getRoot();
            Board temp = current.getBoard();
            int move = current.getMoves() + 1;


            //System.out.println("Moves: " + current.getMoves() + " ,Manhattan: " + temp.manhattan());

            // When current board == goal board. It is solved!
            if (temp.equals(this.goal)) {
                this.minMoves = current.getMoves();
                lastNode = current; // Something like singly linked list
                break;
            }

            //insert onto the priority queue all neighboring search nodes
            for (Board neighbor : temp.neighbors()) {
                if (current.getParent() == null || !(neighbor).equals(current.getParent().getBoard())) {
                    neighbors.add(neighbor);
                    searchNodes.insert(new SearchNode(neighbor, move, current));
                }
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return true;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return minMoves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        Stack<Board> boards = new Stack<>();
        SearchNode lastNode = this.lastNode;
        while (lastNode.getParent() != null) {
            boards.push(lastNode.getBoard());
            //System.out.println("Moves: " + lastNode.getMoves());
            //System.out.println(lastNode.getBoard());
            lastNode = lastNode.getParent();
        }
        return boards;

    }

    // test client (see below)
    public static void main(String[] args) {
        int[][] test = new int[3][3];
        int[] numbers = {8, 0, 3, 4, 1, 2, 7, 6, 5};
        ;
        int idx = 0;

        test = Board.boardFromArray(numbers, 3);

        Board board = new Board(test);
        System.out.println(board);
        Solver solvedBoard = new Solver(board);

        System.out.println("Minimum moves: " + solvedBoard.moves());
        Iterable<Board> boards = solvedBoard.solution();
        for (Board ans : boards) {
            System.out.println(ans);
        }
    }

    // Define a search node of the game to be a board, the number of moves made to reach the board, and the previous search node.
    private class SearchNode implements Comparable<SearchNode> {

        private int priority;
        private SearchNode parent;
        private Board current;
        private int moves;

        public SearchNode(Board current, int moves, SearchNode parent) {
            this.parent = parent;
            this.moves = moves;
            this.current = current;
            this.priority = moves + current.manhattan();
        }

        @Override
        public int compareTo(SearchNode that) {
            return this.priority - that.priority;
        }

        public Board getBoard() {
            return current;
        }

        public SearchNode getParent() {
            return parent;
        }

        public int getPriority() {
            return priority;
        }

        public int getMoves() {
            return moves;
        }
    }
}
