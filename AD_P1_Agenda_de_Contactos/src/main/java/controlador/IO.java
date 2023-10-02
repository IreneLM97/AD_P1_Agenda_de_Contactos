package controlador;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import constantes.color.Colores;

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
	public static Properties cargarProperties() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = IO.class.getResourceAsStream("/StringResources.properties");
            if (input != null) {
                prop.load(input);
            } else {
                System.err.println("No se pudo cargar el archivo de propiedades.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return prop;
    }
}
