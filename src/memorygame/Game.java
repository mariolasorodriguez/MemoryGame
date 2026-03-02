package memorygame;

import java.util.Scanner;

/**
 * Controls game flow and user interaction.
 */
public class Game {

    private final Panel panel;
    private final Scanner scanner;
    private int attempts;

    public Game(int size) {
        this.panel = new Panel(size);
        this.scanner = new Scanner(System.in);
        this.attempts = 0;
    }

    public void start() {
        while (!panel.isGameWon()) {

            panel.display();
            System.out.println("\nAttempt #" + (attempts + 1));

            int[] first = getPosition("first");
            panel.getCard(first[0], first[1]).reveal();
            panel.display();

            int[] second = getPosition("second");
            while (first[0] == second[0] && first[1] == second[1]) {
                System.out.println("Choose a different position.");
                second = getPosition("second");
            }

            panel.getCard(second[0], second[1]).reveal();
            panel.display();

            if (panel.checkMatch(first[0], first[1], second[0], second[1])) {
                System.out.println("✓ MATCH!");
            } else {
                System.out.println("✗ No match.");
                pause();
                panel.getCard(first[0], first[1]).hide();
                panel.getCard(second[0], second[1]).hide();
            }

            attempts++;
        }

        System.out.println("\n🎉 You won in " + attempts + " attempts!");
    }

    private int[] getPosition(String order) {
        int row, col;
        int size = panel.getSize();

        while (true) {
            try {
                System.out.print("Enter " + order + " position (row column): ");
                row = scanner.nextInt();
                col = scanner.nextInt();

                if (row >= 0 && row < size && col >= 0 && col < size) {
                    if (!panel.getCard(row, col).isDiscovered()) {
                        return new int[]{row, col};
                    }
                    System.out.println("Card already revealed.");
                } else {
                    System.out.println("Out of bounds.");
                }

            } catch (Exception e) {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }
        }
    }

    private void pause() {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
        scanner.nextLine();
    }
}