package hello.clases;

import java.sql.Date;

public class Usuario {
	
	int idusuario;
	String usuario;
	String password;
	int tipodiabetes;
	int edad;
	char sexo;
	Date fechadiagnostico;
	String ubicacion;
	
	
	public Usuario(String usuario, String password, int tipodiabetes, int edad, char sexo, Date fechadiagnostico,
			String ubicacion) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.tipodiabetes = tipodiabetes;
		this.edad = edad;
		this.sexo = sexo;
		this.fechadiagnostico = fechadiagnostico;
		this.ubicacion = ubicacion;
	}
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getTipodiabetes() {
		return tipodiabetes;
	}
	public void setTipodiabetes(int tipodiabetes) {
		this.tipodiabetes = tipodiabetes;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public Date getFechadiagnostico() {
		return fechadiagnostico;
	}
	public void setFechadiagnostico(Date fechadiagnostico) {
		this.fechadiagnostico = fechadiagnostico;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	

}
