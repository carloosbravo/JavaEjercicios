package objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Mascota implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String nombre;
	private String especie;
	private String sexo;
	private String edad;
	ArrayList<Cita> citas;

	public Mascota(String id, String nombre, String especie, String sexo, String edad, ArrayList<Cita> citas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.especie = especie;
		this.sexo = sexo;
		this.edad = edad;
		this.citas = citas;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEdad() {
		return edad;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public ArrayList<Cita> getCitas() {
		return citas;
	}

	public void setCitas(ArrayList<Cita> citas) {
		this.citas = citas;
	}

}
