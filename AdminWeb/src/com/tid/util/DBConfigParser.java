package com.tid.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.java.sip.communicator.util.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tid.pojo.DBConfig;

public class DBConfigParser {

	private static final Logger LOG = Logger.getLogger(DBConfigParser.class.getName());
	
	/**
	 * Parse configuration file
	 * @param DBFile
	 * @return
	 */
	public static DBConfig parse(String dBConfig) {
		
		DBConfig config = new DBConfig();
		
		// Parse the input file to get a Document object
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
	        Document doc = db.parse (new File(dBConfig));
	        
	        // normalize text representation
            doc.getDocumentElement().normalize ();
            
            //Get user nodes
            NodeList userList = doc.getElementsByTagName("connection");
            
            for(int i=0; i<userList.getLength() ; i++){
            	
            	 Node userNode = userList.item(i);
                 if(userNode.getNodeType() == Node.ELEMENT_NODE){
                	 
                	 //Create every user object
                     Element userElement = (Element)userNode;
                     DBConfig connection = new DBConfig();                     
                     
                     //HOST                     
                     NodeList list = userElement.getElementsByTagName("host");
                     Element element = (Element)list.item(0);
                     
                     NodeList textNameList = element.getChildNodes();
                     connection.setHost(((Node)textNameList.item(0)).getNodeValue().trim());   
                                                            
                     //PORT
                     list = userElement.getElementsByTagName("port");
                     element = (Element)list.item(0);
                     
                     NodeList txtList = element.getChildNodes();
                     connection.setPort(((Node)txtList.item(0)).getNodeValue().trim());
                     
                     //NAME
                     list = userElement.getElementsByTagName("name");
                     element = (Element)list.item(0);
                     
                     txtList = element.getChildNodes();
                     connection.setName(((Node)txtList.item(0)).getNodeValue().trim());
                     
                     //PASSWORD
                     list = userElement.getElementsByTagName("password");
                     element = (Element)list.item(0);
                     
                     txtList = element.getChildNodes();
                     connection.setPassword(((Node)txtList.item(0)).getNodeValue().trim());

                     //USER
                     list = userElement.getElementsByTagName("user");
                     element = (Element)list.item(0);
                     
                     txtList = element.getChildNodes();
                     connection.setUser(((Node)txtList.item(0)).getNodeValue().trim());                     

                     //-------
                     
                     config = connection;    
                     break;

                 }//end of if clause

             }//end of for loop with s var
            	
                        
		} catch (IOException e) {
			LOG.error(": Load config database file error");
		} catch (Exception e) {
			LOG.error(": Parse config database cache file error");
		}
		
		return config;
        
	}	

	
}
