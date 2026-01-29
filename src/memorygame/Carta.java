package memorygame;

/**
 * Represents a card in the memory game.
 * Each card has a number and can be discovered or hidden.
 */
public class Carta {
    private int number;
    private boolean discovered;

    /**
     * Creates a new card with the given number.
     * Cards start as hidden (not discovered).
     * 
     * @param number The number on this card
     */
    public Carta(int number) {
        this.number = number;
        this.discovered = false;
    }

    public int getNumber() {
        return number;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered(boolean discovered) {
        this.discovered = discovered;
    }

    /**
     * Returns a string representation of the card.
     * Shows the number if discovered, otherwise shows "X"
     */
    @Override
    public String toString() {
        if (discovered) {
            return " " + number + " ";
        } else {
            return " X ";
        }
    }
}