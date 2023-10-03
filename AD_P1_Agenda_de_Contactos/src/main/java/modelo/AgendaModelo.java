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
	 * Constructor para asegurarse de que si no existe el archivo se cree
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
	    try {
	        // abrimos el fichero original en modo lecturael fichero en modo lectura
	        MyObjectInputStream objInpStream = new MyObjectInputStream(new FileInputStream(new File(ruta)));

	        // creamos el archivo temporal para escribir los objetos modificados
	        MyObjectOutputStream objOutStream = new MyObjectOutputStream(new FileOutputStream(new File("contactos.tmp")));

	        // leemos todos los objetos del fichero original
	        List<Contacto> contactos = new ArrayList<>();
	        while (true) {
	            try {
	                Contacto contacto = (Contacto) objInpStream.readObject();
	                if (contacto.getUsuario().equals(usuario)) {
	                    // marcamos el objeto como "eliminado virtualmente" (establece el UUID a 0)
	                    contacto.setUsuario(new UUID(0, 0));
	                }
	                // añadimos el contacto a una lista
	                contactos.add(contacto);
	            } catch (EOFException e) {
	                break; // fin del fichero
	            }
	        }

	        // escribimos los objetos en el archivo temporal
	        for (Contacto contacto : contactos) {
	            objOutStream.writeObject(contacto);
	        }

	        // cerramos los flujos de entrada y salida
	        objInpStream.close();
	        objOutStream.close();

	        // borramos el archivo original y renombramos el archivo temporal al original
	        File originalFile = new File(ruta);
	        File tempFile = new File("contactos.tmp");
	        if (originalFile.delete() && tempFile.renameTo(originalFile)) {
	            return true;
	        } else {
	            return false;
	        }

	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    return false; // si hay algún error, devuelve false
	}
}
