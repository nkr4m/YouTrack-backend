package com.nkr4m.projectService.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nkr4m.projectService.document.Project;
import com.nkr4m.projectService.document.Ticket;
import com.nkr4m.projectService.dto.CollabReq;
import com.nkr4m.projectService.service.ProjectService;

@RestController
@CrossOrigin("*")
public class ProjectServiceController {
	
	@Autowired
	ProjectService projectService;
	
	@GetMapping("/")
	public ResponseEntity<String> test(){
		return new ResponseEntity<String>("project-service works fine!", HttpStatus.OK);
	}
	
	
	@GetMapping("/fetch-project/{emailId}")
	public ResponseEntity<?> fetchProjects(@PathVariable String emailId){
		List<Project> li = this.projectService.fetchProjects(emailId);
		return new ResponseEntity<>(li, HttpStatus.OK);
	}
	
	
	@PostMapping("/save-project/{emailId}")
	public ResponseEntity<Object> saveProject(@RequestBody Project project, @PathVariable String emailId){
		JSONObject obj = new JSONObject();
		obj.put("status", "S");
		this.projectService.saveProject(project, emailId);
		return new ResponseEntity<>(obj.toMap(), HttpStatus.OK);
	}
	
	@PostMapping("/fetch-collab-project/")
	public ResponseEntity<?> fetchCollabProjects(@RequestBody CollabReq collabReq){
		List<Project> li = this.projectService.fetchCollabProjects(collabReq);
		return new ResponseEntity<>(li, HttpStatus.OK);
	}
	


}
