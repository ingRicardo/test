package hello;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Application {
	@Bean(name = "dsMysql2")
	public static DataSource dataSourceTest() {
	    return DataSourceBuilder
	        .create()
	        .username("root")
	        .password("")
	        .url("jdbc:mysql://localhost/test")
	        .driverClassName("com.mysql.jdbc.Driver")
	        .build();
	} 
	 
	
	@Bean(name = "dsMysql")
	public static DataSource dataSource() {
	    return DataSourceBuilder
	        .create()
	        .username("root")
	        .password("")
	        .url("jdbc:mysql://localhost/diabetes")
	        .driverClassName("com.mysql.jdbc.Driver")
	        .build();
	} 
	 
	@Primary
	@Bean(name = "jdbcMysql")
    @Autowired
    public JdbcTemplate slaveJdbcTemplate(@Qualifier("dsMysql") DataSource dsMysql) {
        return new JdbcTemplate(dsMysql);
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
