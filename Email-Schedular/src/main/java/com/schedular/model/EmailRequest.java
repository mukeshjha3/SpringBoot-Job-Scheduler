package com.schedular.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {

	@Id
	private String userId;
	private String userEmailAddress;
	private String username;
	
//	private Date billDueDate;
	
	
}
