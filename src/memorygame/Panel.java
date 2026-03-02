package memorygame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the game board.
 * Responsible for:
 * - Creating and initializing cards
 * - Managing board state
 * - Checking matches
 */
public class Panel {

    private final int size;
    private final Card[][] board;

    /**
     * Creates a new board of size NxN.
     * Size must be even and >= 2.
     *
     * @param size Board dimension
     */
    public Panel(int size) {
        if (size < 2 || size % 2 != 0) {
            throw new IllegalArgumentException("Size must be even and >= 2");
        }

        this.size = size;
        this.board = new Card[size][size];
        initializeBoard();
    }

    /**
     * Initializes the board:
     * 1. Creates pairs of numbers
     * 2. Shuffles them
     * 3. Fills the matrix
     */
    private void initializeBoard() {
        int totalPairs = (size * size) / 2;

        List<Integer> numbers = new ArrayList<>();

        // Create pairs
        for (int i = 1; i <= totalPairs; i++) {
            numbers.add(i);
            numbers.add(i);
        }

        // Shuffle numbers randomly
        Collections.shuffle(numbers);

        // Fill the matrix
        int index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Card(numbers.get(index++));
            }
        }
    }

    /**
     * Returns the card at the given position.
     */
    public Card getCard(int row, int col) {
        validatePosition(row, col);
        return board[row][col];
    }

    /**
     * Checks if two positions contain matching numbers.
     * Does NOT reveal cards (only compares).
     */
    public boolean checkMatch(int r1, int c1, int r2, int c2) {
        return getCard(r1, c1).getNumber() ==
               getCard(r2, c2).getNumber();
    }

    /**
     * Returns true if all cards have been revealed.
     */
    public boolean isGameWon() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!board[i][j].isDiscovered()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Displays the board with row and column indices.
     */
    public void display() {
        System.out.print("    ");
        for (int i = 0; i < size; i++) {
            System.out.printf("%4d", i);
        }
        System.out.println();

        System.out.println("   " + "-".repeat(size * 4));

        for (int i = 0; i < size; i++) {
            System.out.printf("%2d |", i);
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Returns board size.
     */
    public int getSize() {
        return size;
    }

    /**
     * Validates that a position is inside the board.
     */
    private void validatePosition(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
    }
}