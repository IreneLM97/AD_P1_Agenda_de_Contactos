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
	public boolean eliminarVirtualmenteContacto(UUID uuidAEliminar) {
        try {
            // Abre el fichero en modo lectura y escritura
        	MyObjectOutputStream objOutStream = new MyObjectOutputStream(new FileOutputStream(new File(ruta)));

        	MyObjectInputStream objInpStream = new MyObjectInputStream(new FileInputStream(new File(ruta)));

            // Lee todos los objetos del fichero original
            List<Contacto> contactos = new ArrayList<>();
            while (true) {
                try {
                    Contacto contacto = (Contacto) objInpStream.readObject();
                    if (contacto.getUsuario().equals(uuidAEliminar)) {
                        // Marca el objeto como "eliminado virtualmente" (establece el UUID a 0)
                        contacto.setUsuario(new UUID(0, 0));
                    }
                    contactos.add(contacto);
                } catch (EOFException e) {
                    break; // Fin del fichero
                }
            }

            // Escribe los objetos modificados en el fichero temporal
            for (Contacto contacto : contactos) {
            	objOutStream.writeObject(contacto);
            }

            // Cierra los flujos y renombra el fichero temporal al original
            objOutStream.close();
            objInpStream.close();


            File originalFile = new File("contactos.dat");
            File tempFile = new File("contactos.tmp");
            if (tempFile.renameTo(originalFile)) {
                return true;
            } else {
               return false;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
		return true;
    }
}
