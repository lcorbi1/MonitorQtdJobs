package com.macys.domain;

public class DataRunInfo {

	private int quantityJobsPerDay = 0;
	String DateOfDay;   // This information is needed to get the day and convert to the same format as order_date to get all the jobs in that day.
	private Long order_id;
	private String data_center;
	private String order_date;

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public String getData_center() {
		return data_center;
	}

	public void setData_center(String data_center) {
		this.data_center = data_center;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public int getQuantityJobsPerDay() {
		return quantityJobsPerDay;
	}

	public void setQuantityJobsPerDay(int quantityJobsPerDay) {
		this.quantityJobsPerDay = quantityJobsPerDay;
	}
	
	

}
