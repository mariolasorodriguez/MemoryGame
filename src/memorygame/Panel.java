package memorygame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the game board.
 */
public class Panel {

    private final int size;
    private final Carts[][] board;

    public Panel(int size) {
        if (size < 2 || size % 2 != 0) {
            throw new IllegalArgumentException("Size must be even and >= 2");
        }

        this.size = size;
        this.board = new Carts[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        int totalPairs = (size * size) / 2;

        List<Integer> numbers = new ArrayList<>();

        for (int i = 1; i <= totalPairs; i++) {
            numbers.add(i);
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        int index = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Carts(numbers.get(index++));
            }
        }
    }

    public Carts getCard(int row, int col) {
        validatePosition(row, col);
        return board[row][col];
    }

    public boolean checkMatch(int r1, int c1, int r2, int c2) {
        Carts c1Card = getCard(r1, c1);
        Carts c2Card = getCard(r2, c2);

        if (c1Card.getNumber() == c2Card.getNumber()) {
            c1Card.reveal();
            c2Card.reveal();
            return true;
        }

        return false;
    }

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

    public int getSize() {
        return size;
    }

    private void validatePosition(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
    }
}