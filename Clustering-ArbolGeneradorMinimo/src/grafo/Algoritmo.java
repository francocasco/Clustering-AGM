package grafo;

import java.util.List;

import org.openstreetmap.gui.jmapviewer.Coordinate;


/*
 * En esta clase se implementan los algoritmos de prim y de kruskal
 * Decidimos hacer los dos para comparar los resultados
 */
public class Algoritmo {

	public static GrafoConPeso prim(GrafoConPeso grafo) {
		boolean[] verticesArbol = new boolean[grafo.getCoordenadas().size()];
		int vertice = 0;

		GrafoConPeso grafoAGM = new GrafoConPeso(grafo.getCoordenadas());

		return prim(grafo, verticesArbol, vertice, grafoAGM);
	}

	private static GrafoConPeso prim(GrafoConPeso grafo, boolean[] verticesArbol, int vertice, GrafoConPeso grafoAGM) {
		verticesArbol[vertice] = true;

		double distanciaMenor = 0;

		while (!todosMarcados(verticesArbol)) {

			for (int i = 0; i < verticesArbol.length; i++) {

				if (verticesArbol[i]) {

					for (int j = 0; j < grafo.tamanio(); j++) { // recorremos los vecinos del vertice y asignamos la
																// distancia menor

						if (i != j && grafo.existeArista(i, j)) {

							if (!verticesArbol[j]) {

								if (distanciaMenor == 0) {
									distanciaMenor = grafo.distancia(i, j);

								} else {
									distanciaMenor = Math.min(distanciaMenor, grafo.distancia(i, j));
								}
							}
						}

					}
				}
			}

			for (int i = 0; i < verticesArbol.length; i++) {
				if (verticesArbol[i]) {
					for (int j = 0; j < grafo.tamanio(); j++) { // despues recorremos de vuelta los vecinos y agregamos
																// el vertice no visitado que tenga la menor distancia

						if (i != j && grafo.distancia(i, j) == distanciaMenor) {

							if (!verticesArbol[j]) {
								grafoAGM.agregarArista(i, j);
								vertice = j;
								return prim(grafo, verticesArbol, vertice, grafoAGM);
							}
						}
					}
				}
			}
		}
		return grafoAGM;
	}

	public static boolean todosMarcados(boolean[] vertices) { // Método para saber si todos están marcados
		for (boolean marcado : vertices) {
			if (!marcado) {
				return marcado;
			}
		}
		return true;

	}

	/*
	 * Kruskal: Aplico el algoritmo de Kruskal a una lista de Aristas y las inserto
	 * en un grafo nuevo a retornar
	 */
	public static GrafoConPeso kruskal(GrafoConPeso grafo) {
		int tamanio = grafo.tamanio();
		List<Arista> aristas = grafo.obtenerListaDeAristas();
		List<Arista> a = ConjuntoDisjunto.algoritmoKruskal(aristas, tamanio);

		List<Coordinate> coordenadas = grafo.getCoordenadas();
		GrafoConPeso grafoAGM = new GrafoConPeso(coordenadas);

		for (int i = 0; i < a.size(); i++) {
			grafoAGM.agregarArista(a.get(i).getOrigen(), a.get(i).getDestino());
		}

		return grafoAGM;
	}

}
