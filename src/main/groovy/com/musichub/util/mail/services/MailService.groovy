package com.musichub.util.mail.services

import org.apache.catalina.WebResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage
import org.springframework.web.client.RestTemplate;

class MailService {
	private MailSender mailSender;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendMail(String from, String to, String subject, String msg) {
		SimpleMailMessage message = new SimpleMailMessage()
		message.setFrom(from)
		message.setTo(to)
		message.setSubject(subject)
		message.setText(msg)
		mailSender.send(message)
	}
	
}
