package com.tid.dao;

import java.net.URL;
import java.util.List;

import com.tid.pojo.Client;
import com.tid.util.ClientParser;


/**
 * Management clients
 * 
 * @author fdelatorre
 *
 */
public class ClientDao {
	
	String clientsFile;
	String server;
	String user;
	String password;


	public ClientDao(String clientsFile) {
		
		//Init clients' file
		this.clientsFile = clientsFile;	
	}
	
	public ClientDao(String clientsFile, String server, String user, String password) {
		
		//Get clients file from web system
		this.clientsFile = clientsFile;	
		
		//TODO Connect with db server
		this.server = server;
		this.user = user;
		this.password = password;
		
	}
	
	
	public List<Client> getClients() {
		
		//Get all clients
		List<Client> clients = ClientParser.parse(clientsFile);
		
		return clients;
	}
	
	public Client getClient(String name) {
		
		Client client = null;
		
		List<Client> clients = getClients();
		
		for (Client cli:clients) {
			if (cli.getName().equals(name)) {
				client = cli;
				break;
			}
		}
		
		return client;
		
	}
	
	public int setClient(Client client) {
		
		ClientParser.update(clientsFile, client);
		
		return 0;
		
	}	


	public int createClient(Client client) {
		
		//TODO Add code
		return 0;
		
	}	
	
	public int deleteClient(Client client) {
		
		//TODO Add code
		return 0;
		
	}	

	
	/**
	 * Get path to clients' file
	 * 
	 * @return
	 */
	public static String getConfigFile() {
		   String configFile = "";		   	   		  
		   URL classURL = ClientDao.class.getProtectionDomain().getCodeSource().getLocation();	   
		   configFile = classURL.getPath();
		   //Extract the lib name from the path
		   configFile = configFile.substring(0, configFile.length()-15);
		   configFile += "/" + "client-config.xml";
		   
		   return configFile;
		   
	   }	
	
}
