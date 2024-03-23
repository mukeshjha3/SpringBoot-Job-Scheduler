package com.schedular.Service;

import com.schedular.model.EmailRequest;

public interface EmailService {

	public  boolean sendEmailWithoutAttachment(String to, String username) ;
	public String SendEmailToAllUser() ;
	public String saveEmail(EmailRequest emailRequest);
		
	
}
