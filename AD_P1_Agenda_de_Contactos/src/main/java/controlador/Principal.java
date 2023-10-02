package controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import modelo.*;
import vista.*;
import constantes.color.*;

/**
 * Clase principal del programa
 * 
 * @author Sergio Ania Lázaro - Ricardo de Antonio Aguirre - Irene López Melero
 * @version 1.0
 * @since 2023-10-02
 */

public class Principal {

	public static void main(String[] args) {
		// cargamos el properties que contiene los Strings
		Properties prop = IO.cargarProperties();	
		
		// creamos una agenda 
		AgendaModelo agenda = new AgendaModelo(prop.getProperty("ruta.contactos.dat"));
		Contacto contacto;
		
		while(true) {
			// mostramos el menú al usuario
			VistaUsuario.mostrarMenu();
			
			// recogemos la opción marcada
			int opcion = VistaUsuario.solicitarOpcion();
			switch(opcion) {
			
				// buscar por código
				case 1: 
					contacto = agenda.buscarPorUUID(VistaUsuario.solicitarUUID());
					if(contacto == null) { // no se encuentra contacto
						VistaUsuario.mostrarMsg(Colores.ROJO + prop.getProperty("error.UUID") + Colores.RESET);
					} else { // se encuentra contacto
						VistaUsuario.mostrarMsg(Colores.AMARILLO + contacto.toString() + Colores.RESET);
					}
					break;
					
				// buscar por nombre
				case 2: 
					List<Contacto> contactosEncontrados = agenda.buscarPorNombre(VistaUsuario.solicitarNombre());
					contactosEncontrados.stream()
										.forEach(x -> VistaUsuario.mostrarMsg(Colores.AMARILLO + x + Colores.RESET));
					break;
					
				// mostrar agenda completa
				case 3: 
					ArrayList<Contacto> contactos = agenda.obtenerAgenda();
					if(contactos == null) {  // no se pueden mostrar los contactos
						VistaUsuario.mostrarMsg(Colores.ROJO + prop.getProperty("error.accesoContactos") + Colores.RESET);
					} else {  // mostramos los contactos
						VistaUsuario.mostrarAgenda(agenda);
					}
					break;
					
				// añadir contacto
				case 4: 
					contacto = VistaUsuario.solicitarContacto();
					VistaUsuario.mostrarMsg(agenda.agregarContacto(contacto) ? Colores.VERDE + prop.getProperty("msg.agregado") + Colores.RESET : Colores.ROJO + prop.getProperty("error.añadido") + Colores.RESET);
					break;
					
				// eliminar contacto
				case 5: 
					break;
					
				// salir del menú
				case 6: 
					VistaUsuario.mostrarMsg(Colores.AMARILLO + prop.getProperty("msg.salida.menu") + Colores.RESET);
					return;
					
				// opción inválida
				default: 
					VistaUsuario.mostrarMsg(Colores.ROJO +  prop.getProperty("error.opcion.incorrecta") + Colores.RESET);
					break;
			}
		}		
	}
}
