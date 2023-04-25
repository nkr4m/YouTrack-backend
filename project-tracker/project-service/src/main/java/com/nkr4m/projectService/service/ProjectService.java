package com.nkr4m.projectService.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nkr4m.projectService.document.Project;

import com.nkr4m.projectService.document.Ticket;
import com.nkr4m.projectService.dto.CollabReq;
import com.nkr4m.projectService.dto.StatusUpdater;

public interface ProjectService {

	void saveProject(Project project, String userId);

	void addTicket(Ticket ticket);

	List<Project> fetchProjects(String emailId);

	JSONObject fetchTickets(Integer projectId);

	void statusUpdater(StatusUpdater status);

	List<Project> fetchCollabProjects(CollabReq collabReq);

	JSONArray fetchCalenderView(Integer projId);

	JSONObject deleteTicket(Integer ticketId);

	JSONObject updateTicket(Integer ticketId, Ticket ticket);

}
