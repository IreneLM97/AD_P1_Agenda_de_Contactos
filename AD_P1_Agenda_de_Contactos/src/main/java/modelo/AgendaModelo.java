package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

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
		Contacto contactoEncontrado = null;
		try {
			MyObjectInputStream objInpStream = new MyObjectInputStream(new FileInputStream(new File(ruta)));

			Object obj;
			while ((obj = objInpStream.readObject()) != null) {
				if (obj instanceof Contacto) {
					Contacto contacto = (Contacto) obj;
					if (contacto.getUsuario().equals(usuario)) {
						contactoEncontrado = contacto;
						break; // Encontramos el contacto, salimos del bucle
					}
				}
			}
		} catch (EOFException e) {
			// Fin del archivo alcanzado, no se encontró el contacto
			return null;
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return contactoEncontrado;
	}

	@Override
	public ArrayList<Contacto> buscarPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
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
