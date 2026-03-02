package memorygame;

/**
 * Represents a single card in the Memory Game.
 * Each card has a number and a discovered state.
 */
public class Carts {

    private final int number;
    private boolean discovered;

    public Carts(int number) {
        this.number = number;
        this.discovered = false;
    }

    public int getNumber() {
        return number;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public void reveal() {
        discovered = true;
    }

    public void hide() {
        discovered = false;
    }

    @Override
    public String toString() {
        return discovered ? String.format(" %2d ", number) : " XX ";
    }
}