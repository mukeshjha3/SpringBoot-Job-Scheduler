package com.schedular.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateAndTimeForScheduling {

	private String date;
	private String month;
	private String hour;
	private String minute;
	private String second;

}
