package com.lcode.service;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	public boolean sendEmail(String subject,String message, String to) throws AddressException{
		boolean f=false;
		String from="dhanushk1903@gmail.com";
		String host="smtp.gmail.com";
		Properties properties=System.getProperties();
		properties.setProperty("mail.transport.protocol", "smtp"); 
		properties.setProperty("mail.host", "smtp.gmail.com");
		properties.put("mail.debug", "true");  
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.socketFactory.port", "465");  
		properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
		properties.put("mail.smtp.socketFactory.fallback", "false");  
		
		Session session= Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("dhanushk1903@gmail.com","mldawuosaqwanxhu");
			}
		
		});
		session.setDebug(true);
		  InternetAddress addressFrom = new InternetAddress(from); 
		MimeMessage m = new MimeMessage(session);
		
		try {
		
			m.setSender(addressFrom);
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			 m.setSubject(subject);  
			 m.setText(message);
			 Transport.send(m);
			 f=true;
		}catch (Exception e) {
		e.printStackTrace();
		}
		
		return f;
		
	}
	
	
	public boolean sendAttach(String subject,String message, String to) throws AddressException{
		boolean f=false;
		String from="dhanushk1903@gmail.com";
		String host="smtp.gmail.com";
		Properties properties=System.getProperties();
		properties.setProperty("mail.transport.protocol", "smtp"); 
		properties.setProperty("mail.host", "smtp.gmail.com");
		properties.put("mail.debug", "true");  
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.socketFactory.port", "465");  
		properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
		properties.put("mail.smtp.socketFactory.fallback", "false");  
		
		Session session= Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("dhanushk1903@gmail.com","mldawuosaqwanxhu");
			}
		
		});
		session.setDebug(true);
		  InternetAddress addressFrom = new InternetAddress(from); 
		MimeMessage m = new MimeMessage(session);

		
		try {
		
			m.setSender(addressFrom);
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			 m.setSubject(subject);  
			String path="D:\\computer.png";
			MimeMultipart multipart= new MimeMultipart();
			MimeBodyPart text=new MimeBodyPart();
			MimeBodyPart filemime=new MimeBodyPart();
			
			try {
				text.setText(message);
				File file =new File(path);
				filemime.attachFile(file);
				multipart.addBodyPart(text);
				multipart.addBodyPart(filemime);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			m.setContent(multipart);
			 Transport.send(m);
			 f=true;
		}catch (Exception e){
		e.printStackTrace();
		}
		return f;
	

}
}