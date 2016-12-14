package com.my.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DbUtils {
	/**Get the file
	 * @return the file
	 */
	private static Properties getprop(){
		Properties prop = null;
		prop = new Properties();
		InputStream fis = DbUtils.class.getClassLoader().getResourceAsStream("");
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	/** get the dataSource which is for spring jdbc 
	 * @return
	 */
	public static DriverManagerDataSource getDataSource(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = getprop();
		DriverManagerDataSource ds = new DriverManagerDataSource(prop.getProperty("url"), prop);
		
		return ds;
	}
	
	/** for spring jdbc template 
	 * @return
	 */
	public static JdbcTemplate getTpl(){
		return new JdbcTemplate(getDataSource());
	}
	
	
	
	
	
}
