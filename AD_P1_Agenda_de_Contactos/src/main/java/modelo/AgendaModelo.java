package modelo;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import lombok.*;

@Data
@AllArgsConstructor
public class AgendaModelo implements AgendaInterface {
	/**
	 * Ruta del fichero donde se almacenará la información
	 */
	private String ruta;

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
	public Contacto buscarPorUUID(UUID usuario) {
		return obtenerAgenda().stream()
				.filter(x -> x.getUsuario().equals(usuario))
				.findAny()
				.orElse(null);
	}

	@Override
	public ArrayList<Contacto> buscarPorNombre(String nombre) {
	    return obtenerAgenda().stream()
	            .filter(x -> x.getNombre().startsWith(nombre))
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
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean eliminarContacto(UUID usuario) {
		// TODO Auto-generated method stub
		return false;
	}

}
