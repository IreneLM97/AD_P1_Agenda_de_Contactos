package modelo;

import java.io.Serializable;
import java.util.UUID;

import lombok.*;

@Data
@AllArgsConstructor
public class Contacto implements Serializable {
	private UUID usuario;
	private String nombre;
	private String telefono;
	private int edad;
	
	public Contacto(String nombre, String telefono, int edad) {
		this.usuario = UUID.randomUUID();
		this.nombre = nombre;
		this.telefono = telefono;
		this.edad = edad;
	}
	
	@Override
	public String toString() {
		return usuario + ", " + nombre + ", " + telefono + ", " + edad;
	}
}
