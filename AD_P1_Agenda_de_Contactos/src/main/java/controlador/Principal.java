package controlador;

import java.util.ArrayList;
import java.util.Properties;
import java.util.UUID;

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
		//Cargamos el properties que contiene los Strings
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
				case 1: // buscar por código
					contacto = agenda.buscarPorUUID(VistaUsuario.solicitarUUID());
					if(contacto != null) {
						VistaUsuario.mostrarMsg(Colores.AMARILLO +  contacto.toString() + Colores.RESET);;
					}else {
						VistaUsuario.mostrarMsg(Colores.ROJO + prop.getProperty("error.UUID")+ Colores.RESET);
					}
					break;
					
				case 2: // buscar por nombre
					break;
					
				case 3: // mostrar agenda completa
					ArrayList<Contacto> contactos = agenda.obtenerAgenda();
					if(contactos == null) {  // no se pueden mostrar los contactos
						VistaUsuario.mostrarMsg(Colores.ROJO + prop.getProperty("error.accesoContactos") + Colores.RESET);
					} else {  // mostramos los contactos
						VistaUsuario.mostrarAgenda(agenda);
					}
					break;
					
				case 4: // añadir contacto
					contacto = VistaUsuario.solicitarContacto();
					String respuesta = agenda.agregarContacto(contacto) ? Colores.VERDE + prop.getProperty("msg.añadido") + Colores.RESET : Colores.ROJO + prop.getProperty("error.añadido") + Colores.RESET;
					VistaUsuario.mostrarMsg(respuesta);
					break;
					
				case 5: // eliminar contacto
					break;
					
				case 6: // borrar contacto
					VistaUsuario.mostrarMsg(Colores.AMARILLO + prop.getProperty("msg.añadido") + Colores.RESET);
					return;
					
				default: // opción inválida
					VistaUsuario.mostrarMsg(Colores.ROJO +  prop.getProperty("error.opcion.incorrecta") + Colores.RESET);
					break;
			}
		}		
	}
}
