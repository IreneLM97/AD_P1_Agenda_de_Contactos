package controlador;

import java.util.ArrayList;

import modelo.*;
import vista.*;

/**
 * Clase principal del programa
 * 
 * @author Sergio Ania Lázaro - Ricardo de Antonio Aguirre - Irene López Melero
 * @version 1.0
 * @since 2023-10-02
 */

public class Principal {

	public static void main(String[] args) {
		// creamos una agenda 
		AgendaModelo agenda = new AgendaModelo("contactos.dat");
		
		while(true) {
			// mostramos el menú al usuario
			VistaUsuario.mostrarMenu();
			
			// recogemos la opción marcada
			int opcion = VistaUsuario.solicitarOpcion();
			switch(opcion) {
				case 1: // buscar por código
					
					break;
					
				case 2: // buscar por nombre
					break;
					
				case 3: // mostrar agenda completa
					ArrayList<Contacto> contactos = agenda.obtenerAgenda();
					if(contactos == null) {  // no se pueden mostrar los contactos
						System.out.println("No se pueden mostrar los datos");
					} else {  // mostramos los contactos
						agenda.obtenerAgenda().stream().forEach(System.out :: println);
					}
					break;
					
				case 4: // añadir contacto
					Contacto contacto = VistaUsuario.solicitarContacto();
					String respuesta = agenda.agregarContacto(contacto) ? "Añadido correctamente" : "No se ha podido añadir";
					System.out.println(respuesta);
					break;
					
				case 5: // eliminar contacto
					break;
					
				case 6: // borrar contacto
					System.out.println("Has salido del menú");
					return;
					
				default: // opción inválida
					System.err.println("No se ha seleccionado una opción correcta \n");
					break;
			}
		}		
	}
}
