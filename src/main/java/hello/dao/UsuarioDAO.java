/**
 * 
 */
package hello.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import hello.Application;
import hello.clases.Age;
import hello.clases.Bitacora;
import hello.clases.Diabetes;
import hello.clases.DiabetesType;
import hello.clases.InsulinaType;
import hello.clases.Usuario;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author RICA
 *
 */
@Repository
public class UsuarioDAO {

	//JdbcTemplate template;
	/*private static DataSource dataSource = Application.dataSource();
	 JdbcTemplate template = new JdbcTemplate(dataSource);

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	*/
	 //@Autowired
	// JdbcTemplate jdbcTemplate;
	
	private static DataSource dataSource = Application.dataSource();
	 JdbcTemplate template = new JdbcTemplate(dataSource);

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
public Usuario save(Usuario usr){
	
	//java.util.Date utilDate = new java.util.Date();
	//java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	//p.setDate(sqlDate);
	Usuario newUser =  new Usuario();
	String sql=null;
	int valo = 0, id=0;
    sql = "SELECT count(*) FROM usuario WHERE usuario = ? and password =?";
    boolean result = false;
    int count = template.queryForObject(sql, new Object[] { usr.getUsuario(), usr.getPassword() }, Integer.class);
    if (count > 0) {
      result = true;
    }
	if (result == false) {
		sql="insert into usuario(usuario,password,tipodiabetes,edad, sexo, fechadiagnosti,ubicacion) "
				+ "values('"+usr.getUsuario()+"',"
						    + "'"+usr.getPassword()+"',"
						    + ""+usr.getTipodiabetes()+","
						    + ""+usr.getEdad()+","
						    + "'"+usr.getSexo()+"',"
						    + "'"+usr.getFechadiagnostico()+"',"
						    + "'"+usr.getUbicacion()+"')";
		 valo = 	template.update(sql);
		 System.out.println("valo ->>> "+ valo);
		 
		 id= getUserId( usr.getUsuario(), usr.getPassword());
		 
		 newUser.setIdusuario(id);
		 newUser.setUsuario(usr.getUsuario());
		 newUser.setPassword(usr.getPassword());
		 newUser.setTipodiabetes(usr.getTipodiabetes());
		 
	}	
    try {
		template.getDataSource().getConnection().close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	
	
	return newUser;
	
	
}
	
public int countPeople(String sex) {
	String sql=null;
	String sexo = sex;
	sql = "SELECT count(*) FROM usuario WHERE sexo = ? ";
	int count = template.queryForObject(sql, new Object[] { sexo }, Integer.class);
	
	return count;
}
	
public int saveBitac(Bitacora bita, int iduser){
		
		//java.util.Date utilDate = new java.util.Date();
		//java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		//p.setDate(sqlDate);
		String sql=null;
		int valo = 0;
		bita.setIduser(iduser);
			sql="INSERT INTO bitacora(glucemia,insulina,tipoinsulina,fecharegist,horaregist, iduser) "
					+ "values('"+bita.getGlucemia()+"',"
							    + "'"+bita.getInsulina()+"',"
							    + ""+bita.getTipoinsulina()+","
							    + "'"+bita.getFecharegist()+"',"
							    + "'"+bita.getHoraregist()+"',"
							    + "'"+bita.getIduser()+"')";
			 valo = 	template.update(sql);
		
	    try {
			template.getDataSource().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		return valo;
		
		
	}
	
	
public boolean isUserExists(String usuario, String password) {
    String sql = "SELECT count(*) FROM usuario WHERE usuario = ? and password =?";
    boolean result = false;
    int count = template.queryForObject(sql, new Object[] { usuario, password }, Integer.class);
    if (count > 0) {
      result = true;
    }
    
    try {
		template.getDataSource().getConnection().close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return result;
}

public int getUserId(String usuario, String password) {
	int id =0;
    String sql = "SELECT idusuario FROM usuario WHERE usuario = ? and password =?";
   
     id = template.queryForObject(sql, new Object[] { usuario, password }, Integer.class);

    
    try {
		template.getDataSource().getConnection().close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return id;
}

public String getUserName(int iduser) {
	String name =null;
    String sql = "SELECT usuario FROM usuario WHERE idusuario = ? ";
   
     name = template.queryForObject(sql, new Object[] { iduser }, String.class);

    
    try {
		template.getDataSource().getConnection().close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return name;
	}

public List<Bitacora> getBitaRegisters(String nombre, String date) {
	String sql = "";
	
	if (!"".equals(date))
		sql = "select bitacora.glucemia, bitacora.insulina, bitacora.fecharegist, bitacora.horaregist, tipoinsulina.nombre , usuario.usuario from bitacora INNER JOIN tipoinsulina ON bitacora.tipoinsulina = tipoinsulina.idtinsul INNER JOIN  usuario ON bitacora.iduser = usuario.idusuario where usuario.usuario = '"+nombre+"' and fecharegist='"+date+"'";
	else 
		sql = "select bitacora.glucemia, bitacora.insulina, bitacora.fecharegist, bitacora.horaregist, tipoinsulina.nombre , usuario.usuario from bitacora INNER JOIN tipoinsulina ON bitacora.tipoinsulina = tipoinsulina.idtinsul INNER JOIN  usuario ON bitacora.iduser = usuario.idusuario where usuario.usuario = '"+nombre+"'";
	
	List<Bitacora> listaRegis = new ArrayList<Bitacora>();
	
	listaRegis =template.query(sql, new RowMapper<Bitacora>() {
		public Bitacora mapRow (ResultSet result, int rowNum) throws SQLException{
			Bitacora bitac = new Bitacora(result.getInt("glucemia"), result.getInt("insulina"), result.getDate("fecharegist"), result.getTimestamp("horaregist"), result.getString("nombre"), result.getString("usuario"));
			return bitac;
		}
	});
	
	return listaRegis;
}

public List<Age> getAgeRanges(){
	List<Age> listAges = new ArrayList<Age>();
	
	String sql="SELECT\r\n" + 
			"    SUM(IF(edad < 20,1,0)) as 'Under 20',\r\n" + 
			"    SUM(IF(edad BETWEEN 20 and 29,1,0)) as '20 - 29',\r\n" + 
			"    SUM(IF(edad BETWEEN 30 and 39,1,0)) as '30 - 39',\r\n" + 
			"    SUM(IF(edad BETWEEN 40 and 49,1,0)) as '40 - 49',\r\n" + 
			"	SUM(IF(edad BETWEEN 50 and 59,1,0)) as '50 - 59',\r\n" + 
			"    SUM(IF(edad BETWEEN 60 and 69,1,0)) as '60 - 69',\r\n" + 
			"    SUM(IF(edad BETWEEN 70 and 79,1,0)) as '70 - 79',\r\n" + 
			"    SUM(IF(edad BETWEEN 80 and 89,1,0)) as '80 - 89'\r\n" + 
			"FROM usuario";
	listAges = template.query(sql, new RowMapper<Age>() {
		public Age mapRow (ResultSet result, int rowNum) throws SQLException{
			Age ages = new Age( String.valueOf(result.getInt("Under 20")), 
					String.valueOf(result.getInt("20 - 29")), 
					String.valueOf(result.getInt("30 - 39")), 
					String.valueOf(result.getInt("40 - 49")), 
					String.valueOf(result.getInt("50 - 59")), 
					String.valueOf(result.getInt("60 - 69")),
					String.valueOf(result.getInt("70 - 79")),
					String.valueOf(result.getInt("80 - 89")));
			return ages;
		}
	});
	
	return listAges;
}


public List<Diabetes> getDiabetesType(){
	List<Diabetes> listDiabeType = new ArrayList<Diabetes>();
	
	String sql="SELECT\r\n" + 
			"    COUNT(CASE WHEN tipoinsulina LIKE '1' THEN 1 END) AS Humalog_Novolog_Apidra,\r\n" + 
			"    COUNT(CASE WHEN tipoinsulina LIKE '2' THEN 1 END) AS Regular_R,\r\n" + 
			"    COUNT(CASE WHEN tipoinsulina LIKE '3' THEN 1 END) AS NPH_N,\r\n" + 
			"     COUNT(CASE WHEN tipoinsulina LIKE '4' THEN 1 END) AS Lantus_Levemir\r\n" + 
			"FROM bitacora";
	listDiabeType = template.query(sql, new RowMapper<Diabetes>() {
		public Diabetes mapRow (ResultSet result, int rowNum) throws SQLException{
			Diabetes diabetes = new Diabetes( String.valueOf(result.getInt("Humalog_Novolog_Apidra")), 
					String.valueOf(result.getInt("Regular_R")), 
					String.valueOf(result.getInt("NPH_N")), 
					String.valueOf(result.getInt("Lantus_Levemir")));
			return diabetes;
		}
	});
	
	return listDiabeType;
}

public List<DiabetesType> getDiabetesT(){
	List<DiabetesType> listDiabeType = new ArrayList<DiabetesType>();
	
	String sql="SELECT * FROM tipodiabetes";
	listDiabeType = template.query(sql, new RowMapper<DiabetesType>() {
		public DiabetesType mapRow (ResultSet result, int rowNum) throws SQLException{
			DiabetesType diabetes = new DiabetesType( String.valueOf(result.getInt("id")), 
					result.getString("nombre"));
			return diabetes;
		}
	});
	
	return listDiabeType;
}


public List<InsulinaType> getInsuline(){
	List<InsulinaType> listInsulinaType = new ArrayList<InsulinaType>();
	
	String sql="SELECT * FROM tipoinsulina";
	listInsulinaType = template.query(sql, new RowMapper<InsulinaType>() {
		public InsulinaType mapRow (ResultSet result, int rowNum) throws SQLException{
			InsulinaType insulina = new InsulinaType( String.valueOf(result.getInt("idtinsul")), 
					result.getString("nombre"));
			return insulina;
		}
	});
	
	return listInsulinaType;
}


public List<Diabetes> getDiabetesTypeStat(){
	List<Diabetes> listDiabeType = new ArrayList<Diabetes>();
	
	String sql="SELECT\r\n" + 
			"    COUNT(CASE WHEN tipodiabetes LIKE '1' THEN 1 END) AS Type_1_diabetes,\r\n" + 
			"    COUNT(CASE WHEN tipodiabetes LIKE '2' THEN 1 END) AS Type_2_diabetes,\r\n" + 
			"    COUNT(CASE WHEN tipodiabetes LIKE '3' THEN 1 END) AS Prediabetes,\r\n" + 
			"     COUNT(CASE WHEN tipodiabetes LIKE '4' THEN 1 END) AS Gestational_diabetes\r\n" + 
			"FROM usuario";
	listDiabeType = template.query(sql, new RowMapper<Diabetes>() {
		public Diabetes mapRow (ResultSet result, int rowNum) throws SQLException{
			Diabetes diabetes = new Diabetes( String.valueOf(result.getInt("Type_1_diabetes")), 
					String.valueOf(result.getInt("Type_2_diabetes")), 
					String.valueOf(result.getInt("Prediabetes")), 
					String.valueOf(result.getInt("Gestational_diabetes")));
			return diabetes;
		}
	});
	
	return listDiabeType;
}


}



