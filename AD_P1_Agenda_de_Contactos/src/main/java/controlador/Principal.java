package controlador;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
		Properties prop = cargarProperties();	
		
		// creamos una agenda 
		AgendaModelo agenda = new AgendaModelo(prop.getProperty("ruta.contactos.dat"));
		Contacto contacto;
		
		// mostramos el menú al usuario
		VistaUsuario.mostrarMenu();
		do {
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
					ArrayList<Contacto> contactosEncontrados = agenda.buscarPorNombre(VistaUsuario.solicitarNombre());
					if (contactosEncontrados.isEmpty()) {
					    VistaUsuario.mostrarMsg(Colores.ROJO + prop.getProperty("error.nombreNoEncontrado") + Colores.RESET);
					} else {
						VistaUsuario.mostrarAgenda(contactosEncontrados);
					}
					break;
					
				// mostrar agenda completa
				case 3: 
					ArrayList<Contacto> contactos = agenda.obtenerAgenda();
					if(contactos == null) {  // no se pueden mostrar los contactos
						VistaUsuario.mostrarMsg(Colores.ROJO + prop.getProperty("error.accesoContactos") + Colores.RESET);
					} else {  // mostramos los contactos
						VistaUsuario.mostrarAgenda(contactos);
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
			
			VistaUsuario.mostrarSeparador();
			VistaUsuario.mostrarMenu();
		} while (true);	
	}

	private static Properties cargarProperties() {
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
