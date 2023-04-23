package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import algoritmos.Kmeans;
import auxiliar.LecturaDatos;
import algoritmos.Auxiliar;

public class Main {

	public static void main(String[] args) {
		LecturaDatos.readDatos("./Iris2Clases.txt");
		ArrayList<double []> puntos= LecturaDatos.getDatos();
		ArrayList<String> clases = LecturaDatos.getclases();
		
		ArrayList<double []> centros = new ArrayList<double[]>();
		centros.add(new double[] {4.6,3.0, 4.0, 0.0});
		centros.add(new double[] {6.8, 3.4, 4.6, 0.7});
		
       
        
        ejecutarKmeans(puntos,centros,clases);
        
       
	}
	
	 private static void ejecutarKmeans(ArrayList<double []> puntos, ArrayList<double []> centros, ArrayList<String> clases) {
	        Kmeans k_medias = new Kmeans(puntos,centros);
			k_medias.execute();
			ArrayList<double[]> sol2 = k_medias.getCentros();
			System.out.println("########################################## \n");
			System.out.println("Algoritmo k_means borroso - Ejercicio 2, Hoja 2");
			System.out.println("..........................................");
			System.out.println("Centros obtenidos:");
			System.out.println(Auxiliar.centros2String(sol2));
			System.out.println("Matriz de grados de pertenencia U:");
			System.out.println(Auxiliar.u2String(k_medias.getU()));
			
			/*TEST01*/
			LecturaDatos.readDatos("./TestIris01.txt");
			ArrayList<double []> aux= LecturaDatos.getDatos();
			double[] punto = aux.get(0);
			int clase = k_medias.clasificarNuevo(punto);
			System.out.println("Clase del ejemplo 01: " +  clases.get(clase));
			
			/*TEST02*/
			LecturaDatos.readDatos("./TestIris02.txt");
			aux= LecturaDatos.getDatos();
			punto = aux.get(0);
			clase = k_medias.clasificarNuevo(punto);
			System.out.println("Clase del ejemplo 02: " +  clases.get(clase));
			
			/*TEST02*/
			LecturaDatos.readDatos("./TestIris03.txt");
			aux= LecturaDatos.getDatos();
			punto = aux.get(0);
			clase = k_medias.clasificarNuevo(punto);
			System.out.println("Clase del ejemplo 03: " +  clases.get(clase));
	 }
	
	
    private static double[] arrayOf(double x, double y, double z, double w)
    {
        double[] a = new double[4];
        a[0] = x;
        a[1] = y;
        a[2] = z;
        a[3] = w;
        return a;
    }
}
