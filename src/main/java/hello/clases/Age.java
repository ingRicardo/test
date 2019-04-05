/**
 * 
 */
package hello.clases;

/**
 * @author RICA
 *
 */
public class Age {

	/**
	 * 
	 */
	
	private String under20;
	private String b2029;
	private String b3039;
	private String b4049;
	private String b5059;
	private String b6069;
	private String b7079;
	private String b8089;
	
	
	/**
	 * @param under20
	 * @param b2029
	 * @param b3039
	 * @param b4049
	 * @param b5059
	 * @param b6069
	 * @param b7079
	 * @param b8089
	 */
	public Age(String under20, String b2029, String b3039, String b4049, String b5059, String b6069, String b7079,
			String b8089) {
		super();
		this.under20 = under20;
		this.b2029 = b2029;
		this.b3039 = b3039;
		this.b4049 = b4049;
		this.b5059 = b5059;
		this.b6069 = b6069;
		this.b7079 = b7079;
		this.b8089 = b8089;
	}


	public String getUnder20() {
		return under20;
	}


	public void setUnder20(String under20) {
		this.under20 = under20;
	}


	public String getB2029() {
		return b2029;
	}


	public void setB2029(String b2029) {
		this.b2029 = b2029;
	}


	public String getB3039() {
		return b3039;
	}


	public void setB3039(String b3039) {
		this.b3039 = b3039;
	}


	public String getB4049() {
		return b4049;
	}


	public void setB4049(String b4049) {
		this.b4049 = b4049;
	}


	public String getB5059() {
		return b5059;
	}


	public void setB5059(String b5059) {
		this.b5059 = b5059;
	}


	public String getB6069() {
		return b6069;
	}


	public void setB6069(String b6069) {
		this.b6069 = b6069;
	}


	public String getB7079() {
		return b7079;
	}


	public void setB7079(String b7079) {
		this.b7079 = b7079;
	}


	public String getB8089() {
		return b8089;
	}


	public void setB8089(String b8089) {
		this.b8089 = b8089;
	}


	public Age() {
		// TODO Auto-generated constructor stub
	}

}

/*
 * import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CustomerRowMapper implements RowMapper
{
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setCustId(rs.getInt("CUST_ID"));
		customer.setName(rs.getString("NAME"));
		customer.setAge(rs.getInt("AGE"));
		return customer;
	}
	
}
 * */
