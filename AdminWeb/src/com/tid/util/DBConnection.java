package com.tid.util;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.java.sip.communicator.util.Logger;

import com.tid.Constants;
import com.tid.pojo.DBConfig;
import com.tid.service.impl.CenterService;

public class DBConnection {

	private static final Logger LOG = Logger.getLogger(DBConnection.class.getName());
	
	private DBConfig config ;
		
	public DBConnection() {
		config = getConfig();	
	}
	
	public String getHost() {
		return config.getHost();
	}

	
	public String getPort() {
		return config.getPort();
	}

	
	public String getName() {
		return config.getName();
	}

	
	public String getUser() {
		return config.getUser();
	}

	
	public String getPassword() {
		return config.getPassword();
	}

	/**
	 * Open a db connection
	 * 
	 * @return
	 */
	public Connection open() {
		
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			StringBuilder server = new StringBuilder("jdbc:mysql://");
			server.append(config.getHost());
			if (config.getPort()!=null && !"".equals(config.getPort())) {
				server.append(":").append(config.getPort());
			}
			server.append("/").append(config.getName());
											
			//Init db connection			
			connection = DriverManager.getConnection(server.toString(), config.getUser(), config.getPassword());			

		} catch (ClassNotFoundException e) {			
			connection = null;
			LOG.error("Error on database connection");
		} catch (SQLException e) {
			connection = null;
			LOG.error("Error on database connection");
		}
		
		return connection;
	}
	
	/**
	 * Close a db connection
	 * @param connection
	 */
	public void close(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			LOG.error("Error on database close");
		}
	}
	
	
	/**
	 * Get db's config
	 * 
	 * @return
	 */
	private DBConfig getConfig()  {
		   		   
		   //String configFile = System.getProperty("user.dir");
		   URL classURL = this.getClass().getProtectionDomain().getCodeSource().getLocation();	   
		   String configFile = classURL.getPath() + Constants.CONFIG_DB;
		   DBConfig config = DBConfigParser.parse(configFile);		   		   
		   return config;
		   
	   }
}
