package grafo;

import java.util.ArrayList;
import java.util.List;
import org.openstreetmap.gui.jmapviewer.Coordinate;

public class GrafoConPeso extends Grafo {

	/*
	 * El grafo con peso es un grafo donde cada vertice tiene una coordenada
	 * 
	 */

	private List<Coordinate> coordenadas;

	public GrafoConPeso(int tamanio) {
		super(tamanio);
		this.coordenadas = new ArrayList<Coordinate>();
	}

	public GrafoConPeso(List<Coordinate> coordenadas) {
		super(coordenadas.size());
		this.coordenadas = coordenadas;
	}

	/*
	 * Metodo que recibe dos vertices y devuelve la distancia entre ellos
	 * 
	 * Distancia Haversine: Euclides no me daba bien la distancia
	 */
	public double distancia(int priVertice, int segVertice) {
		verificarVertice(priVertice);
		verificarVertice(segVertice);
		verificarDistintos(priVertice, segVertice);
		double distancia;

		double lat1 = obtenerLat(priVertice);
		double lon1 = obtenerLon(priVertice);
		double lat2 = obtenerLat(segVertice);
		double lon2 = obtenerLon(segVertice);
		
		distancia = Haversine.formula(lat1, lon1, lat2, lon2);

		return distancia;
	}


	public double obtenerLat(int a) {
		return coordenadas.get(a).getLat();
	}

	public double obtenerLon(int a) {
		return coordenadas.get(a).getLon();
	}

	public Coordinate getCoordenada(int a) {
		return coordenadas.get(a);
	}

	public List<Coordinate> getCoordenadas() {
		return coordenadas;
	}

	/*
	 * Metodo para borrar todas las aristas del grafo
	 */
	public GrafoConPeso limpiarAristasDelGrafo() {
		GrafoConPeso grafoLimpio = this;
		for (int i = 0; i < tamanio(); i++) {
			for (int j = 0; j < tamanio(); j++) {
				if (i != j && existeArista(i, j)) {
					grafoLimpio.eliminarArista(i, j);
				}
			}
		}
		return grafoLimpio;
	}

	/*
	 * Obtengo una lista de aristas de un grafo
	 */
	public List<Arista> obtenerListaDeAristas(){
		
		ArrayList<Tupla> yaAgregados = new ArrayList<Tupla>();
		
		ArrayList<Arista> aristas = new ArrayList<Arista>();
		for(int i = 0; i<tamanio();i++) {
			for(int j = 0; j<tamanio();j++) {
				if(i!=j && existeArista(i,j)) {
					if(!contains(new Tupla(i,j), yaAgregados)) {
						aristas.add(new Arista(i,j,distancia(i,j)));
						yaAgregados.add(new Tupla(i,j));
					}
				}
			}
		}
		return aristas;
	}
	/*
	 * Metodo para saber si una tupla ya está dentro de una lista de tuplas
	 * Lo uso para saber si ya agregue la arista a la lista de aristas
	 */
	private boolean contains(Tupla t, ArrayList<Tupla> listaTuplas) {
		for (int i = 0; i < listaTuplas.size(); i++) {
			if (t.getX() == listaTuplas.get(i).getX() && t.getY() == listaTuplas.get(i).getY()) {
				return true;
			} else if (t.getX() == listaTuplas.get(i).getY() && t.getY() == listaTuplas.get(i).getX()) {
				return true;
			}

		}
		return false;
	}

	/*
	 * Metodo para ayudarnos a ver los grafos en consola
	 */
	public void mostrarMatrizGrafo() {
		for (int contadorHorizontal = 0; contadorHorizontal < tamanio(); contadorHorizontal++) {
			for (int contadorVertical = 0; contadorVertical < tamanio(); contadorVertical++) {
				System.out.print(A[contadorHorizontal][contadorVertical] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	@Override
	public String toString() {
		return "GrafoConPeso [coordenadas=" + coordenadas + "]\n";
	}

	public void eliminarAristasMayores(int cantAristasEliminar) {
		List<Arista> listaAristas = obtenerListaDeAristas();
		
		
		int contador = 0;
		int indiceAristaAEliminar = 0;
		
		while(cantAristasEliminar != contador) {
			
			Arista aristaMayor =  listaAristas.get(0);
			
			for(int i  = 0; i<listaAristas.size(); i++) {
				
				if(listaAristas.get(i).peso > aristaMayor.peso) {
					aristaMayor = listaAristas.get(i);
					indiceAristaAEliminar = i;
				}
				
			}
			listaAristas.remove(indiceAristaAEliminar);
			eliminarArista(aristaMayor.origen, aristaMayor.destino);
			cantAristasEliminar--;
		}
		
	}

}
