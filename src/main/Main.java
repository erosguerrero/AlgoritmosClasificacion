package main;

import java.util.ArrayList;
import java.util.HashMap;

import Jama.*;
import algoritmos.Bayes;
import algoritmos.KMedias;
import algoritmos.Lloyd;
import auxiliar.Auxiliar;
import auxiliar.LecturaDatos;

public class Main {

	public static void main(String[] args) {
		LecturaDatos.readDatos("./src/datos/Iris2Clases.txt");
		ArrayList<double []> puntos= LecturaDatos.getDatos();
		ArrayList<String> clases = LecturaDatos.getclases();
		
		ArrayList<double []> centros = new ArrayList<double[]>();
		centros.add(new double[] {4.6,3.0, 4.0, 0.0});
		centros.add(new double[] {6.8, 3.4, 4.6, 0.7});

		//K-MEDIAS******************************************************
        ejecutarKmeans(puntos,centros,clases);
		
		//BAYES**********************************************************
        //Bayes b = new Bayes();
        //b.execute();
        
		//LLOYD**********************************************************
        ejecutarLloyd(puntos,centros,clases);
        

        
	}
	
	 private static void ejecutarKmeans(ArrayList<double []> puntos, ArrayList<double []> centros, ArrayList<String> clases) {
	        KMedias k_medias = new KMedias(puntos,centros);
			k_medias.execute();
			ArrayList<double[]> sol2 = k_medias.getCentros();
			System.out.println("\n****************** [K-MEDIAS] **********************\n");
			System.out.println("**** Centros ****");
			System.out.println(Auxiliar.centros2String(sol2));
			System.out.println("**** Matriz U ****");
			System.out.println(Auxiliar.u2String(k_medias.getU()));
			
			System.out.println("**** Resultados ****");
			/*TEST01*/
			LecturaDatos.readDatos("./src/datos/TestIris01.txt");
			ArrayList<double []> aux= LecturaDatos.getDatos();
			double[] punto = aux.get(0);
			int clase = k_medias.clasificarNuevo(punto);
			System.out.println("Clase del ejemplo 01: " +  clases.get(clase));
			
			/*TEST02*/
			LecturaDatos.readDatos("./src/datos/TestIris02.txt");
			aux= LecturaDatos.getDatos();
			punto = aux.get(0);
			clase = k_medias.clasificarNuevo(punto);
			System.out.println("Clase del ejemplo 02: " +  clases.get(clase));
			
			/*TEST03*/
			LecturaDatos.readDatos("./src/datos/TestIris03.txt");
			aux= LecturaDatos.getDatos();
			punto = aux.get(0);
			clase = k_medias.clasificarNuevo(punto);
			System.out.println("Clase del ejemplo 03: " +  clases.get(clase));

	 }
	
	 private static void ejecutarLloyd(ArrayList<double []> puntos, ArrayList<double []> centros, ArrayList<String> clases) {
	        Lloyd lloyd_alg = new Lloyd(puntos,centros);
	        lloyd_alg.execute();
			ArrayList<double[]> sol2 = lloyd_alg.getCentros();
			System.out.println("\n****************** [LLOYD] **********************\n");
			System.out.println("**** Centros ****");
			System.out.println(Auxiliar.centros2String(sol2));
			
			System.out.println("**** Resultados ****");
			/*TEST01*/
			LecturaDatos.readDatos("./src/datos/TestIris01.txt");
			ArrayList<double []> aux= LecturaDatos.getDatos();
			double[] punto = aux.get(0);
			int clase = lloyd_alg.clasificarNuevo(punto);
			System.out.println("Clase del ejemplo 01: " +  clases.get(clase));
			
			/*TEST02*/
			LecturaDatos.readDatos("./src/datos/TestIris02.txt");
			aux= LecturaDatos.getDatos();
			punto = aux.get(0);
			clase = lloyd_alg.clasificarNuevo(punto);
			System.out.println("Clase del ejemplo 02: " +  clases.get(clase));
			
			/*TEST03*/
			LecturaDatos.readDatos("./src/datos/TestIris03.txt");
			aux= LecturaDatos.getDatos();
			punto = aux.get(0);
			clase = lloyd_alg.clasificarNuevo(punto);
			System.out.println("Clase del ejemplo 03: " +  clases.get(clase));
	 }
	 
	private static void ejecutarBayes() {
		/*
		ArrayList<double []> puntos = new ArrayList<double []>();
		ArrayList<String> clases = new ArrayList<String>();
		puntos.add(new double[] {1,2});
		clases.add("Clase 1");
		puntos.add(new double[] {2,1});
		clases.add("Clase 1");
		puntos.add(new double[] {0,3});
		clases.add("Clase 1");
		puntos.add(new double[] {4,5});
		clases.add("Clase 2");
		puntos.add(new double[] {2,4});
		clases.add("Clase 2");
		puntos.add(new double[] {3,3});
		clases.add("Clase 2");
		
		Bayes b = new Bayes(puntos,clases);
		b.execute();
		System.out.println("########################################## \n");
		System.out.println("Algoritmo de Bayes -  Ejercicio 1, Hoja 2");
		System.out.println("..........................................");
		System.out.println("Centros obtenidos:");
		System.out.println(Auxiliar.centros2String(b.getCentros()));
		System.out.println("Matrices de Covarianza:");
		HashMap<String, Matrix> mCov = b.getmCovarianzas();
		for(String c: mCov.keySet()) {
			System.out.println("Clase: " + c + "\n");
			System.out.println(Auxiliar.matrix2String(mCov.get(c)));
		}
		*/
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
