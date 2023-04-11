package practica3IC;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
        ArrayList<double[]> points = new ArrayList<double[]>();
        

        points.add(arrayOf(-1.5, 2.0, 5.0, 1.2));
        points.add(arrayOf(-2.0, -2.0, 0.0,3.4));
        points.add(arrayOf(1.0, 1.0, 2.0,2.2));
        points.add(arrayOf(1.5, 1.5, 1.2,0.0));
        points.add(arrayOf(1.0, 2.0, 5.6,0.0));
        points.add(arrayOf(1.0, -2.0, -2.0,1.2));
        points.add(arrayOf(1.0, -3.0, -2.0,1.0));
        points.add(arrayOf(1.0, -2.5, -4.5,1.0));
        Lloyd gl = new Lloyd(points.toArray(new double[points.size()][2]));
 
        double[][] results = gl.getClusterPoints(4);
        for (double[] point : results)
        {
            System.out.println("Cluster " + point[0] + ", " + point[1] + ", "
                    + point[2]);
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
