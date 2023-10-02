package vista;

import java.util.UUID;

import controlador.IO;
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
	
	/**
	 * Solicita el UUID de búsqueda
	 * @return
	 */
	public static UUID solicitarUUID() {
		System.out.println("UUID ?");
		try {
		    UUID usuarioUUID = java.util.UUID.fromString(IO.readStringNoEmpty());
		    return usuarioUUID;
		} catch (IllegalArgumentException e) {
		    return null;
		}	
	}
	
	/**
	 * Solicita el nombre de búsqueda
	 * @return
	 */
	public static String solicitarNombre() {
		System.out.println("Nombre ?");
		return IO.readStringNoEmpty();
	}
	
	/**
	 * Solicita la información de un contacto
	 * @return 
	 */
	public static Contacto solicitarContacto() {
		System.out.println("Nombre ?");
		String nombre = IO.readStringNoEmpty();
		System.out.println("Teléfono ?");
		String telefono = IO.readString();
		System.out.println("Edad ?");
		int edad = IO.readInt();
		
		return new Contacto(nombre, telefono, edad);
	}
	
	/**
	 * Muestra todos los contactos de la agenda
	 * @param agenda a mostrar
	 */
	public static void mostrarAgenda(AgendaModelo agenda) {
		agenda.obtenerAgenda().stream().forEach(System.out :: println);
	}
	
	/**
	 * Muestra un mensaje al usuario
	 * @param msg a mostrar
	 */
	public static void mostrarMsg(String msg) {
		System.out.println(msg);
	}
}
