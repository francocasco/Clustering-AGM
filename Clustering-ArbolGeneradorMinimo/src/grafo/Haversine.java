package grafo;

public class Haversine {
	public static double formula(double lat1, double lon1, double lat2, double lon2) {
		double distancia;
		double radio = 6731;
		
		lat1 = Math.toRadians(lat1);
		lon1 = Math.toRadians(lon1);
		lat2 = Math.toRadians(lat2);
		lon2 = Math.toRadians(lon2);
		
		double dlon = (lon2 - lon1);
		double dlat = (lat2 - lat1);
		
		double sinlat = Math.sin(dlat / 2);
		double sinlon = Math.sin(dlon / 2);
		
		double a = (sinlat * sinlat) + Math.cos(lat1)*Math.cos(lat2)*(sinlon*sinlon);
		double c = 2 * Math.asin (Math.min(1.0, Math.sqrt(a)));

		distancia = radio * c * 1000;
		return distancia;
	}

}
