import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

import static java.lang.Math.abs;
import static java.lang.Math.ceil;

public class Board implements IBoard {
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    private int[] board;
    private int width;

    public Board(int[][] tiles) {

        int width = tiles[0].length;
        int[] board = new int[width * width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                board[i * 3 + j] = tiles[i][j];
            }
        }
        this.board = board;
        this.width = width;
    }

    public Board(int[] tiles) {
        this.width = (int) Math.sqrt(tiles.length);
        this.board = tiles;
    }

    @Override
    public String toString() {
        String strings = width + "\n ";
        for (int i = 0; i < board.length; i++) {
            if (i % 3 == 0) strings += "" + "\n ";
            strings += board[i] + " ";
        }
        return strings;
    }

    public int dimension() {
        return width;
    }

    public int hamming() {
        int hammingNum = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) {
                continue;
            }
            if (board[i] != i + 1) hammingNum++;
        }
        return hammingNum;
    }

    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < width * width; i++) {
            if (board[i] != 0) {
                manhattan += abs(row(board[i]) - row(i + 1)) + abs(column(board[i]) - column(i + 1));
                /*
                if (board[i] % width == 0) {
                    manhattan += abs(((board[i] - 1) / width) - i / width) + abs(width - (i + 1) % width);
                } else {
                    manhattan += abs(((board[i] - 1) / width) - i / width) + abs(board[i] % width - (i + 1) % width); // Up/down + left/right = (Actual row - current row) + (Actual column - current column)

                }

                 */
            }
            System.out.println(manhattan);
        }
        return manhattan;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public boolean equals(Board that) {
        if (that == null) return false;
        return Arrays.equals(this.board, that.board);
    }

    public Iterable<Board> neighbors() {
        Stack<Board> neighbors = new Stack<>();
        int[] neighbor;

        int emptyTileIndex = emptyTileIndex(board);
        boolean[] usableSwap = swapLegality(emptyTileIndex);
        int tileSelect;

        if (usableSwap[0]) {
            tileSelect = emptyTileIndex - 3; // Up 1 row
            neighbor = board.clone();
            swap(neighbor, emptyTileIndex, tileSelect);
            neighbors.push(new Board(neighbor));
        }
        if (usableSwap[1]) {
            tileSelect = emptyTileIndex + 3; // Down 1 row
            neighbor = board.clone();
            swap(neighbor, emptyTileIndex, tileSelect);
            neighbors.push(new Board(neighbor));
        }
        if (usableSwap[2]) {
            tileSelect = emptyTileIndex - 1; // Left 1 column
            neighbor = board.clone();
            swap(neighbor, emptyTileIndex, tileSelect);
            neighbors.push(new Board(neighbor));
        }
        if (usableSwap[3]) {
            tileSelect = emptyTileIndex + 1; // Right 1 column
            neighbor = board.clone();
            swap(neighbor, emptyTileIndex, tileSelect);
            neighbors.push(new Board(neighbor));
        }

        return neighbors;
    }

    public Board twin() { // Make the board unsolvable by swapping 2 tiles, empty tile 0 is not a tile
        int[] twinTiles = null;
        int randomSelect1;
        Random rand = new Random();

        do randomSelect1 = rand.nextInt(9);
        while (board[randomSelect1] == 0);

        boolean[] usableSwap = swapLegality(randomSelect1);
        while (twinTiles == null) {
            int randomSelect2 = rand.nextInt(4);
            if (usableSwap[randomSelect2]) {
                if (randomSelect2 == 0) randomSelect2 = randomSelect1 - 3; // Up 1 row
                else if (randomSelect2 == 1) randomSelect2 = randomSelect1 + 3; // Down 1 row
                else if (randomSelect2 == 2) randomSelect2 = randomSelect1 - 1; // Left 1 column
                else if (randomSelect2 == 3) randomSelect2 = randomSelect1 + 1; // Right 1 column
                else throw new ArrayIndexOutOfBoundsException("randomSelect is out of bound");
                if (board[randomSelect2] == 0) continue; // Re-roll if select 0
                twinTiles = board.clone();
                swap(twinTiles, randomSelect1, randomSelect2);
            }
        }
        return new Board(twinTiles);
    }

    private void swap(int[] board, int firstIndex, int secondIndex) {
        int temp = board[firstIndex];
        board[firstIndex] = board[secondIndex];
        board[secondIndex] = temp;
    }

    private int emptyTileIndex(int[] board) {
        int index = 0;
        while (board[index] != 0) {
            index++;
        }
        return index;
    }

    private boolean[] swapLegality(int tileIndex) {
        boolean[] usableSwap = {true, true, true, true};
        if ((tileIndex) / width == 0) usableSwap[0] = false; // No top
        if ((tileIndex) / width == width - 1) usableSwap[1] = false; // No bottom
        if ((tileIndex) % width == 0) usableSwap[2] = false; // No Left
        if ((tileIndex + 1) % width == 0) usableSwap[3] = false; // No Right
        return usableSwap;
    }

    private int row(int tile) {
        return (int) ceil((double) tile / (double) width);
    }

    private int column(int tile) {
        if (tile % width == 0) return width;
        else return tile % width;
    }

    private static int[][] boardFromArray(int[] arr, int width) {
        int[][] result = new int[width][width];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                result[i][j] = arr[i * 3 + j];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        int[][] test = new int[3][3];
        int[][] goalBoard = new int[3][3];
        int[] numbers = {8, 1, 3, 4, 0, 2, 7, 6, 5};
        int[] goal = {1, 2, 3, 4, 5, 6, 7, 8, 0};
        int idx = 0;

        test = boardFromArray(numbers, 3);
        goalBoard = boardFromArray(goal, 3);

        Board board = new Board(test);
        System.out.println(board);

        System.out.println("Is it goal board? " + board.isGoal());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Hamming: " + board.hamming());
        System.out.println();

        int n = 1;
        Iterable<Board> neighbors = board.neighbors();
        for (Board b : neighbors) {
            System.out.println("Neighbor " + n++);
            System.out.println(b);
        }

        Board twin;
        twin = board.twin();
        System.out.println("Twin");
        System.out.println(twin);

        Board boardClone = new Board(test);
        System.out.println("Is board equal to twin? " + board.equals(twin));
        System.out.println("Is board equal to board? " + board.equals(board));
        System.out.println("Is board equal to null? " + board.equals(null));
        System.out.println("Is board equal to boardClone? " + board.equals(boardClone));
    }
}
