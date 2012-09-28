package com.tid.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.java.sip.communicator.util.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tid.Constants;


public class MailParser {

	private static final Logger LOG = Logger.getLogger(MailParser.class.getName());
	
	/**
	 * Parse configuration file
	 * @param usersFile
	 * @return
	 */
	public static HashMap parse(String file) {
		
		HashMap mailConfig = new HashMap();
		
				
		// Parse the input file to get a Document object
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
	        Document doc = db.parse (new File(file));
	        
	        // normalize text representation
            doc.getDocumentElement ().normalize ();
            
            //Get user nodes
            NodeList mailList = doc.getElementsByTagName("mail");
            
            for(int i=0; i<mailList.getLength() ; i++){
            	
            	 Node mailNode = mailList.item(i);
                 if(mailNode.getNodeType() == Node.ELEMENT_NODE){
                	 
                	 //Create every user object
                     Element mailElement = (Element)mailNode;
                                                            
                     //HOST                     
                     NodeList list = mailElement.getElementsByTagName(Constants.MAIL_HOST);
                     Element element = (Element)list.item(0);                     
                     NodeList textNameList = element.getChildNodes();
                     mailConfig.put(Constants.MAIL_HOST, ((Node)textNameList.item(0)).getNodeValue().trim());
                        
                     //PORT                     
                     list = mailElement.getElementsByTagName(Constants.MAIL_PORT);
                     element = (Element)list.item(0);                     
                     textNameList = element.getChildNodes();
                     mailConfig.put(Constants.MAIL_PORT, ((Node)textNameList.item(0)).getNodeValue().trim());
                     
                     //USER                    
                     list = mailElement.getElementsByTagName(Constants.MAIL_USER);
                     element = (Element)list.item(0);                     
                     textNameList = element.getChildNodes();
                     mailConfig.put(Constants.MAIL_USER, ((Node)textNameList.item(0)).getNodeValue().trim());

                     //PASSWORD                 
                     list = mailElement.getElementsByTagName(Constants.MAIL_PASSWORD);
                     element = (Element)list.item(0);                     
                     textNameList = element.getChildNodes();
                     mailConfig.put(Constants.MAIL_PASSWORD, ((Node)textNameList.item(0)).getNodeValue().trim());

                     //FROM           
                     list = mailElement.getElementsByTagName(Constants.MAIL_FROM);
                     element = (Element)list.item(0);                     
                     textNameList = element.getChildNodes();
                     mailConfig.put(Constants.MAIL_FROM, ((Node)textNameList.item(0)).getNodeValue().trim());

                     //SSL            
                     list = mailElement.getElementsByTagName(Constants.MAIL_SSL);
                     element = (Element)list.item(0);                     
                     textNameList = element.getChildNodes();
                     mailConfig.put(Constants.MAIL_SSL, ((Node)textNameList.item(0)).getNodeValue().trim());

                     
                 }//end of if clause

             }//end of for loop with s var
            	
                        
		} catch (IOException e) {
			LOG.error(": Load users' cache file error");
		} catch (Exception e) {
			LOG.error(": Parse users' cache file error");
		}
		
		return mailConfig;
        
	}	

	
}
