package com.tid.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.java.sip.communicator.util.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import com.tid.pojo.Client;

public class ClientParser {
	
	private static final Logger LOG = Logger.getLogger(ClientParser.class.getName());
	
	/**
	 * Parse configuration file
	 * @param clientsFile
	 * @return
	 */
	public static List<Client> parse(String clientsFile) {
		
		List<Client> clients = new ArrayList<Client>();
		
		// Parse the input file to get a Document object
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
	        Document doc = db.parse (new File(clientsFile));
	        
	        // normalize text representation
            doc.getDocumentElement ().normalize ();
            
            //Get client nodes
            NodeList clientList = doc.getElementsByTagName("client");
            
            for(int i=0; i<clientList.getLength() ; i++){
            	
            	 Node clientNode = clientList.item(i);
                 if(clientNode.getNodeType() == Node.ELEMENT_NODE){
                	 
                	 //Create every Client object
                     Element clientElement = (Element)clientNode;
                     Client client = new Client();                     
                     
                     //NAME
                     
                     NodeList list = clientElement.getElementsByTagName("name");
                     Element element = (Element)list.item(0);
                     
                     NodeList textNameList = element.getChildNodes();
                     client.setName(((Node)textNameList.item(0)).getNodeValue().trim());   
                     
                     //DESCRIPTION
                     list = clientElement.getElementsByTagName("description");
                     element = (Element)list.item(0);
                     
                     NodeList txtList = element.getChildNodes();
                     client.setDescription(((Node)txtList.item(0)).getNodeValue().trim());
                   
                     //URL
                     list = clientElement.getElementsByTagName("url");
                     element = (Element)list.item(0);
                     
                     txtList = element.getChildNodes();
                     client.setUrl(((Node)txtList.item(0)).getNodeValue().trim());
                     
                     //USER
                     list = clientElement.getElementsByTagName("user");
                     element = (Element)list.item(0);
                     
                     txtList = element.getChildNodes();
                     client.setUser(((Node)txtList.item(0)).getNodeValue().trim());
                     
                     //PASSWORD
                     list = clientElement.getElementsByTagName("password");
                     element = (Element)list.item(0);
                     
                     txtList = element.getChildNodes();
                     client.setPassword(((Node)txtList.item(0)).getNodeValue().trim());
                     
                     //SSL
                     list = clientElement.getElementsByTagName("ssl");
                     element = (Element)list.item(0);
                     
                     txtList = element.getChildNodes();
                     client.setSsl(Boolean.parseBoolean(((Node)txtList.item(0)).getNodeValue().trim()));

                     //System.out.println("Name : " + client.getName());

                     //-------
                     
                     clients.add(client);                     

                 }//end of if clause

             }//end of for loop with s var
            	
                        
		} catch (IOException e) {
			LOG.error(": Load clients' cache file error");
		} catch (Exception e) {
			LOG.error(": Parse clients' cache file error");
		}
		
		return clients;
        
	}
	
	/**
	 * Update client information
	 * 
	 * @param clientsFile
	 * @param client
	 */
	public static void update(String clientsFile, Client client) {
		
		
		// Parse the input file to get a Document object
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
	        Document doc = db.parse (new File(clientsFile));
	        
	        // normalize text representation
            doc.getDocumentElement ().normalize ();
            
            //Get user nodes
            NodeList clientList = doc.getElementsByTagName("client");
            
            for(int i=0; i<clientList.getLength() ; i++){
            	
            	 Node clientNode = clientList.item(i);
                 if(clientNode.getNodeType() == Node.ELEMENT_NODE){
                	 
                	 //Create every user object
                     Element clientElement = (Element)clientNode;
                                                               
                     //NAME                     
                     NodeList list = clientElement.getElementsByTagName("name");
                     Element element = (Element)list.item(0);                     
                     NodeList textNameList = element.getChildNodes();
                     String name = ((Node)textNameList.item(0)).getNodeValue().trim();   
                                                            
                     
                     //if name then update
                     if (name.equals(client.getName())) {
                                          
	                     //ROLE
	                     list = clientElement.getElementsByTagName("description");
	                     element = (Element)list.item(0);	                     
	                     NodeList txtList = element.getChildNodes();
	                     ((Node)txtList.item(0)).setNodeValue(client.getDescription());
	                     
	                     	                     
	                     //MAIL
	                     list = clientElement.getElementsByTagName("url");
	                     element = (Element)list.item(0);	                     
	                     txtList = element.getChildNodes();
	                     ((Node)txtList.item(0)).setNodeValue(client.getUrl());
	                     
	                     //PASSWORD
	                     list = clientElement.getElementsByTagName("password");
	                     element = (Element)list.item(0);	                     
	                     txtList = element.getChildNodes();
	                     ((Node)txtList.item(0)).setNodeValue(client.getPassword());
	                     
	                     //USER
	                     list = clientElement.getElementsByTagName("user");
	                     element = (Element)list.item(0);	                     
	                     txtList = element.getChildNodes();
	                     ((Node)txtList.item(0)).setNodeValue(client.getUser());
	                     
	                     //SSL
	                     list = clientElement.getElementsByTagName("ssl");
	                     element = (Element)list.item(0);	                     
	                     txtList = element.getChildNodes();
	                     ((Node)txtList.item(0)).setNodeValue(client.isSsl()?"true":"false");

	                     	                     
	                     // use specific Xerces class to write DOM-data to a file:
	                     XMLSerializer serializer = new XMLSerializer();
	                     serializer.setOutputCharStream(
	                       new java.io.FileWriter(clientsFile));
	                     serializer.serialize(doc);
	                     
	                     
	                     
	                     break;

                     }

                 }//end of if clause

             }//end of for loop with s var
            	
                        
		} catch (IOException e) {
			LOG.error(": Load clients' cache file error");
		} catch (Exception e) {
			LOG.error(": Parse clients' cache file error");
		}
		
		//return users;
        
	}	

}
