package com.fms.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.properties.EmailProperties;
import com.sun.istack.ByteArrayDataSource;

@Service
public class EmailService {

	@Autowired
	EmailProperties emailProperties;

	public void sendmailForFeedback(String userEmail, String subject, String content) throws AddressException, MessagingException, IOException {

		Message msg = new MimeMessage(getMailSession());
		msg.setFrom(new InternetAddress(emailProperties.getEmailAddress(), false));

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
		msg.setSubject(subject);

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

//		multipart.addBodyPart(mbp2);
		msg.setContent(content, "text/html");
		Transport.send(msg);
	}
	
	public void sendEmailReportAttachment(String userEmail, String subject, ByteArrayOutputStream bos) throws AddressException, MessagingException, IOException {
		
		Message msg = new MimeMessage(getMailSession());
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
		msg.setSubject(subject);

		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(subject);

		MimeBodyPart mbp2 = new MimeBodyPart();
		mbp2.setDataHandler(new DataHandler(new ByteArrayDataSource(bos.toByteArray(), "application/excel")));
		mbp2.setFileName("excel.xls");

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		multipart.addBodyPart(mbp2);
		msg.setContent(multipart);
		Transport.send(msg);
	}

	public Session getMailSession() {
		Properties props = new Properties();
		props.put(emailProperties.getMailSmtpAuth(), emailProperties.getMailSmtpAuthStatus());
		props.put(emailProperties.getMailSmtpStarttlsEnable(), emailProperties.getMailSmtpStarttlsEnableStatus());
		props.put(emailProperties.getMailSmtpHost(), emailProperties.getMailSmtpHostName());
		props.put(emailProperties.getMailSmtpPort(), emailProperties.getMailSmtpPortNumber());

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailProperties.getEmailAddress(),
						emailProperties.getEmailPassword());
			}
		});
		return session;
	}
}
