package memorygame;

import java.util.Scanner;

/**
 * Main class for the Memory Game.
 * Handles game flow and user interaction.
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== MEMORY GAME ===");
        System.out.println("Find all matching pairs to win!\n");

        // Get board size from user
        int boardSize = getBoardSize();
        
        // Create the game panel
        Panel panel = new Panel(boardSize);
        
        // Start the game
        playGame(panel);
        
        scanner.close();
    }

    /**
     * Gets a valid board size from the user.
     * Size must be even and at least 2x2.
     * 
     * @return The board size
     */
    private static int getBoardSize() {
        int size;
        while (true) {
            System.out.print("Enter board size (even number, e.g., 4 for 4x4): ");
            try {
                size = scanner.nextInt();
                if (size >= 2 && size % 2 == 0) {
                    return size;
                } else {
                    System.out.println("Size must be even and at least 2!");
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid number!");
                scanner.nextLine(); // Clear buffer
            }
        }
    }

    /**
     * Main game loop.
     * Player selects two positions each turn until all pairs are found.
     * 
     * @param panel The game board
     */
    private static void playGame(Panel panel) {
        int attempts = 0;

        while (!panel.isGameWon()) {
            // Display the current board
            panel.displayPanel();

            // Get first position
            System.out.println("\nAttempt #" + (attempts + 1));
            int[] pos1 = getPosition(panel, "first");
            Carta card1 = panel.getCard(pos1[0], pos1[1]);

            // Temporarily reveal first card
            card1.setDiscovered(true);
            panel.displayPanel();

            // Get second position (different from first)
            int[] pos2 = getPosition(panel, "second");
            while (pos1[0] == pos2[0] && pos1[1] == pos2[1]) {
                System.out.println("Please choose a different position!");
                pos2 = getPosition(panel, "second");
            }
            Carta card2 = panel.getCard(pos2[0], pos2[1]);

            // Reveal second card
            card2.setDiscovered(true);
            panel.displayPanel();

            // Check if cards match
            if (card1.getNumber() == card2.getNumber()) {
                System.out.println("✓ MATCH! Cards stay revealed.");
            } else {
                System.out.println("✗ No match. Cards will be hidden again.");
                card1.setDiscovered(false);
                card2.setDiscovered(false);
            }

            attempts++;
            
            // Pause before next turn
            if (!panel.isGameWon()) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
                scanner.nextLine();
            }
        }

        // Game won!
        panel.displayPanel();
        System.out.println("\n🎉 CONGRATULATIONS! You won in " + attempts + " attempts!");
    }

    /**
     * Gets a valid position from the user.
     * 
     * @param panel The game board
     * @param order "first" or "second" position
     * @return Array with [row, column]
     */
    private static int[] getPosition(Panel panel, String order) {
        int row, column;
        int size = panel.getSize();

        while (true) {
            try {
                System.out.print("Enter " + order + " position (row column, 0-" + (size - 1) + "): ");
                row = scanner.nextInt();
                column = scanner.nextInt();

                // Validate position
                if (row >= 0 && row < size && column >= 0 && column < size) {
                    Carta card = panel.getCard(row, column);
                    
                    // Check if card is already discovered
                    if (card.isDiscovered()) {
                        System.out.println("This card is already discovered! Choose another.");
                    } else {
                        return new int[]{row, column};
                    }
                } else {
                    System.out.println("Position out of bounds! Use 0-" + (size - 1));
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Enter two numbers.");
                scanner.nextLine(); // Clear buffer
            }
        }
    }
}