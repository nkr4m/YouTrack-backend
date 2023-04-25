package com.nkr4m.notificationService.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CollabProject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer projectId;
	private String projectName;
	private String sender;
	private String userEmailId;
	private String role;
	
//	A-accept, R-reject, P-pending
	private String actionTaken;
	
	
	public CollabProject() {
		// TODO Auto-generated constructor stub
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getProjectId() {
		return projectId;
	}


	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}





	public String getUserEmailId() {
		return userEmailId;
	}

	
	

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getActionTaken() {
		return actionTaken;
	}


	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}


	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public String getSender() {
		return sender;
	}


	public void setSender(String sender) {
		this.sender = sender;
	}
	
	
	
	
	

}
