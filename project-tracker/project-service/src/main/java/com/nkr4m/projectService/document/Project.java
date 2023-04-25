package com.nkr4m.projectService.document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Project {
	
	@Id
	private Integer id;
	private String projectName;
	private String projectCode;
	private String owner;
	
	private LocalDate createdDate;
	private LocalTime createdTime;
	
	
	public Project() {
		// TODO Auto-generated constructor stub
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public String getProjectCode() {
		return projectCode;
	}


	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}


	public String getOwner() {
		return owner;
	}


	public void setOwner(String owner) {
		this.owner = owner;
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
	
	


	
	
	
	
	

}
