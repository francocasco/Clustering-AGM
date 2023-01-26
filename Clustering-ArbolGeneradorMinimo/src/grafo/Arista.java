package grafo;

public class Arista{

	int origen;
	int destino;
	double peso;
	
	public Arista(int origen, int destino, double peso) {
		this.origen=origen;
		this.destino=destino;
		this.peso=peso;
	}
	
	 public int getOrigen() {
		return origen;
	}

	public int getDestino() {
		return destino;
	}

	public double getPeso() {
		return peso;
	}

	@Override
	public String toString() {
		return "(" + origen + ", " + destino + ", " + peso + ")\n";
	}


}
