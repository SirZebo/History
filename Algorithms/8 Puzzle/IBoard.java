public interface IBoard {
    // string representation of this board
    public String toString();

    // board dimension n
    public int dimension();

    // number of tiles out of place
    public int hamming();

    // sum of Manhattan distances between tiles and goal
    public int manhattan();

    // is this board the goal board?
    public boolean isGoal();

    // does this board equal y?
    public boolean equals(Object y);

    // all neighboring boards
    public Iterable<Board> neighbors();

    // a board that is obtained by exchanging any pair of tiles
    public Board twin();
}
