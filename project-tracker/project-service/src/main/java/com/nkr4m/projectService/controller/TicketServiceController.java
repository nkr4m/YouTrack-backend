package com.nkr4m.projectService.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.nkr4m.projectService.document.Ticket;
import com.nkr4m.projectService.dto.StatusUpdater;
import com.nkr4m.projectService.service.ProjectService;

@CrossOrigin
@RestController
public class TicketServiceController {
	
	@Autowired
	ProjectService projectService;
	
	
	@PostMapping("add-new-ticket")
	public ResponseEntity<?> addTicket(@RequestBody Ticket ticket){
		this.projectService.addTicket(ticket);
		JSONObject obj = new JSONObject();
		obj.put("status", "S");
		return new ResponseEntity<>(obj.toMap(), HttpStatus.OK);
	}
	
	@GetMapping("fetch-ticket/{projectId}")
	public ResponseEntity<Object> fetchTickets(@PathVariable Integer projectId){
		JSONObject data = this.projectService.fetchTickets(projectId);
		return new ResponseEntity<Object>(data.toMap(), HttpStatus.OK);
	}
	
	@PostMapping("status-updater")
	public ResponseEntity<Object> statusUpdater(@RequestBody StatusUpdater status){
		JSONObject data = new JSONObject();
		projectService.statusUpdater(status);
		data.put("status", "S");
		return new ResponseEntity<Object>(data.toMap(), HttpStatus.OK);
	}
	
	@GetMapping("fetch-calender-view/{projId}")
	public ResponseEntity<Object> fetchCalenderView(@PathVariable Integer projId){
		
			JSONArray data = this.projectService.fetchCalenderView(projId);
		
		return new ResponseEntity<Object>(data.toList(), HttpStatus.OK);
	}
	
	@DeleteMapping("delete-ticket/{ticketId}")
	public ResponseEntity<Object> deleteTicketId(@PathVariable Integer ticketId){
		
		JSONObject data = this.projectService.deleteTicket(ticketId);
	
		return new ResponseEntity<Object>(data.toMap(), HttpStatus.OK);
	}
	
	@PutMapping("update-ticket/{ticketId}")
	public ResponseEntity<Object> UpdateTicketId(@PathVariable Integer ticketId, @RequestBody Ticket ticket){
		
		JSONObject data = this.projectService.updateTicket(ticketId, ticket);
		
		return new ResponseEntity<Object>(data.toMap(), HttpStatus.OK);
	}
	
	

}
