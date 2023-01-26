package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import grafo.Algoritmo;
import grafo.Arista;
import grafo.ConjuntoDisjunto;
import grafo.GrafoConPeso;
import leerarchivo.Archivo;

public class KruskalTest {

	GrafoConPeso grafo;
	GrafoConPeso grafoInstanciado;

	@Before
	public void init() {
		List<Coordinate> coordenadas = new ArrayList<Coordinate>();
		coordenadas.add(new Coordinate(10, 10));
		coordenadas.add(new Coordinate(5, 8));
		coordenadas.add(new Coordinate(20, 15));
		grafo = new GrafoConPeso(coordenadas);

		Archivo arc = new Archivo();
		List<Coordinate> c = arc.obtenerCoordenadas("instancias\\instancia1.txt");
		grafoInstanciado = new GrafoConPeso(c);
	}

	@Test
	public void testCantidadDeAristasCero() {
		grafo = Algoritmo.kruskal(grafo);
		assertTrue(grafo.getCantAristas() == 0);
	}

	@Test
	public void testCantidadDeAristasAlta() {
		grafoInstanciado.completarGrafo();
		grafoInstanciado = Algoritmo.kruskal(grafoInstanciado);
		assertTrue(grafoInstanciado.getCantAristas() == grafoInstanciado.tamanio() - 1);
	}
	
	@Test
	public void testListaAristasVacia() {
		List<Arista> sinAristas = new ArrayList<Arista>();
		sinAristas = ConjuntoDisjunto.algoritmoKruskal(sinAristas, 0);
		assertTrue(sinAristas.size()==0);
	}
	
}
