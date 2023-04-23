package algoritmos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Jama.Matrix;


public class Bayes{
	private HashMap<String, ArrayList<double []>> puntos; //String es el nombre de cada clase
	private HashMap<String, double []> centros;
	private HashMap<String, Matrix> mCovarianzas;
	
	public Bayes() {
        leerDatos();
		this.centros = new HashMap<String, double []>();
		this.mCovarianzas = new HashMap<String, Matrix>();
	}
	
	public Bayes(ArrayList<double []> _puntos, ArrayList<String> _clasesPuntos) {
		inicializaDatos(_puntos, _clasesPuntos);
		this.centros = new HashMap<String, double []>();
		this.mCovarianzas = new HashMap<String, Matrix>();
	}
	
	public void execute() {
		calculaCentros();
		calculaMatricesCovarianza();
	}
	
	private void inicializaDatos(ArrayList<double[]> _puntos, ArrayList<String> _clasesPuntos) {
		this.puntos = new HashMap<String, ArrayList<double []>>();
		
		for(int i = 0; i < _puntos.size(); i++) {
			if(!puntos.containsKey(_clasesPuntos.get(i))) {
				puntos.put(_clasesPuntos.get(i), new ArrayList<double[]>());
			}
			puntos.get(_clasesPuntos.get(i)).add(_puntos.get(i));
		}
	}
	
	private void calculaCentros() {
		for(String clase: this.puntos.keySet()) {
			double [] aux = new double [this.puntos.get(clase).get(0).length];
			for(double [] p: this.puntos.get(clase)) {
				for(int i = 0; i < p.length; i++) {
					aux[i] += p[i];
				}
			}
			for(int j = 0; j < aux.length; j++) {
				aux[j] =  aux[j] / this.puntos.get(clase).size();
			}
			this.centros.put(clase,aux);
		}
	}
	private void calculaMatricesCovarianza() {
		for(String clase: this.centros.keySet()) {	
			Matrix mCov = inicializaMatriz(this.puntos.get(clase).get(0).length);
			Matrix mCentro = getMatrix(this.centros.get(clase)); 
			for(double[] puntoClase: this.puntos.get(clase)) {
				Matrix mPunto  = getMatrix(puntoClase);
				mCov = mCov.plus((mPunto.minus(mCentro)).times((mPunto.minus(mCentro).transpose())));
			}
		
			double n =  1.0/this.puntos.get(clase).size();
			mCov.timesEquals(n);
			mCovarianzas.put(clase, mCov);
		}
	}

	public String clasificarNuevo(double[] punto) {
		String claseMejor = "";
		double fMejor = 0, f = 0;
		int i = 0;
		for(String c: this.centros.keySet()) {
			f = calcularFProbabilidad(punto,c);
			if(i != 0) {
				if(fMejor < f) {
					claseMejor = c;
					fMejor = f;
				}
			}
			else {
				fMejor = f;
				claseMejor = c;
			}
			i++;
		}
		
		return claseMejor;
	}
	
	private double calcularFProbabilidad(double[] punto, String clase) {
		Matrix mPunto = getMatrix(punto);
		Matrix mCentro = getMatrix(this.centros.get(clase));
		int d = mPunto.getRowDimension();
		Matrix cInversa = this.mCovarianzas.get(clase).inverse();
		double distMahalanobisAlCuadrado =  (((mPunto.minus(mCentro)).transpose()).times(cInversa)).times(mPunto.minus(mCentro)).get(0, 0); // 1 x 1
		double aux = distMahalanobisAlCuadrado * -0.5;
		double f = Math.exp(aux);
		double sol = f * 1.0/(Math.pow(2*Math.PI, d/2)*Math.pow(this.mCovarianzas.get(clase).det(), 0.5));
		return sol;
	}
	
	public ArrayList<double[]> getCentros() {
		ArrayList<double[]> sol = new ArrayList<double[]>();
		for(String c: this.centros.keySet()) {
			sol.add(this.centros.get(c));
		}
		return sol;
	}

	public HashMap<String, Matrix> getmCovarianzas() {
		return mCovarianzas;
	}

	public void setmCovarianzas(HashMap<String, Matrix> mCovarianzas) {
		this.mCovarianzas = mCovarianzas;
	}
	
	public static Matrix getMatrix(double [] dif) { //matriz columna
		double [][] mDif = new double[dif.length][1];
		for(int i = 0; i < dif.length; i++) {
			mDif[i][0] = dif[i];
		}
		return Matrix.constructWithCopy(mDif);
	}
	
	public static Matrix inicializaMatriz(int length) {
		double [][] aux =  new double [length][length];
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < length; j++) {
				aux[i][j] = 0;
			}
			
		}
		return Matrix.constructWithCopy(aux);
	}
	
	private void leerDatos(){
		this.puntos = new HashMap<String, ArrayList<double []>>();
		try {
			File f = new File("src/Datos/Iris2Clases.txt");
			FileReader r = new FileReader(f);
			BufferedReader lectorLinea = new BufferedReader(r);
			String l;
			while ((l = lectorLinea.readLine()) != null) {
				String[] punto = l.split(",");
				
				if(!puntos.containsKey(punto[4])) {
					puntos.put(punto[4], new ArrayList<double[]>());
				}
				double[] d = {};
				for (int i = 0; i < punto.length - 1; i++) {
					d[i] = Double.parseDouble(punto[i]);
				}
				puntos.get(punto[4]).add(d);
			}
			lectorLinea.close();
	
	    } catch (IOException e) {
	      System.out.println("Error al leer el archivo: " + e.getMessage());
	    }
	}
}
