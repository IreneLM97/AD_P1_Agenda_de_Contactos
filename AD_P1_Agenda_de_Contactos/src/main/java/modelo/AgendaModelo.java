package modelo;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import lombok.*;

@Data
public class AgendaModelo implements AgendaInterface {
	/**
	 * Ruta del fichero donde se almacenará la información
	 */
	private String ruta;
	
	/**
	 * Constructor para comprobar si existe el fichero y, si no existe, crearlo
	 * @param ruta
	 */
	public AgendaModelo(String ruta) {
		this.ruta = ruta;
		File fichero = new File(ruta);
		if(!fichero.exists()) {
			try {
				fichero.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Contacto buscarPorUUID(UUID usuario) {
		// buscamos los contactos por ese UUID que no hayan sido eliminados (con UUID valor 0)
		return obtenerAgenda().stream()
				.filter(x -> x.getUsuario().equals(usuario) && !x.getUsuario().equals(new UUID(0, 0)))
				.findAny()
				.orElse(null);
	}

	@Override
	public ArrayList<Contacto> buscarPorNombre(String nombre) {
		// buscamos los contactos por ese nombre que no hayan sido eliminados (con UUID valor 0)
	    return obtenerAgenda().stream()
	            .filter(x -> x.getNombre().startsWith(nombre) && !x.getUsuario().equals(new UUID(0, 0)))
	            .collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public ArrayList<Contacto> obtenerAgenda() {
		ArrayList<Contacto> contactos = new ArrayList<Contacto>();
		try {
			MyObjectInputStream objInpStream = new MyObjectInputStream(new FileInputStream(new File(ruta)));

			while (true) {
				try {
					Contacto contacto = (Contacto) objInpStream.readObject();
					contactos.add(contacto);
				} catch (EOFException e) {
					objInpStream.close();
					return contactos;
				}
			}

		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public boolean agregarContacto(Contacto contacto) {
		try {
			MyObjectOutputStream objOutStream = new MyObjectOutputStream(new FileOutputStream(new File(ruta), true));
			objOutStream.writeObject(contacto);
			objOutStream.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean eliminarVirtualmenteContacto(UUID usuario) {
		// obtenemos todos los contactos de la agenda
		ArrayList<Contacto> contactos = obtenerAgenda();
		
		// si no existe ningún contacto con ese UUID o es 0 devolvemos false
		if(contactos.stream().filter(x -> x.getUsuario().equals(usuario)).count() == 0 || usuario.equals(new UUID(0, 0))) {
			return false;
		}
		
		// si existe un contacto con ese UUID lo cambiamos por UUID 0  
		contactos.stream()
			     .filter(x -> x.getUsuario().equals(usuario))
			     .forEach(contacto -> contacto.setUsuario(new UUID(0, 0)));
		
		// escribimos en el fichero los contactos actualizados
        MyObjectOutputStream objOutStream;
		try {
			objOutStream = new MyObjectOutputStream(new FileOutputStream(new File(ruta)));
	        for (Contacto contacto : contactos) objOutStream.writeObject(contacto);
	        objOutStream.close();
		} catch (Exception e) {
			return false;  // ha habido problemas con la escritura
		} 
        return true;
	}
}
