package leerarchivo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Archivo {

	public List<Coordinate> obtenerCoordenadas(String ruta) {
		ArrayList<Coordinate> coordenadas = new ArrayList<Coordinate>();

		try {
			BufferedReader bf = new BufferedReader(new FileReader(ruta));

			String bfRead;
			while ((bfRead = bf.readLine()) != null) {

				ArrayList<String> coord = extraerCoordenada(bfRead);

				if (coord.size() == 2)
					coordenadas.add(new Coordinate(Double.parseDouble(coord.get(0)), Double.parseDouble(coord.get(1))));
				else if (coord.size() > 2) {
					throw new IllegalArgumentException();
				} else
					continue;

			}
			
		} 
		catch (IOException e) {
			
		} 
		
		return coordenadas;
	}

	private ArrayList<String> extraerCoordenada(String bfRead) {
		StringTokenizer tokens = new StringTokenizer(bfRead);

		ArrayList<String> coord = new ArrayList<String>();

		while (tokens.hasMoreElements()) {
			coord.add(tokens.nextToken());
		}
		return coord;
	}

}
