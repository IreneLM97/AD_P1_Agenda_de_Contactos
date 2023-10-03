package vista;

import java.util.ArrayList;
import java.util.UUID;

import modelo.*;
import constantes.color.*;

/**
 * Clase que contiene los métodos relacionados con la vista del usuario
 */
public class VistaUsuario {

	/**
	 * Muestra el menú al usuario
	 */
	public static void mostrarMenu() {
	    System.out.println(	"-------------------------------------------------\n" + Colores.NEGRITA +
	    					"|\t\tAGENDA\t\t\t\t|\n" +
						    "-------------------------------------------------\n" +
						    "|\t" + Colores.NEGRITA + "1. Buscar por código de usuario\t\t" + Colores.RESET + "|\n" +
						    "|\t" + Colores.NEGRITA + "2. Buscar por el comienzo del nombre\t" + Colores.RESET + "|\n" +
						    "|\t" + Colores.NEGRITA + "3. Mostrar la agenda completa\t\t" + Colores.RESET + "|\n" +
						    "|\t" + Colores.NEGRITA + "4. Añadir un contacto\t\t\t" + Colores.RESET + "|\n" +
						    "|\t" + Colores.NEGRITA + "5. Borrar un contacto\t\t\t" + Colores.RESET + "|\n" +
						    "|\t" + Colores.NEGRITA + "6. Salir\t\t\t\t" + Colores.RESET + "|\n" +
						    "-------------------------------------------------");
	}
	
	/**
	 * Dibuja un separador en la vista del usuario
	 */
	public static void mostrarSeparador() {
	    System.out.println(Colores.FONDO_BLANCO + 
	    		Colores.BLANCO + 
	    		"\n===========================================================================\n" + 
	    		Colores.RESET);
	}
	
	/**
	 * Solicita la opción que desea el usuario
	 * @return
	 */
	public static int solicitarOpcion() {
		System.out.print("Opción?:  ");
		return IO.readInt();
	}
	
	/**
	 * Solicita el UUID de búsqueda
	 * @return
	 */
	public static UUID solicitarUUID() {
		System.out.print(Colores.RESET + "UUID?: ");
		return IO.readUUID();
	}
	
	/**
	 * Solicita el nombre de búsqueda
	 * @return
	 */
	public static String solicitarNombre() {
		System.out.print("Nombre?: ");
		return IO.readStringNoEmpty();
	}
	
	/**
	 * Solicita la información de un contacto
	 * @return 
	 */
	public static Contacto solicitarContacto() {
		System.out.print("Nombre?: ");
		String nombre = IO.readStringNoEmpty();
		System.out.print("Teléfono?: ");
		String telefono = IO.readString();
		System.out.print("Edad?: ");
		int edad = IO.readInt();
		
		return new Contacto(nombre, telefono, edad);
	}
	
	/**
	 * Muestra una lista de contactos de la agenda
	 * @param lista de contactos a mostrar
	 */
	public static void mostrarContactos(ArrayList<Contacto> contactos) {
	    System.out.println("--------------------------------------------------------------------------------------");
	    System.out.printf("| %-50s | %-10s | %-3s | %-10s|%n", "UUID", "Nombre", "Edad", "Teléfono");
	    System.out.println("--------------------------------------------------------------------------------------");

	    contactos.forEach(contacto -> {
	        System.out.printf("|");
	        if (contacto.getUsuario().toString().equals("00000000-0000-0000-0000-000000000000")) {
	        	System.out.printf(Colores.ROJO + " BORRADO ->" + Colores.RESET);
	        	System.out.printf( Colores.AMARILLO + " %39s " + Colores.RESET + "|" + Colores.CYAN +" %-10s " + Colores.RESET + "|" + Colores.AMARILLO +" %-3d " + Colores.RESET + "|" + Colores.CYAN +" %-10s " + Colores.RESET + "|%n",
		                contacto.getUsuario(),
		                contacto.getNombre(),
		                contacto.getEdad(),
		                contacto.getTelefono());
	        }else {
	        	System.out.printf( Colores.AMARILLO + " %50s " + Colores.RESET + "|" + Colores.CYAN +" %-10s " + Colores.RESET + "|" + Colores.AMARILLO +" %-3d " + Colores.RESET + "|" + Colores.CYAN +" %-10s " + Colores.RESET + "|%n",
		                contacto.getUsuario(),
		                contacto.getNombre(),
		                contacto.getEdad(),
		                contacto.getTelefono());
	        }
	        
	    });

	    System.out.println("--------------------------------------------------------------------------------------");
	}
	
	/**
	 * Muestra un mensaje al usuario
	 * @param msg a mostrar
	 */
	public static void mostrarMsg(String msg) {
		System.out.print(msg);
	}
	
}
