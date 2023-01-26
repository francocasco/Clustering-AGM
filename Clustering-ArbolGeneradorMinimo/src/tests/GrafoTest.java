package tests;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import grafo.Grafo;

public class GrafoTest {

	Grafo grafo;
	
	@Before
	public void init() {
		grafo = new Grafo(5);
		grafo.agregarArista(2, 3);
		grafo.agregarArista(0, 1);
		grafo.agregarArista(2, 1);
		grafo.agregarArista(2, 0);

	}
	
	@Test
	public void existeAristaTest() { 
		assertTrue(grafo.existeArista(2, 3));
 
	}

	@Test
	public void aristaOpuestaTest() {
		assertTrue(grafo.existeArista(3, 2));
	}

	@Test
	public void aristaInexistenteTest() {
		grafo.existeArista(1, 4);

	}
	
	@Test
	public void eliminarAristaTest() {
		grafo.eliminarArista(2, 3);
		assertFalse(grafo.existeArista(2,3));
		
	}
	
	@Test 
	public void vecinosTest() {
		int cantVecinos = grafo.vecinos(2).size();
		assertTrue(3==cantVecinos);
	}
	
	@Test
	public void completarGrafoTest() {
		grafo.completarGrafo();
		int cantVecinos = grafo.vecinos(2).size();
		assertTrue(4==cantVecinos);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void varticeValidoTest() {
		grafo.verificarVertice(6);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void loopTest() {
		grafo.verificarDistintos(0, 0);
	}

}
