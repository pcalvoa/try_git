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
import com.tid.pojo.ApplicationUser;

public class UserParser {

	private static final Logger LOG = Logger.getLogger(UserParser.class.getName());
	
	/**
	 * Parse configuration file
	 * @param usersFile
	 * @return
	 */
	public static List<ApplicationUser> parse(String usersFile) {
						
		List<ApplicationUser> users = new ArrayList<ApplicationUser>();
		
		// Parse the input file to get a Document object
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
	        Document doc = db.parse (new File(usersFile));
	        
	        // normalize text representation
            doc.getDocumentElement ().normalize ();
            
            //Get user nodes
            NodeList userList = doc.getElementsByTagName("user");
            
            for(int i=0; i<userList.getLength() ; i++){
            	
            	 Node userNode = userList.item(i);
                 if(userNode.getNodeType() == Node.ELEMENT_NODE){
                	 
                	 //Create every user object
                     Element userElement = (Element)userNode;
                     ApplicationUser user = new ApplicationUser();                     
                     
                     //NAME                     
                     NodeList list = userElement.getElementsByTagName("name");
                     Element element = (Element)list.item(0);
                     
                     NodeList textNameList = element.getChildNodes();
                     user.setUserName(((Node)textNameList.item(0)).getNodeValue().trim());   
                                                            
                     //ROLE
                     list = userElement.getElementsByTagName("role");
                     element = (Element)list.item(0);
                     
                     NodeList txtList = element.getChildNodes();
                     user.setRole(((Node)txtList.item(0)).getNodeValue().trim());
                     
                     //MAIL
                     list = userElement.getElementsByTagName("mail");
                     element = (Element)list.item(0);
                     
                     txtList = element.getChildNodes();
                     user.setMail(((Node)txtList.item(0)).getNodeValue().trim());
                     
                     //PASSWORD
                     list = userElement.getElementsByTagName("password");
                     element = (Element)list.item(0);
                     
                     txtList = element.getChildNodes();
                     user.setPassword(((Node)txtList.item(0)).getNodeValue().trim());

                     //STATE
                     list = userElement.getElementsByTagName("state");
                     element = (Element)list.item(0);
                     
                     txtList = element.getChildNodes();
                     user.setState(((Node)txtList.item(0)).getNodeValue().trim());

                     //CODE
                     list = userElement.getElementsByTagName("code");
                     element = (Element)list.item(0);
                     
                     txtList = element.getChildNodes();
                     user.setCode(((Node)txtList.item(0)).getNodeValue().trim());

                     //-------
                     
                     users.add(user);                     

                 }//end of if clause

             }//end of for loop with s var
            	
                        
		} catch (IOException e) {
			LOG.error(": Load users' cache file error " + usersFile);
		} catch (Exception e) {
			LOG.error(": Parse users' cache file error" + usersFile);
		}
		
		return users;
        
	}	
	
	public static void update(String usersFile, ApplicationUser user) {
				
		// Parse the input file to get a Document object
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
	        Document doc = db.parse (new File(usersFile));
	        
	        // normalize text representation
            doc.getDocumentElement ().normalize ();
            
            //Get user nodes
            NodeList userList = doc.getElementsByTagName("user");
            
            for(int i=0; i<userList.getLength() ; i++){
            	
            	 Node userNode = userList.item(i);
                 if(userNode.getNodeType() == Node.ELEMENT_NODE){
                	 
                	 //Create every user object
                     Element userElement = (Element)userNode;
                                                               
                     //NAME                     
                     NodeList list = userElement.getElementsByTagName("name");
                     Element element = (Element)list.item(0);                     
                     NodeList textNameList = element.getChildNodes();
                     String username = ((Node)textNameList.item(0)).getNodeValue().trim();   
                                                            
                     
                     //if username then update
                     if (username.equals(user.getUserName())) {
                                          
	                     //ROLE
	                     list = userElement.getElementsByTagName("role");
	                     element = (Element)list.item(0);	                     
	                     NodeList txtList = element.getChildNodes();
	                     ((Node)txtList.item(0)).setNodeValue(user.getRole());
	                     
	                     	                     
	                     //MAIL
	                     list = userElement.getElementsByTagName("mail");
	                     element = (Element)list.item(0);	                     
	                     txtList = element.getChildNodes();
	                     ((Node)txtList.item(0)).setNodeValue(user.getMail());
	                     
	                     //PASSWORD
	                     list = userElement.getElementsByTagName("password");
	                     element = (Element)list.item(0);	                     
	                     txtList = element.getChildNodes();
	                     ((Node)txtList.item(0)).setNodeValue(user.getPassword());
	                     
	                     //STATE
	                     list = userElement.getElementsByTagName("state");
	                     element = (Element)list.item(0);	                     
	                     txtList = element.getChildNodes();
	                     ((Node)txtList.item(0)).setNodeValue(user.getState());
	                     
	                     //CODE
	                     list = userElement.getElementsByTagName("code");
	                     element = (Element)list.item(0);	                     
	                     txtList = element.getChildNodes();
	                     ((Node)txtList.item(0)).setNodeValue(user.getCode());
	                     
	                     //Save user
	                     
	                  // use specific Xerces class to write DOM-data to a file:
	                     XMLSerializer serializer = new XMLSerializer();
	                     serializer.setOutputCharStream(
	                       new java.io.FileWriter(usersFile));
	                     serializer.serialize(doc);
	                     
	                     
	                     
	                     break;

                     }

                 }//end of if clause

             }//end of for loop with s var
            	
                        
		} catch (IOException e) {
			LOG.error(": Load users' cache file error"  + usersFile);
		} catch (Exception e) {
			LOG.error(": Parse users' cache file error"  + usersFile);
		}
		
		//return users;
        
	}	

	
}
