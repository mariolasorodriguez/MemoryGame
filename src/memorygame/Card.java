package memorygame;

/**
 * Represents a single card in the Memory Game.
 * Each card contains:
 * - A fixed number (pair identifier)
 * - A discovered state (visible or hidden)
 */
public class Card {

    // The value of the card (cannot change after creation)
    private final int number;

    // Indicates whether the card is currently visible
    private boolean discovered;

    /**
     * Creates a new hidden card with the given number.
     */
    public Card(int number) {
        this.number = number;
        this.discovered = false;
    }

    /**
     * Returns the number of the card.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns whether the card is revealed.
     */
    public boolean isDiscovered() {
        return discovered;
    }

    /**
     * Reveals the card.
     */
    public void reveal() {
        discovered = true;
    }

    /**
     * Hides the card.
     */
    public void hide() {
        discovered = false;
    }

    /**
     * String representation of the card.
     * Shows the number if revealed, otherwise shows XX.
     */
    @Override
    public String toString() {
        return discovered ? String.format(" %2d ", number) : " XX ";
    }
}