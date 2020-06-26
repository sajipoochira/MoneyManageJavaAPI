package com.mecherycorp.MoneyManage.model;

import java.time.LocalDate;

public class monthTodate {
	
	private LocalDate FirstDay;
	private LocalDate LastDay;

	public monthTodate() {
		// TODO Auto-generated constructor stub
	}

	public monthTodate(LocalDate firstDay, LocalDate lastDay) {
		super();
		FirstDay = firstDay;
		LastDay = lastDay;
	}

	public LocalDate getFirstDay() {
		return FirstDay;
	}

	public void setFirstDay(LocalDate firstDay) {
		FirstDay = firstDay;
	}

	public LocalDate getLastDay() {
		return LastDay;
	}

	public void setLastDay(LocalDate lastDay) {
		LastDay = lastDay;
	}

	
	
}
