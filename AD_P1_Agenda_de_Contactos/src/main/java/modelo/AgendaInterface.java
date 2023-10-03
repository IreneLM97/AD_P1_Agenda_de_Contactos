package modelo;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Interfaz con los métodos necesarios para gestionar la agenda
 */
public interface AgendaInterface {
	
	/**
	 * Método para buscar un contacto por UUID
	 * @param usuario (UUID)
	 * @return contacto encontrado o null si no encuentra
	 */
	public Contacto buscarPorUUID(UUID usuario);
	
	/**
	 * Método para buscar un listado de contactos por nombre
	 * @param nombre
	 * @return listado de contactos
	 */
	public ArrayList<Contacto> buscarPorNombre(String nombre);
	
	/**
	 * Método para obtener todos los contactos de la agenda
	 * @return listado de contactos o null si hay problemas con la lectura
	 */
	public ArrayList<Contacto> obtenerAgenda();
	
	/**
	 * Método para agregar un contacto a la agenda
	 * @param contacto
	 * @return true si se ha podido realizar, false en caso contrario
	 */
	public boolean agregarContacto(Contacto contacto);
	
	/**
	 * Método para eliminar un contacto de la agenda dado su UUID
	 * @param usuario (UUID)
	 * @return true si se ha podido realizar, false en caso contrario
	 */
	public boolean eliminarContacto(UUID usuario);

}
