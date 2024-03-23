package com.schedular.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.schedular.Repository.EmailRepo;
import com.schedular.model.EmailRequest;
import lombok.AllArgsConstructor;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private EmailRepo emailRepo;

	
	public String SendEmailToAllUser() {
		
		List<EmailRequest> findAll = emailRepo.findAll();
		System.out.println("Total mail needs to be sent : "+findAll.size());
		findAll.stream().forEach(e -> {
			boolean emailSentSuccess = sendEmailWithoutAttachment(e.getUserEmailAddress(), e.getUsername());
			System.out.println("mail sent to " + e.getUserEmailAddress());
			if (!emailSentSuccess) {
				System.out.println("email has not been sent to " + e.getUserEmailAddress());
			}
		});
		return "Email has been sent to all user";
	}

	public boolean sendEmailWithoutAttachment(String to, String username) {
		

		final String subject = "Your Credit Card Bill Payment Reminder";

		final String body = "Dear " + username + ",\n\n"
				+ "This is a reminder that your credit card bill of Rs. 1234 is due on March 3rd. "
				+ "Please ensure timely payment to avoid any late fees or penalties.\n\n"
				+ "Thank you for your prompt attention to this matter.\n\n" + "Best Regards,\n" + "Mukesh Jha";

		try {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("mukeshjhatest33@gmail.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		javaMailSender.send(message);
		System.out.println("Mail sent");
		return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public String saveEmail(EmailRequest emailRequest) {
		for (int i = 0; i < 10; i++) {
			EmailRequest emailRequest1 = new EmailRequest();
			emailRequest1.setUserId(UUID.randomUUID().toString());
			emailRequest1.setUserEmailAddress(emailRequest.getUserEmailAddress());
			emailRequest1.setUsername(emailRequest.getUsername());
			EmailRequest lastEmailRequestDetails = emailRepo.save(emailRequest1);
		}
		return "User has been saved";
	}
}
