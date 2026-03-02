package memorygame;

import java.util.Scanner;

/**
 * Entry point of the Memory Game.
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== MEMORY GAME ===");

        int size;

        // Ask for valid board size
        while (true) {
            System.out.print("Enter board size (even >= 2): ");
            try {
                size = sc.nextInt();

                if (size >= 2 && size % 2 == 0) {
                    break;
                }

                System.out.println("Size must be even and >= 2.");

            } catch (Exception e) {
                System.out.println("Invalid number.");
                sc.nextLine(); // clear buffer
            }
        }

        Game game = new Game(size);
        game.start();

        sc.close();
    }
}