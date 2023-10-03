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

	/**
	 * Método principal del programa
	 * @param args
	 */
	public static void main(String[] args) {
		// cargamos el properties que contiene los Strings
		Properties prop = cargarProperties();	
		
		// creamos una agenda 
		AgendaModelo agenda = new AgendaModelo(prop.getProperty("ruta.contactos.dat"));
		Contacto contacto;
		
		do {
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
					} else { // se encuentra contacto -> se convierte a ArrayList para mostrarlo con diseño en la consola
						VistaUsuario.mostrarContactos(new ArrayList<Contacto>(java.util.Arrays.asList(contacto)));
					}
					break;
					
				// buscar por nombre
				case 2: 
					ArrayList<Contacto> contactosEncontrados = agenda.buscarPorNombre(VistaUsuario.solicitarNombre());
					if (contactosEncontrados.isEmpty()) { // no se encuentran contactos
					    VistaUsuario.mostrarMsg(Colores.ROJO + prop.getProperty("error.nombreNoEncontrado") + Colores.RESET);
					} else { // se encuentran contactos
						VistaUsuario.mostrarContactos(contactosEncontrados);
					}
					break;
					
				// mostrar agenda completa
				case 3: 
					ArrayList<Contacto> contactos = agenda.obtenerAgenda();
					if(contactos == null) {  // no se pueden mostrar los contactos
						VistaUsuario.mostrarMsg(Colores.ROJO + prop.getProperty("error.accesoContactos") + Colores.RESET);
					} else {  // mostramos los contactos
						VistaUsuario.mostrarContactos(contactos);
					}
					break;
					
				// añadir contacto
				case 4: 
					contacto = VistaUsuario.solicitarContacto(); 
					// mostramos mensaje en función de si se pudo agregar o no
					VistaUsuario.mostrarMsg(agenda.agregarContacto(contacto) ? Colores.VERDE + prop.getProperty("msg.agregado") + Colores.RESET : Colores.ROJO + prop.getProperty("error.agregado") + Colores.RESET);
					break;
					
				// eliminar contacto
				case 5: 
					// intentamos eliminar y mostramos mensaje en función de si se pudo agregar o no
					VistaUsuario.mostrarMsg(agenda.eliminarContacto(VistaUsuario.solicitarUUID()) ? Colores.VERDE + prop.getProperty("msg.eliminado") + Colores.RESET : Colores.ROJO + prop.getProperty("error.eliminado") + Colores.RESET);
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
			
			// mostramos separador
			VistaUsuario.mostrarSeparador();
		} while (true);	
	}

	/**
	 * Carga el fichero de properties que se encuentra en la carpeta resources (por defecto eclipse sabe identificar esta carpeta)
	 * @return Properties
	 */
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
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
	}
}
