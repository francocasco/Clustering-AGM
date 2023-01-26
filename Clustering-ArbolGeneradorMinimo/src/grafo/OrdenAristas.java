package grafo;

import java.util.Comparator;

public class OrdenAristas implements Comparator<Arista> {

	@Override
	public int compare(Arista a, Arista b) {
		if(a.getPeso() > b.getPeso()) {
			return 1;
		}else if(a.getPeso() < b.getPeso()) {
			return -1;
		}else
			return 0;
	}

}
