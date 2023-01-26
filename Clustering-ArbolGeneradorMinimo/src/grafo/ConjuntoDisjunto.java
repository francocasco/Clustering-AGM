package grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConjuntoDisjunto {

	Map<Integer, Integer> padre = new HashMap<>();

	/*
	 * Toma un numero que vendria a ser la cantidad de vertices
	 * Genera n conjuntos(map) la clave es la misma que el valor inicialmente
	 */
	public void generarConjunto(int n) {
		for (int i = 0; i < n; i++) {
			padre.put(i, i);
		}
	}
	
	/*
	 * Find y union encuentras ciclos, si lo encuentra no se puede agregar esa arista
	 */

	/*
	 * Se recurre hasta que encuentra la raiz del numero ingresado
	 * si encuentra a el padre de el vertice analizado devuelve ese valor
	 */
	private int find(int k) {
		if (padre.get(k) == k) {
			return k;
		}

		
		return find(padre.get(k));
	}

	/*
	 * Une dos conjuntos disconjuntos
	 * Agrega la arista ingresada al hashmap
	 */
	private void union(int a, int b) {
		
		int x = find(a);
		int y = find(b);

		padre.put(x, y);
	}
	

	/*
	 * Metodo para construir AGM usando el algoritmo de Kruskal
	 * Recibe como parametro una lista de aristas y la cantidad de vertices del grafo
	 */
	public static List<Arista> algoritmoKruskal(List<Arista> aristas, int n) {
		// almacena las aristas presentes en AGM
		List<Arista> AGM = new ArrayList<>();

		ConjuntoDisjunto ds = new ConjuntoDisjunto();
		ds.generarConjunto(n);

		int index = 0;

		//Aca ordeno todas las aristas con peso decreciente
		Collections.sort(aristas, new OrdenAristas());

		/*
		 * Recorro todas las aristas hasta obtener N-1 aristas
		 */
		while (AGM.size() != n - 1 && !aristas.isEmpty()) {
		
			Arista sigArista = aristas.get(index++);

			int x = ds.find(sigArista.getOrigen());
			int y = ds.find(sigArista.getDestino());

			/*
			 * una vez que recurri hasta la raiz de cada vertice y
			 * descubri que no son ciclos, los agrego al AGM
			 */
			if (x != y) {
				AGM.add(sigArista);
				ds.union(x, y);
			}
		}

		return AGM;
	}
}
