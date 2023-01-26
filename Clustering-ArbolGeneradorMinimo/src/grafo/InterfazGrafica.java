package grafo;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import leerarchivo.Archivo;
import javax.swing.JTextField;

public class InterfazGrafica {

	private JFrame frame;
	private JPanel panelMapa, panelInicio;
	private JButton btnIniciarGrafo, btnExaminarRuta, btnVolverInicio, btnCompletarGrafo, btnPrim;
	private JTextField textRuta;
	private JMapViewer mapa;

	private GrafoConPeso grafo;
	private Archivo archivo;

	private ArrayList<MapPolygonImpl> lineasMapa = new ArrayList<MapPolygonImpl>();
	private JButton btnBorrarLineas;
	private JButton btnkruskal;
	private JButton btnBorrarArista;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazGrafica window = new InterfazGrafica();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazGrafica() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		initialize();
		map();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 596, 407);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panelInicio = new JPanel();
		panelInicio.setBounds(0, 0, 580, 368);
		frame.getContentPane().add(panelInicio);
		panelInicio.setLayout(null);

		/*
		 * Accion de iniciar grafo
		 */
		btnIniciarGrafo = new JButton("Iniciar Grafo");
		btnIniciarGrafo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				archivo = new Archivo();

				try {
					grafo = new GrafoConPeso(archivo.obtenerCoordenadas(textRuta.getText()));
					mostrarPuntosEnMapa();
					panelInicio.setVisible(false);
					panelMapa.setVisible(true);
				} catch (IllegalArgumentException e1) {
					JOptionPane.showMessageDialog(null, "El archivo no contiene coordenadas.");
				} catch (IndexOutOfBoundsException e2) {
					JOptionPane.showMessageDialog(null, "No se encontro el archivo");
				} catch (IOException e3) {

				}

			}
		});
		btnIniciarGrafo.setBounds(237, 224, 106, 23);
		panelInicio.add(btnIniciarGrafo);

		/*
		 * Accion de examinar archivo
		 */
		btnExaminarRuta = new JButton("Examinar...");
		btnExaminarRuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser fc = new JFileChooser();

				fc.setCurrentDirectory(new File("instancias"));
				FileNameExtensionFilter filtro;
				filtro = new FileNameExtensionFilter("Documento de texto(.txt)", "txt");
				fc.setFileFilter(filtro);
				int seleccion = fc.showOpenDialog(null);

				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File fichero = fc.getSelectedFile();
					String ruta = fichero.getAbsolutePath();
					textRuta.setText(ruta);

				}

			}
		});
		btnExaminarRuta.setBounds(238, 190, 105, 23);
		panelInicio.add(btnExaminarRuta);

		textRuta = new JTextField();
		textRuta.setBounds(75, 157, 429, 20);
		panelInicio.add(textRuta);
		textRuta.setColumns(10);

	}

	private void mostrarPuntosEnMapa() throws IOException {
		mapa.setDisplayPosition(grafo.getCoordenada(0), 13);
		List<MapMarker> mapMarkerList = new ArrayList<MapMarker>();
		for (int i = 0; i < grafo.tamanio(); i++) {
			mapMarkerList.add(new MapMarkerDot(grafo.getCoordenada(i)));

		}

		mapa.setMapMarkerList(mapMarkerList);

	}

	private void dibujarTodasLasLineas() {
		for (int i = 0; i < grafo.tamanio(); i++) {
			for (int j = 0; j < grafo.tamanio(); j++) {
				if (i != j && grafo.existeArista(i, j)) {
					dibujarLineaEntreDosVertices(i, j, Color.black);
				}
			}
		}
		for (int i = 0; i < lineasMapa.size(); i++) {
			mapa.addMapPolygon(lineasMapa.get(i));
		}

	}

	private void dibujarLineaEntreDosVertices(int punto_a, int punto_b, Color color) {
		ArrayList<Coordinate> coordenadasLinea = new ArrayList<Coordinate>();

		Coordinate a = grafo.getCoordenada(punto_a);
		Coordinate b, c;
		b = c = grafo.getCoordenada(punto_b);

		coordenadasLinea.add(a);
		coordenadasLinea.add(b);
		coordenadasLinea.add(c);

		MapPolygonImpl linea = new MapPolygonImpl(coordenadasLinea);
		linea.setColor(color);
		lineasMapa.add(linea);
	}

	private void map() {
		panelMapa = new JPanel();
		panelMapa.setBounds(0, 0, 580, 368);
		panelMapa.setVisible(false);
		frame.getContentPane().add(panelMapa);

		mapa = new JMapViewer();
		mapa.setBounds(3, 5, 400, 400);
		mapa.setDisplayPosition(new Coordinate(-34.5365767, -58.710699), 15);
		mapa.setVisible(true);
		panelMapa.setLayout(null);
		panelMapa.add(mapa);

		btnCompletarGrafo = new JButton("Completar Grafo");
		btnCompletarGrafo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grafo.completarGrafo();
				dibujarTodasLasLineas();
			}
		});
		btnCompletarGrafo.setBounds(428, 26, 142, 23);
		panelMapa.add(btnCompletarGrafo);

		btnVolverInicio = new JButton("Volver");
		btnVolverInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMapa.setVisible(false);
				panelInicio.setVisible(true);

			}
		});
		btnVolverInicio.setBounds(507, 319, 63, 23);
		panelMapa.add(btnVolverInicio);

		btnPrim = new JButton("Generar Arbol");
		btnPrim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				grafo = Algoritmo.prim(grafo);
				borrarLineas();
				dibujarTodasLasLineas();
			}
		});
		btnPrim.setBounds(428, 123, 142, 23);
		panelMapa.add(btnPrim);

		btnBorrarLineas = new JButton("Borrar Lineas");
		btnBorrarLineas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarLineas();
			}
		});
		btnBorrarLineas.setBounds(428, 49, 142, 23);
		panelMapa.add(btnBorrarLineas);
		
		btnkruskal = new JButton("Generar Arbol v2");
		btnkruskal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grafo = Algoritmo.kruskal(grafo);
				borrarLineas();
				dibujarTodasLasLineas();
			}
		});
		btnkruskal.setBounds(428, 146, 142, 23);
		panelMapa.add(btnkruskal);
		
		
		JTextField cantClusters = new JTextField();
		cantClusters.setBounds(428, 205, 142, 23);
		panelMapa.add(cantClusters);
		
		btnBorrarArista = new JButton("Crear Clusters");
		btnBorrarArista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int cantAristasEliminar = Integer.parseInt(cantClusters.getText()) - 1 ;
				grafo.eliminarAristasMayores(cantAristasEliminar);
				borrarLineas();
				dibujarTodasLasLineas();
				
			}
		});
		btnBorrarArista.setBounds(428, 230, 142, 23); 
		panelMapa.add(btnBorrarArista);
		
	}

	private void borrarLineas() {
		mapa.removeAllMapPolygons();
		lineasMapa.clear();
	}
}
