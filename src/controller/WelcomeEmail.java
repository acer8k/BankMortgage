package controller;

import static controller.WelcomeEmail.sendMail;

import java.io.UnsupportedEncodingException;
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

	public static void sendMail(String to, String subject, String message, String emailtype) throws UnsupportedEncodingException {

		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		Properties mailSet = System.getProperties();
		mailSet.setProperty("mail.smtp.host", "smtp.gmail.com");
		mailSet.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		mailSet.setProperty("mail.smtp.socketFactory.fallback", "false");
		mailSet.setProperty("mail.smtp.port", "465");
		mailSet.setProperty("mail.smtp.socketFactory.port", "465");
		mailSet.put("mail.smtp.auth", "true");
		// mailSet.put("mail.debug", "true");
		mailSet.put("mail.store.protocol", "pop3");
		mailSet.put("mail.transport.protocol", "smtp");

		final String username = "ualbanybank@gmail.com";
		final String password = "bank@123";
		try {

			Session session = Session.getDefaultInstance(mailSet, new Authenticator() {

				protected PasswordAuthentication getPasswordAuthentication() {

					return new PasswordAuthentication(username, password);
				}
			});

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(username));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

			if (emailtype.equalsIgnoreCase("reg")) {
				msg.setSubject(subject);
				msg.setText(message);
			}

			msg.setSentDate(new Date());
			Transport.send(msg);

			/*Saves the emails in the inbox.*/

			msg = new MimeMessage(session); // Create a new message
			msg.setFrom(new InternetAddress(username,"UBA"));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(username, false));
			msg.setSubject(subject + " From: " + to);
			msg.setText(to + " sent:\n" + message);
			msg.setSentDate(new Date());
			Transport.send(msg);

		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}