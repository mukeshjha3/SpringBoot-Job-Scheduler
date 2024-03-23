package com.schedular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.schedular.Service.EmailService;
import com.schedular.Service.EmailServiceImpl;
import com.schedular.model.DateAndTimeForScheduling;
import com.schedular.model.EmailRequest;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class EmailController {

	@Autowired
	public final EmailServiceImpl emailService;
	
	@Autowired
	private final TaskScheduler taskscheduler;

	@PostMapping("/save")
	public String SaveEmail(@RequestBody EmailRequest emailRequest) {
		emailService.saveEmail(emailRequest);
		return "Email has been Saved";
	}

	@GetMapping("/send")
	public String sendMailInBulk(@RequestBody DateAndTimeForScheduling andTimeForScheduling) {

		String month = andTimeForScheduling.getMonth();
		String date = andTimeForScheduling.getDate();
		String hour = andTimeForScheduling.getHour();
		String minute = andTimeForScheduling.getMinute();
		String second = andTimeForScheduling.getSecond();

		String cronExpression =second + " " + minute + " " + hour + " " + date + " " + month + " ?";
		System.out.println( cronExpression );
		try {
		    taskscheduler.schedule(() -> emailService.SendEmailToAllUser(), new CronTrigger(cronExpression));
		    System.out.println("Task scheduled successfully.");
		} catch (Exception e) {
		    System.err.println("Error scheduling task: " + e.getMessage());
		    e.printStackTrace(); // Print stack trace for detailed error analysis
		}
		return "Mail has been sent to all User Successfully";
	}
	
	
	@GetMapping("/single")
	public String SendSinglemail() {
		boolean sendEmailWithoutAttachment = emailService.sendEmailWithoutAttachment("immukeshjha123@gmail.com", "mukesh jha");
		return "sent";
	}
}

