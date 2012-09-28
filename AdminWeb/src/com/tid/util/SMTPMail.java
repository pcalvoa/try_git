package com.tid.util;

import java.security.Security;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import com.sun.mail.smtp.SMTPSSLTransport;


public class SMTPMail {
	
	private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	
	public static void main(String[] args) {
		
        String to = "pactorre@gmail.com";
        String from = "defaultmailsbs.1721@gmail.com";
        // SUBSTITUTE YOUR ISP'S MAIL SERVER HERE!!!
        String host = "smtp.gmail.com";
        String port = "465";        
        String user = "defaultmailsbs.1721@gmail.com";
        String password = "sbsadmin2011";
        
        SMTPMail mail = new SMTPMail(to, from, host, user, password);
        mail.setPort(port);
        mail.setSslEnabled(false);
        mail.setSubject("subject");
        mail.setContent("content");        
        mail.send();
        
	}
	 
	

	 private String to;
	 private String from;
	 private String host;
	 private String port = "465";
	 private boolean sslEnabled;
	 private String user;
	 private String password;
	 private String subject;
	 private String content;
	 	 	 	 
	 public SMTPMail(String to, String from, String host, String user, String password) {
		 this.to = to;
		 this.from = from;
		 this.host = host;
		 this.user = user;
		 this.password = password;
	 }
	 
	 public void send() {
	        
	        // Create properties, get Session
		 	Properties props = System.getProperties();


	        // If using static Transport.send(),
	        // need to specify which host to send it to
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.user", from);
	     	props.put("mail.smtp.auth", "true");	        	        
	        props.put("mail.smtp.port", port);
	        
	        
	        //if (sslEnabled) {
	        	props.put("mail.smtp.socketFactory.port", port);
	        	props.put("mail.smtp.socketFactory.class", SSL_FACTORY);	        	
	        	props.put("mail.smtp.starttls.enable", "true");	        	
	        	props.put("mail.smtp.socketFactory.fallback", "true");
	        //}
	        
	        // To see what is going on behind the scene
	        props.put("mail.debug", "false");
	        	        
	        // Get session
			Authenticator auth = new MyAuthenticator(user, password);
			Session session = Session.getDefaultInstance(props, auth);
	        	        
	        try {
	        	
	        	// Instantiate a message
	            Message msg = new MimeMessage(session);

	            // Set message attributes
	            msg.setFrom(new InternetAddress(from));
	            InternetAddress[] address = {new InternetAddress(to)};
	            msg.setRecipients(Message.RecipientType.TO, address);

	            msg.setSubject(subject);
	            msg.setSentDate(new Date());
	            
	            // Set message content 
	            setTextContent(msg);
	            
	            msg.saveChanges();
	        
	        	if (sslEnabled) {
	        		
	        		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	        		
	        		SMTPSSLTransport sslBus =
	 	            	(SMTPSSLTransport)session.getTransport("smtps");
	        		 
	        		sslBus.connect(host, user, password);
	        		 
	        		sslBus.sendMessage(msg, address);

	        		sslBus.close();
		        }
	        	else {
	        	
		            // Get a Transport object to send e-mail
		            Transport bus = session.getTransport("smtp");
	       	            
		            // Connect only once here
		            // Transport.send() disconnects after each send
		            // Usually, no username and password is required for SMTP
		            //bus.connect();	            
		            bus.connect(host, user, password);
	            	            
		            bus.sendMessage(msg, address);

		            bus.close();
	        	}
		            
	        }
	        catch (MessagingException mex) {
	            // Prints all nested (chained) exceptions as well
	            mex.printStackTrace();
	            // How to access nested exceptions
	            while (mex.getNextException() != null) {
	                // Get next exception in chain
	                Exception ex = mex.getNextException();
	                ex.printStackTrace();
	                if (!(ex instanceof MessagingException)) break;
	                else mex = (MessagingException)ex;
	            }
	        }
	    }
	 
	 	// A simple, single-part text/plain e-mail.
	    private void setTextContent(Message msg) throws MessagingException {
	            // Set message content
	            String content = this.content;
	            msg.setText(content);

	            // Alternate form
	            msg.setContent(content, "text/plain");

	    }

		public String getTo() {
			return to;
		}

		public void setTo(String to) {
			this.to = to;
		}

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getPort() {
			return port;
		}

		public void setPort(String port) {
			this.port = port;
		}

		public boolean isSslEnabled() {
			return sslEnabled;
		}

		public void setSslEnabled(boolean sslEnabled) {
			this.sslEnabled = sslEnabled;
		}

	
}

class MyAuthenticator extends Authenticator {
	private String user;
	private String password;
	MyAuthenticator(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("user", "password");
	}
}
