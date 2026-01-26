package memorygame;

public class Carta {

	private int number;
	
	private boolean discovered;

	public Carta(int number) {

		this.number = number;

		this.discovered = false;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isDiscovered() {
		return discovered;
	}

	public void setDiscovered(boolean discovered) {
		this.discovered = discovered;
	}

	public String toString() {
		if (discovered) {
			return " " + number + " ";
		} else {
			return " X ";
		}
	}
}
