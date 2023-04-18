package Main;

import java.util.ArrayList;

import Algoritmos.Bayes;
import Algoritmos.Lloyd;

public class Main {

	public static void main(String[] args) {
        ArrayList<double[]> points = new ArrayList<double[]>();
        ArrayList<String> clases = new ArrayList<String>();

        points.add(arrayOf(2.0, 3.0, 1.0, 1.2));
        points.add(arrayOf(4.0, 5.0, 4.0,3.4));
        points.add(arrayOf(6.0, 5.0, 7.0,2.2));
        points.add(arrayOf(2.0, 2.0, 3.0,0.0));
        points.add(arrayOf(5.0, 6.0, 6.0,0.0));
        points.add(arrayOf(1.0, -2.0, -2.0,1.2));
        points.add(arrayOf(1.0, -3.0, -2.0,1.0));
        points.add(arrayOf(1.0, -2.5, -4.5,1.0));
        
        
        ejecutarBayes();
        Bayes b = new Bayes();
        b.execute();
        /*
        Lloyd gl = new Lloyd(points.toArray(new double[points.size()][2]));
        double[][] results = gl.getClusterPoints(2);
        for (double[] point : results)
        {
            System.out.println("Cluster " + point[0] + ", " + point[1] + ", "
                    + point[2]);
        }
        */
	}
	
	private static void ejecutarBayes() {
		// Ejercicio 1, Hoja 2
		ArrayList<double []> puntos = new ArrayList<double []>();
		puntos.add(new double[] {3,1});
		puntos.add(new double[] {2,2});
		puntos.add(new double[] {1,0});
		puntos.add(new double[] {6,7});
		puntos.add(new double[] {7,5});
		puntos.add(new double[] {8,6});
				
		ArrayList<String> clases = new ArrayList<String>();//clase de cada punto
		clases.add("Clase 1");
		clases.add("Clase 1");
		clases.add("Clase 1");
		clases.add("Clase 2");
		clases.add("Clase 2");
		clases.add("Clase 2");
		
		ArrayList<double []> centros = new ArrayList<double[]>();
		centros.add(new double[] {2,1});
		centros.add(new double[] {7,6});
		
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
