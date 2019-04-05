/**
 * 
 */
package hello.clases;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author RICA
 *
 */
public class Bitacora {

	/**
	 * 
	 */
	int idregistro;
	int glucemia;
	int insulina;
	int tipoinsulina;
	Date fecharegist;
	Timestamp horaregist;
	int iduser;
	String tipoinsul;
	String usuario;
	
	
	public Bitacora(int glucemia, int insulina, Date fecharegist, Timestamp horaregist, String tipoinsul,
			String usuario) {
		super();
		this.glucemia = glucemia;
		this.insulina = insulina;
		this.fecharegist = fecharegist;
		this.horaregist = horaregist;
		this.tipoinsul = tipoinsul;
		this.usuario = usuario;
	}


	public Bitacora(int glucemia, int insulina, int tipoinsulina, Date fecharegist, Timestamp horaregist, int iduser) {
		super();
		this.glucemia = glucemia;
		this.insulina = insulina;
		this.tipoinsulina = tipoinsulina;
		this.fecharegist = fecharegist;
		this.horaregist = horaregist;
		this.iduser = iduser;
	}


	public Bitacora() {
		// TODO Auto-generated constructor stub
	}

	
	public String getTipoinsul() {
		return tipoinsul;
	}


	public void setTipoinsul(String tipoinsul) {
		this.tipoinsul = tipoinsul;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public int getIdregistro() {
		return idregistro;
	}


	public void setIdregistro(int idregistro) {
		this.idregistro = idregistro;
	}


	public int getGlucemia() {
		return glucemia;
	}


	public void setGlucemia(int glucemia) {
		this.glucemia = glucemia;
	}


	public int getInsulina() {
		return insulina;
	}


	public void setInsulina(int insulina) {
		this.insulina = insulina;
	}


	public int getTipoinsulina() {
		return tipoinsulina;
	}


	public void setTipoinsulina(int tipoinsulina) {
		this.tipoinsulina = tipoinsulina;
	}


	public Date getFecharegist() {
		return fecharegist;
	}


	public void setFecharegist(Date fecharegist) {
		this.fecharegist = fecharegist;
	}


	public Timestamp getHoraregist() {
		return horaregist;
	}


	public void setHoraregist(Timestamp horaregist) {
		this.horaregist = horaregist;
	}


	public int getIduser() {
		return iduser;
	}


	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	
	
	
	
}
