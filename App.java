package com.shaad;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class App 
{
    public static void main( String[] args )
    {
       System.out.println("preparing for send message...");
       String message="Hello dear this is message for your security check.";
       String subject="code : confirmation";
       String to="hussainahadman@gmail.com";
       String from ="shadmanhussain05@gmail.com";
       
     //  sendEmail(message,subject,to,from);
       sendAttach(message,subject,to,from);
    }
    
		// this is resposible to send the messag e with attachment
	private static void sendAttach(String message, String subject, String to, String from) {
		

	//this is responsible for send email
//	private static void sendEmail(String message, String subject, String to, String from) {

		// variable for gmail host
		String host= "smtp.gmail.com";
		
		// get Sysytem properties
	Properties properties = System.getProperties();
	
	
	// Setting information to properties object
	// host set
	properties.put("mail.smtp.host",host);
	properties.put("mail.smtp.port", "465");
	properties.put("mail.smtp.ssl.enable", "true");
	properties.put("mail.smtp.auth", "true");
	
	//step: 1 get  the session object...
	Session session= Session.getInstance(properties,new Authenticator() {

		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("shadmanhussain05@gmail.com","ShadmanHussain05@123");
		}
		
		
	});
	
	session.setDebug(true);
	
	// step: 2 compose message[text,multi media]
	MimeMessage m=new MimeMessage(session);
	
	try {
	// from email
	m.setFrom(from);
	
	// add Receipient to message
	m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	
	// ADDINg subject to message
	m.setSubject(subject);
	
	
	// attachment....
	// file path
	String path="C:\\Users\\Arman\\Desktop\\logo.jpg";
	MimeMultipart mimeMultipart = new MimeMultipart();
			
	// text
	//file
	MimeBodyPart textMime= new MimeBodyPart();
	MimeBodyPart fileMime = new MimeBodyPart();
	
	
	try {
		textMime.setText(message);
		File file = new File(path);
		fileMime.attachFile(file);
	
		mimeMultipart.addBodyPart(textMime);
		 
		mimeMultipart.addBodyPart(fileMime);
	}catch(Exception e) {
		e.printStackTrace();
	}
	m.setContent(mimeMultipart);
	
	
	// Adding text message
	//m.setText(message);
	
	
	// send
	// send message using transport class
	Transport.send(m);
	System.out.println("sent Successfully..........");
	
	
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	}
}
