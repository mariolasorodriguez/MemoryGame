package memorygame;

public class Carta {

	private int valor;
	
	private boolean descubierta;

	public Carta(int valor) {

		this.valor = valor;

		this.descubierta = false;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public boolean isDescubierta() {
		return descubierta;
	}

	public void setDescubierta(boolean descubierta) {
		this.descubierta = descubierta;
	}

	public String toString() {
		if (descubierta) {
			return " " + valor + " ";
		} else {
			return " X ";
		}
	}
}
