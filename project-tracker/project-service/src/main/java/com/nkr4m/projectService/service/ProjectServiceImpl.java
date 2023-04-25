package com.nkr4m.projectService.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nkr4m.projectService.document.Project;

import com.nkr4m.projectService.document.Ticket;
import com.nkr4m.projectService.dto.CollabReq;
import com.nkr4m.projectService.dto.Item;
import com.nkr4m.projectService.dto.StatusUpdater;
import com.nkr4m.projectService.repository.ProjectRepository;
import com.nkr4m.projectService.repository.TicketRepository;
import com.nkr4m.projectService.util.NextProjectSequenceService;
import com.nkr4m.projectService.util.NextTicketSequenceService;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository projectRep;
	
	@Autowired
	TicketRepository ticketRep;
	
	@Autowired
	NextProjectSequenceService nextSeq;
	
	@Autowired
	NextTicketSequenceService nextTicketSeq;
	
	@Override
	public void saveProject(Project project, String userId) {
		project.setId(nextSeq.getNextSequence("customSequences"));
		project.setCreatedDate(LocalDate.now());
		project.setCreatedTime(LocalTime.now());
		project.setOwner(userId);
		projectRep.save(project);
	}

	@Override
	public void addTicket(Ticket ticket) {
		ticket.setTicketId(nextTicketSeq.getNextSequence("customTicketSequences"));
		ticket.setCreatedDate(LocalDate.now());
		ticket.setCreatedTime(LocalTime.now());
		ticketRep.save(ticket);
	}

	@Override
	public List<Project> fetchProjects(String emailId) {
		
		List<Project> li = projectRep.fetchProjectFormUserId(emailId);
		return li;
	}

	@Override
	public JSONObject fetchTickets(Integer projectId) {
		JSONObject ob = new JSONObject();
		JSONArray res = new JSONArray();
		
		JSONArray toDo = new JSONArray();
		JSONArray pending = new JSONArray();
		JSONArray done = new JSONArray();
		JSONArray delivered = new JSONArray();
		
		
		List<Ticket> li = this.ticketRep.fetchTicketsFromProjectId(projectId);
		
		HashMap<String, List<Ticket>> sortedTickets = new HashMap<>();
		for(Ticket t : li) {
			JSONObject obj = new JSONObject();
			obj.put("ticketId", t.getTicketId());
			obj.put("title", t.getTitle());
			obj.put("createdDate", t.getCreatedDate());
			obj.put("createdTime", t.getCreatedTime());
			obj.put("reportedBy", t.getReportedBy());
			obj.put("assignedTo", t.getAssignedTo());
			obj.put("description", t.getDescription());
			obj.put("startDate", t.getStartDate());
			obj.put("endDate", t.getEndDate());
			
			
			if(t.getCurrentState().equalsIgnoreCase("Pending")) {
				obj.put("currentState", "Pending");
				pending.put(obj);
			}else if(t.getCurrentState().equalsIgnoreCase("To-Do")) {
				obj.put("currentState", "To-Do");
				toDo.put(obj);
			}else if(t.getCurrentState().equalsIgnoreCase("Done")) {
				obj.put("currentState", "Done");
				done.put(obj);
			}else if(t.getCurrentState().equalsIgnoreCase("Delivered")) {
				obj.put("currentState", "Delivered");
				delivered.put(obj);
			}
		}
		
		
//		fetch project Info
		Project p = projectRep.fetchByProjectId(projectId);
		ob.put("ProjectId", p.getId());
		ob.put("ProjectName", p.getProjectName());
		
//		add the tickets as per the slots
		JSONArray arr = new JSONArray();
		for(int i=0; i<4; i++) {
			JSONObject obj = new JSONObject();
			if(i == 0) {
//				toDo
				obj.put("title", "To-Do");
				obj.put("items", toDo);
			}else if(i == 1) {
//				pending
				obj.put("title", "Pending");
				obj.put("items", pending);
			}else if(i == 2) {
//				done
				obj.put("title", "Done");
				obj.put("items", done);
			}else if(i == 3) {
//				delivered
				obj.put("title", "Delivered");
				obj.put("items", delivered);
			}
			arr.put(obj);
		}
		ob.put("Tickets", arr);
		
		
		
		return ob;
	}

	@Override
	public void statusUpdater(StatusUpdater status) {
		
//		ticketId / status
		HashMap<Integer, String> map = new HashMap<>();
		for(Item i : status.getTicketInfo()) {
			String sta = i.getTitle();
			for(Ticket t : i.getItems()) {
				map.put(t.getTicketId(), sta);
			}
		}
		
		
		List<Ticket> li = this.ticketRep.fetchTicketsFromProjectId(status.getProjectId());
		for(Ticket t1 : li) {
			String st = map.get(t1.getTicketId());
			if(st.equalsIgnoreCase(t1.getCurrentState())) {
				continue;
			}else {
				t1.setCurrentState(st);
//				myDocumentRepository.save(myDocumentToUpdate);
				ticketRep.save(t1);
				
			}
		}

		
		
	}

	@Override
	public List<Project> fetchCollabProjects(CollabReq collabReq) {
		
		String[] arr = collabReq.getEncodedIds().split(",");
		
		List<Project> res = new ArrayList<>();
		
			for(String s : arr) {
				if(s.equals("")) {
					break;
				}
				int id = Integer.parseInt(s);
				res.add(projectRep.fetchProjectFromId(id));
			}
		
		
		return res;
	}

	@Override
	public JSONArray fetchCalenderView(Integer projId) {
		List<Ticket> li = ticketRep.fetchTicketsFromProjectId(projId);
		
		JSONArray res = new JSONArray();
		for(Ticket t : li) {
			JSONObject obj = new JSONObject();
			obj.put("text", t.getTitle());
			obj.put("startDate", t.getStartDate());
			obj.put("endDate", t.getEndDate());
			obj.put("allDay", true);
			
			Integer roomId = -1;
			if(t.getCurrentState().equals("To-Do")) {
				roomId = 1;
			}else if(t.getCurrentState().equals("Pending")) {
				roomId = 2;
			}else if(t.getCurrentState().equals("Done")) {
				roomId = 3;
			}else if(t.getCurrentState().equals("Delivered")) {
				roomId = 4;
			}
			
			obj.put("roomId", roomId);
			res.put(obj);
			
		}
		
		return res;
	}

	@Override
	public JSONObject deleteTicket(Integer ticketId) {
		// TODO Auto-generated method stub
		ticketRep.deleteById(ticketId);
		JSONObject obj = new JSONObject();
		obj.put("status", "s");
		return obj;
	}

	@Override
	public JSONObject updateTicket(Integer ticketId, Ticket ticket) {
		// TODO Auto-generated method stub
		Ticket t = ticketRep.findById(ticketId).get();
		//t.setTitle(ticket.getTitle());
		t.setDescription(ticket.getDescription());
		t.setAssignedTo(ticket.getAssignedTo());
		t.setStartDate(ticket.getStartDate());
		t.setEndDate(ticket.getEndDate());
		
		
		ticketRep.save(t);
		
		JSONObject obj = new JSONObject();
		obj.put("status", "S");
		
		return obj;
	}
	
	

}
