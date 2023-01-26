package grafo;

import java.util.ArrayList;
import java.util.Arrays;

public class Grafo {
	
	private int cantAristas; 
	
	// Representamos el grafo por su matriz de adyacencia
	protected boolean[][] A;

	// La cantidad de vertices esta predeterminada desde el constructor
	public Grafo(int vertices) {
		A = new boolean[vertices][vertices]; 
		this.cantAristas=0;
	}

	// Agregado de aristas
	public void agregarArista(int i,int j) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		A[i][j] = true;
		A[j][i] = true;
		cantAristas++;
	}

	// Eliminacion de aristas
	public void eliminarArista(int i, int j) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		A[i][j] = false;
		A[j][i] = false;
		cantAristas--;
	}

	// Informa si existe la arista especificada
	public boolean existeArista(int i, int j) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);

		return A[i][j];
	}

	// Cantidad de vertices
	public int tamanio() {
		return A.length;
	}

	// Vecinos de un vertice
	public ArrayList<Integer> vecinos(int i) {
		verificarVertice(i);

		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (int j = 0; j < this.tamanio(); ++j)
			if (i != j) {
				if (this.existeArista(i, j))
					ret.add(j);
			}

		return ret;
	}
	
	public void completarGrafo() {
		for (int i = 0; i < tamanio(); i++) {
			for (int j = 0; j < tamanio(); j++) {
				if (i != j)
					agregarArista(i, j);
			}
		}
	}

	// Verifica que sea un vertice valido
	public void verificarVertice(int i) {
		if (i < 0)
			throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);

		if (i >= A.length)
			throw new IllegalArgumentException("Los vertices deben estar entre 0 y |V|-1: " + i);
	}

	// Verifica que i y j sean distintos
	public void verificarDistintos(int i, int j) {
		if (i == j)
			throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
	}

	public int getCantAristas() {
		return cantAristas;
	}
	
	@Override
	public String toString() {
		return "Grafo [A=" + Arrays.toString(A) + "]";
	}

	

	

}
