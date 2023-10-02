package vista;

import io.*;
import modelo.*;

/**
 * Clase que contiene los métodos relacionados con la vista del usuario
 */
public class VistaUsuario {

	/**
	 * Muestra el menú al usuario
	 */
	public static void mostrarMenu() {
		String menu = "MENÚ \n" +
					"1. Buscar por código de usuario \n" +
					"2. Buscar por el comienzo del nombre \n" +
					"3. Mostrar la agenda completa \n" +
					"4. Añadir un contacto \n" +
					"5. Borrar un contacto \n" + 
					"6. Salir";
		System.out.println(menu);			
	}
	
	/**
	 * Solicita la opción que desea el usuario
	 * @return
	 */
	public static int solicitarOpcion() {
		System.out.println("Opción ? ");
		return IO.readInt();
	}
	
	public static Contacto solicitarContacto() {
		System.out.println("Nombre ?");
		String nombre = IO.readStringNoEmpty();
		System.out.println("Teléfono ?");
		String telefono = IO.readString();
		System.out.println("Edad ?");
		int edad = IO.readInt();
		
		return new Contacto(nombre, telefono, edad);
	}
}
