package hello;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


public class CustomDao {
//JdbcTemplate template;
private static DataSource dataSource = Application.dataSource();
 JdbcTemplate template = new JdbcTemplate(dataSource);

public void setTemplate(JdbcTemplate template) {
	this.template = template;
}
public int save(Customer p){
	System.out.println("customer" +p);
	java.util.Date utilDate = new java.util.Date();
	java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	p.setDate(sqlDate);
	String sql="insert into customer(name,email,created_date) values('"+p.getName()+"','"+p.getEmail()+"','"+p.getDate()+"')";
	int res = template.update(sql);
	
	
	try {
		template.getDataSource().getConnection().close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	
	return res;
}
public int update(Customer p){
	
	String sql="update customer set name='"+p.getName()+"', email='"+p.getEmail()+"', created_date='"+p.getDate()+"' where id="+p.getId()+"";
	System.out.println("->>>_"+p.getName() + "Updated");
	int res = template.update(sql);
	
	try {
		template.getDataSource().getConnection().close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	
	return res;
}
public int delete(int id){
	String sql="delete from customer where id="+id+"";
	
	int res = template.update(sql);
	
	try {
		template.getDataSource().getConnection().close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	
	return res;
}
public Customer getEmpById(int id){
	String sql="select * from customer where id=?";
	Customer custom = new Customer();
	
	
	custom = template.queryForObject(sql, new Object[]{id}, new RowMapper<Customer>(){
		public Customer mapRow(ResultSet rs, int row) throws SQLException {
			Customer e=new Customer();
			e.setId(rs.getInt(1));
			e.setName(rs.getString(2));
			e.setEmail(rs.getString(3));
			e.setDate(rs.getDate(4));
			return e;
			}
		});
	
	
	try {
		template.getDataSource().getConnection().close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
 
	
	return custom;
}

public List<Customer> getCustomers(){
	
	
	String sql = "select * from customer";	
	template.execute(sql);
	System.out.println("connection "+ template.getDataSource());
	
	 List<Customer> listContact = template.query(sql, new RowMapper<Customer>() {
		 
            public Customer mapRow(ResultSet result, int rowNum) throws SQLException {
            	Customer customer = new Customer();
            	customer.setId(result.getInt("id"));
            	customer.setName(result.getString("name"));
            	customer.setEmail(result.getString("email"));
            	customer.setDate(result.getDate("created_date"));
                 
                return customer;
            }
             
        });
	
	 try {
			template.getDataSource().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	 
	 return listContact;
}
}
