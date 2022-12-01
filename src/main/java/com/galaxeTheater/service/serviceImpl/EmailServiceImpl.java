package com.galaxeTheater.service.serviceImpl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.galaxeTheater.exceptions.EmailNotFoundException;
import com.galaxeTheater.exceptions.EmailNotSentException;
import com.galaxeTheater.service.EmailService;
import com.galaxeTheater.service.UserService;


@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private UserService userService;

	@Value("${spring.mail.username}")
	private String sender;
	
	
	Logger log=LogManager.getLogger(EmailServiceImpl.class);
	
	@Override
	public String sendSimpleMail(String email) {
		try {

			SimpleMailMessage mailMessage = new SimpleMailMessage();

			mailMessage.setFrom(sender);
			mailMessage.setTo(email);
			mailMessage.setText("The otp for reset password is\n" + userService.generateOTP(email));
			mailMessage.setSubject("OTP for forget Password");
			javaMailSender.send(mailMessage);
			return "Mail Sent Successfully";
		} catch (EmailNotSentException e) {
			System.out.println(e);
			log.error(e);
			return "Error while Sending Mail";
		}
		catch(EmailNotFoundException e) {
			System.out.println(e);
			log.error(e);
			return "Error while Sending Mail";
		}
		
	}

	@Override
	public String sendWelcomeMail(String email) {
		try {

			SimpleMailMessage mailMessage = new SimpleMailMessage();

			mailMessage.setFrom(sender);
			mailMessage.setTo(email);
			mailMessage.setText("Thanks for registering to GalaxE Movie Theater");
			mailMessage.setSubject("Registration Success");
			javaMailSender.send(mailMessage);
			return "Mail Sent Successfully";
		} catch (EmailNotSentException e) {
			System.out.println(e);
			log.error(e);
			return "Error while Sending Mail";
		}
		catch(EmailNotFoundException e) {
			System.out.println(e);
			log.error(e);
			return "Error while Sending Mail";
		}

	}

}
