package practica3IC;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
        ArrayList<double[]> points = new ArrayList<double[]>();
        

        double[] pt1 = {-1.5, 2.0, 5.0, 0.0};
        points.add(pt1);
        double[] pt2 = {-2.0, -2.0, 0.0, 0.0};
        points.add(pt2);
        double[] pt3 = {1.0, 1.0, 2.0, 0.0};
        points.add(pt3);
        double[] pt4 = {1.5, 1.5, 1.2, 0.0};
        points.add(pt4);
        double[] pt5 = {1.0, 2.0, 5.6, 0.0};
        points.add(pt5);
        double[] pt6 = {1.0, -2.0, -2.0, 0.0};
        points.add(pt6);
        double[] pt7 = {1.0, -3.0, -2.0, 0.0};
        points.add(pt7);
        double[] pt8 = {1.0, -2.5, -4.5, 0.0};
        points.add(pt8);
        Lloyd gl = new Lloyd(points.toArray(new double[points.size()][2]));
 
        double[][] results = gl.getClusterPoints(4);
        for (double[] point : results)
        {
            System.out.println("Cluster " + point[0] + ", " + point[1] + ", "
                    + point[2]);
        }
	}

}
