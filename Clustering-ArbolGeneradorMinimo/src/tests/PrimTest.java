package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import grafo.Algoritmo;
import grafo.GrafoConPeso;

public class PrimTest {

	
	GrafoConPeso grafo ;
	GrafoConPeso grafoAGM ;
	
	@Before
	public void init() {
		ArrayList<Coordinate> coordenadas = new ArrayList<>();
		coordenadas.add(new Coordinate(15.5, 20.8));
		coordenadas.add(new Coordinate(50.5, 30.8));
		coordenadas.add(new Coordinate(70.5, 10.8));
		coordenadas.add(new Coordinate(100.5, 20.8));
		coordenadas.add(new Coordinate(80.5, 5.8));
		grafo = new GrafoConPeso(coordenadas);
		
		grafo.completarGrafo();
		
		grafoAGM = Algoritmo.prim(grafo);
	}
	
	@Test
	public void sinCircuitosTest() {
		ArrayList<Coordinate> coordenadas = new ArrayList<>();
		coordenadas.add(new Coordinate(15.5, 20.8));
		coordenadas.add(new Coordinate(50.5, 30.8));
		coordenadas.add(new Coordinate(70.5, 10.8));
		GrafoConPeso grafo2 = new GrafoConPeso(coordenadas);
		grafo2.completarGrafo();
		
		assertTrue(grafo2.vecinos(0).size()==2);
		assertTrue(grafo2.vecinos(1).size()==2);
		assertTrue(grafo2.vecinos(2).size()==2);
		
		GrafoConPeso grafoAGM2 = Algoritmo.prim(grafo2);
		
		
		assertTrue(grafoAGM2.vecinos(0).size()==1);
		assertTrue(grafoAGM2.vecinos(1).size()==2);
		assertTrue(grafoAGM2.vecinos(2).size()==1);
		 
	}
	
	
	@Test
	public void cantAristasTest() {
		int cant = grafo.tamanio()-1;
		
		assertTrue(cant==grafoAGM.getCantAristas());
		
	}
	
	@Test
	public void tamañoTest() {
		
		assertTrue(grafo.tamanio() == grafoAGM.tamanio());
		
	}

}  
