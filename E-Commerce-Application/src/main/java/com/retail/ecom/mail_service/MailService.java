package com.retail.ecom.mail_service;

import java.util.Date;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.retail.ecom.utility.MessageModel;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MailService {
	
	private JavaMailSender javaMailSender;
	
	public void sendEmailMessage (MessageModel model) throws MessagingException {
		MimeMessage message=javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(model.getTo());
		helper.setSubject(model.getSubject());
		helper.setSentDate(new Date());
		helper.setText(model.getText(),true);
		javaMailSender.send(message);
		
	}

}
