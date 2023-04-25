package com.nkr4m.projectService.document;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Ticket {
	@Id
	private Integer ticketId;
	private Integer projectLinkId;
	private String title;
	private String currentState;
	private String description;
	
	private String startDate;
	private String endDate;
	
	
	private LocalDate createdDate;
	private LocalTime createdTime;
//	private String owner;
	private String reportedBy;
	private String assignedTo;
	
	
	public Ticket() {
		// TODO Auto-generated constructor stub
	}


	public Integer getTicketId() {
		return ticketId;
	}


	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}


	public Integer getProjectLinkId() {
		return projectLinkId;
	}


	public void setProjectLinkId(Integer projectLinkId) {
		this.projectLinkId = projectLinkId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getCurrentState() {
		return currentState;
	}


	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}


	public LocalDate getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}


	public LocalTime getCreatedTime() {
		return createdTime;
	}


	public void setCreatedTime(LocalTime createdTime) {
		this.createdTime = createdTime;
	}


	public String getReportedBy() {
		return reportedBy;
	}


	public void setReportedBy(String reportedBy) {
		this.reportedBy = reportedBy;
	}


	public String getAssignedTo() {
		return assignedTo;
	}


	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	
	
	
	




	
	

}
