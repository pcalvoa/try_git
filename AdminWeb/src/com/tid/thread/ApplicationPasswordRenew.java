package com.tid.thread;

import java.util.Calendar;
import java.util.HashMap;

import net.java.sip.communicator.util.Logger;

import com.tid.Constants;
import com.tid.atica.htc.core.bean.UserVO;
import com.tid.atica.htc.core.util.encriptacion.MD5;
import com.tid.atica.htc.filtro.consultas.bean.Entidad;
import com.tid.dao.MailDao;
import com.tid.service.impl.UserService;
import com.tid.util.Parser;
import com.tid.util.SMTPMail;

/**
 * Desactive a user and send a new password
 * @author fdelatorre
 *
 */
public class ApplicationPasswordRenew extends Thread {

	private static final Logger LOG = Logger.getLogger(ApplicationPasswordRenew.class.getName());
	
	public String email;
	public String url;
	public String client;
	public String server;
	public String user;
	public String password;
	
	public ApplicationPasswordRenew(String client, String server, String user, String password, String email, String url) {
		this.email = email;
		this.url = url;
		this.client = client;
		this.server = server;
		this.user = user;
		this.password = password;		
	}
	
	@Override
	public void run() {
		
		//Search user by email		
		UserService service;
		try {
			service = new UserService(this.server,this.user,this.password);
			
			LOG.debug("Before get email information");
			
			Object[] users = service.searchEmail(email);	
			
			LOG.debug("After get email information");
			
			//Transform user class
			UserVO user = null;
					
			if (users!=null && users.length > 0) {		
				Entidad value = Entidad.class.cast(users[0]);															
				user = Parser.getUser(value);
			}
			else {
				LOG.error("Invalid mail");
			}
			
			if (user!=null) {			
	
				//Security code	
				String code= MD5.digest(String.valueOf(Calendar.getInstance().getTimeInMillis())).substring(0, 15);
				//FIXME Probar				
				user.setCodigoSeguridad(code);
				//Update expiration	
				user.setFechaCaducidad(Calendar.getInstance().getTime());
				
				this.url += "?" + Constants.PARAM_CODE + "=" + code + "&" + Constants.PARAM_CLIENT  + "=" + this.client;
				
				LOG.debug("Before send mail to " + user.getCorreo());
				
				//Send new password
				MailDao mailDao = new MailDao();
				HashMap mailConfig = mailDao.getMail(MailDao.getConfigFile());
				SMTPMail mail = new SMTPMail(user.getCorreo(),  
									(String)mailConfig.get(Constants.MAIL_FROM), 
									(String)mailConfig.get(Constants.MAIL_HOST), 
									(String)mailConfig.get(Constants.MAIL_USER), 
									(String)mailConfig.get(Constants.MAIL_PASSWORD));
				
				mail.setSubject("Side by Side - Notification");
				
				StringBuilder body = new StringBuilder("Side by Side automatic message\n\n");
				body.append("You requested a password recovery. Please click on the followed link to continue the process").append("\n");
				body.append(url);
				body.append("\n\n").append("This is an automatically generated email, please do not reply.");
				body.append("\n").append("Please, contact your help desk for any futher information.");
							
				mail.setContent(body.toString());
												
				
				mail.send();
							
				LOG.debug("Before user update");
				
				//Save user						
				service.update(user);
				
			}
			else {
				LOG.error("Invalid mail");
			}
			
		} catch (Exception e) {			
			LOG.error("Exception in password recovery (" + e.getMessage() + ")");
		}	
		
	}
	
}
