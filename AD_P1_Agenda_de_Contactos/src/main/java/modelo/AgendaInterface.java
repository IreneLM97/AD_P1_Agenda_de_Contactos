package modelo;

import java.util.ArrayList;
import java.util.UUID;

public interface AgendaInterface {
	/**
	 * Método para agregar un contacto a la agenda
	 * @param contacto
	 * @return true si se ha podido realizar, false en caso contrario
	 */
	public boolean agregarContacto(Contacto contacto);
	
	/**
	 * Método para buscar un contacto por UUID
	 * @param usuario
	 * @return contacto encontrado
	 */
	public Contacto buscarPorUUID(UUID usuario);
	
	/**
	 * Método para buscar un listado de contactos por nombre
	 * @param nombre
	 * @return listado de contactos
	 */
	public ArrayList<Contacto> buscarPorNombre(String nombre);
	
	/**
	 * Método para mostrar todos los contactos de la agenda
	 * @return listado de contactos
	 */
	public ArrayList<Contacto> obtenerAgenda();
	
	/**
	 * Método para eliminar un contacto de la agenda
	 * @param usuario
	 * @return true si se ha podido realizar, false en caso contrario
	 */
	public boolean eliminarContacto(UUID usuario);

}
