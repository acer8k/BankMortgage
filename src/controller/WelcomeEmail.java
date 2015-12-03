package controller;

import static controller.WelcomeEmail.sendMail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class WelcomeEmail {

	public static void sendMail(String to, String subject, String message, String emailtype){

		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.auth", "true");
		//props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");

		final String username = "ualbanybank@gmail.com"; 
		final String password = "bank@123";
		try{
			
			Session session = Session.getDefaultInstance(props, new Authenticator(){
				
				protected PasswordAuthentication getPasswordAuthentication() {
					
					return new PasswordAuthentication(username, password);
				}
			});
			
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(username));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to,false));
			
			if(emailtype.equalsIgnoreCase("reg")){
				msg.setSubject(subject);
				msg.setText(message); 
			}

			msg.setSentDate(new Date());
			Transport.send(msg);

			
			// Store the mail in Company's inbox as well

			msg = new MimeMessage(session); // Create a new message
			msg.setFrom(new InternetAddress(username));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username,false));
			msg.setSubject(subject + " From: " + to);
			msg.setText(to+" sent:\n"+message);
			msg.setSentDate(new Date());
			Transport.send(msg);
			
		}catch (MessagingException e){ 
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}