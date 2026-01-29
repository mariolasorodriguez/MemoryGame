package memorygame;

import java.util.Random;

/**
 * Represents the game board with NxN cards.
 * Manages card placement and board state.
 */
public class Panel {
    private int n;
    private Carta[][] panel;

    /**
     * Creates a new panel with the specified size.
     * Size must be even to allow pairs of cards.
     * 
     * @param n The size of the board (NxN)
     */
    public Panel(int n) {
        if (n % 2 != 0) {
            throw new IllegalArgumentException("Panel size must be even!");
        }
        this.n = n;
        this.panel = new Carta[n][n];
        initializePanel();
    }

    /**
     * Initializes the panel by placing pairs of cards randomly.
     * Each number appears exactly twice on the board.
     */
    private void initializePanel() {
        Random rand = new Random();
        int totalPairs = (n * n) / 2;

        // Place pairs of cards randomly on the board
        for (int num = 0; num < totalPairs; num++) {
            for (int rep = 0; rep < 2; rep++) {
                int row, column;
                
                // Find an empty position
                do {
                    row = rand.nextInt(n);
                    column = rand.nextInt(n);
                } while (panel[row][column] != null);
                
                // Place the card
                panel[row][column] = new Carta(num);
            }
        }
    }

    /**
     * Gets the card at the specified position.
     * 
     * @param row The row index
     * @param column The column index
     * @return The card at that position
     */
    public Carta getCard(int row, int column) {
        if (!isValidPosition(row, column)) {
            throw new IndexOutOfBoundsException("Invalid position!");
        }
        return panel[row][column];
    }

    /**
     * Checks if the given position is valid.
     */
    private boolean isValidPosition(int row, int column) {
        return row >= 0 && row < n && column >= 0 && column < n;
    }

    /**
     * Checks if all cards have been discovered.
     * 
     * @return true if the game is won, false otherwise
     */
    public boolean isGameWon() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!panel[i][j].isDiscovered()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Displays the current state of the board.
     */
    public void displayPanel() {
        System.out.println("\n" + "=".repeat(n * 4 + 1));
        for (int i = 0; i < n; i++) {
            System.out.print("|");
            for (int j = 0; j < n; j++) {
                System.out.print(panel[i][j] + "|");
            }
            System.out.println();
            System.out.println("=".repeat(n * 4 + 1));
        }
    }

    public int getSize() {
        return n;
    }
}