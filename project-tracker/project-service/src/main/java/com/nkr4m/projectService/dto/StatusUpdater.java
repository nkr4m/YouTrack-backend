package com.nkr4m.projectService.dto;

import java.util.List;

public class StatusUpdater {
	private Integer projectId;
	private List<Item> ticketInfo;
	
	public StatusUpdater() {
		// TODO Auto-generated constructor stub
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public List<Item> getTicketInfo() {
		return ticketInfo;
	}

	public void setTicketInfo(List<Item> ticketInfo) {
		this.ticketInfo = ticketInfo;
	}
	
	
	
}
