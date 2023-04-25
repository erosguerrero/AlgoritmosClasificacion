package auxiliar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public final class LecturaDatos {

	private static ArrayList<double[]> datos;
	private static ArrayList<String> clasesDeDatos;
	private static ArrayList<String> clases;
	
	public static ArrayList<double[]> readDatos(String nomFile) {
		if (nomFile == null || nomFile.equals("")) {
			throw new NullPointerException();
		}
		File file = new File(nomFile);
		datos = new ArrayList<double[]> ();
		clasesDeDatos = new ArrayList<String>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] linea = line.split(",");
				double[] ejemplo = new double[linea.length - 1];
				for (int i = 0; i < linea.length; i++) {
					if(i == linea.length-1) {
						clasesDeDatos.add(linea[i]);
					}
					else {
						ejemplo[i] = Double.valueOf(linea[i]);
					}
				}
				datos.add(ejemplo);
			}
		} catch (IOException e ) {
			e.printStackTrace();
			
		}
		clases = new ArrayList<String>();
		for(int i = 0; i < clasesDeDatos.size(); i++) {
			if(!clases.contains(clasesDeDatos.get(i))) {
				clases.add(new String (clasesDeDatos.get(i)));
			}
		}
		return datos;
	}

	public static ArrayList<double[]> getDatos() {
		return datos;
	}

	public static void setDatos(ArrayList<double[]> datos) {
		LecturaDatos.datos = datos;
	}

	public static ArrayList<String> getClasesDeDatos() {
		return clasesDeDatos;
	}
	
	public static ArrayList<String> getclases() {
		return clases;
	}

	public static void setClasesDeDatos(ArrayList<String> clasesDeDatos) {
		LecturaDatos.clasesDeDatos = clasesDeDatos;
	}
	
}