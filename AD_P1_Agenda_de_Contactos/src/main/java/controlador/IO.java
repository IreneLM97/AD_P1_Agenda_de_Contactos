package controlador;

import java.util.Scanner;

/**
 * Clase con métodos de lectura correcta de datos
 */
public class IO {
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * Lee un valor de tipo int
	 * 
	 * @return
	 */
	static public int readInt() {
		while (true) {
			try {
				return Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.err.print("ERROR: no es de tipo int ? ");
			}
		}
	}
	
	/**
	 * Lee un valor de tipo String sin admitir cadena vacía
	 * 
	 * @return
	 */
	static public String readStringNoEmpty() {
		while (true) {
			String lectura = sc.nextLine().trim();
			if(lectura.isEmpty()) {
				System.err.print("ERROR: este campo es obligatorio ? ");
			} else {
				return lectura;
			}
		}
	}
	
	/**
	 * Lee un valor de tipo String admitiendo cadena vacía
	 * 
	 * @return
	 */
	static public String readString() {
		return sc.nextLine();
	}
	
	/**
	 * Carga el fichero de properties que se encuentra en la carpeta resources (Por defecto eclipse sabe identificar esta carpeta)
	 * 
	 * @return Properties
	 */
	
}
