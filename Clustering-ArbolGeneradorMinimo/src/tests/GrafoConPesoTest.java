package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import grafo.GrafoConPeso;
import grafo.Haversine;
import leerarchivo.Archivo;


public class GrafoConPesoTest {
	
	GrafoConPeso grafo;
	GrafoConPeso grafoInstanciado;
	
	@Before
	public void init() {
		ArrayList<Coordinate> coordenadas = new ArrayList<Coordinate>();
		coordenadas.add(new Coordinate(10,10));
		coordenadas.add(new Coordinate(5,8));
		coordenadas.add(new Coordinate(20,15));
		grafo = new GrafoConPeso(coordenadas);
		
		Archivo a = new Archivo();
		List<Coordinate> s = a.obtenerCoordenadas("instancias\\instancia1.txt");
		grafoInstanciado = new GrafoConPeso(s);
	}

	@Test
	public void testDistanciaHaversine1() {
		double distancia =grafo.distancia(0, 1);
		assertTrue(distancia == Haversine.formula(10,10 ,5 ,8 )); 
	}
	
	@Test
	public void testDistanciaHaversine2() {
		double distancia =grafo.distancia(0, 2);
		assertTrue(distancia == Haversine.formula(10,10 ,20 ,15 )); 
	}
	
	@Test
	public void testDistanciaHaversine3() {
		double distancia =grafo.distancia(1, 2);
		assertTrue(distancia == Haversine.formula(5,8 ,20 ,15 ));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testErrorVerticeDistancia() {
		grafo.distancia(3, 4);
	}

	@Test
	public void testLatPerteneceAVertice() {
		assertTrue(grafoInstanciado.obtenerLat(0) == -34.52133782929332);
	}
	
	@Test
	public void testLonPerteneceAVertice() {
		assertTrue(grafoInstanciado.obtenerLon(0) == -58.70068073272705);
	}
	
	@Test
	public void testLatPerteneceAVerticeBorde() {
		assertTrue(grafoInstanciado.obtenerLat(67) == -34.5197820361829);
	}
	
	@Test
	public void testLonPerteneceAVerticeBorde() {
		assertTrue(grafoInstanciado.obtenerLon(67) == -58.700809478759766);
	}
	
	@Test
	public void testCantAristas() {
		assertTrue(grafoInstanciado.getCantAristas()==grafoInstanciado.obtenerListaDeAristas().size());
	}
}
