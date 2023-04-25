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
        ejecutarBayes(puntos,centros,clases);
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
	 
	 private static void ejecutarBayes(ArrayList<double []> puntos, ArrayList<double []> centros, ArrayList<String> clases) {
		Bayes b = new Bayes("src/Datos/Iris2Clases.txt");
		b.execute();
		System.out.println("\n****************** [BAYES] **********************\n");
		System.out.println("**** Centros **** ");
		ArrayList<double[]> sol2 = b.getCentros();
		System.out.println(Auxiliar.centros2String(sol2));
		System.out.println("**** Covarianzas **** ");
		HashMap<String, Matrix> mCov = b.getmCovarianzas();
		for(String c: mCov.keySet()) {
			System.out.println("	**** " + c + "**** ");
			System.out.println(Auxiliar.matrix2String(mCov.get(c)));
		}
		
		/*TEST01*/
		LecturaDatos.readDatos("./src/datos/TestIris01.txt");
		System.out.println("**** Resultados **** ");
		ArrayList<double []> aux= LecturaDatos.getDatos();
		double[] punto = aux.get(0);
		String clase = b.clasificarNuevo(punto);
		System.out.println("Clase del ejemplo 01: " +  clase);
		
		/*TEST02*/
		LecturaDatos.readDatos("./src/datos/TestIris02.txt");
		aux= LecturaDatos.getDatos();
		punto = aux.get(0);
		clase = b.clasificarNuevo(punto);
		System.out.println("Clase del ejemplo 01: " +  clase);
		
		/*TEST02*/
		LecturaDatos.readDatos("./src/datos/TestIris03.txt");
		aux= LecturaDatos.getDatos();
		punto = aux.get(0);
		clase = b.clasificarNuevo(punto);
		System.out.println("Clase del ejemplo 01: " +  clase);
	}
	
}
