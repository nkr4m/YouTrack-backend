package com.nkr4m.notificationService.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nkr4m.notificationService.entity.CollabProject;
import com.nkr4m.notificationService.entity.Project;
import com.nkr4m.notificationService.entity.UserResponse;
import com.nkr4m.notificationService.service.CollabProjectService;


@CrossOrigin
@RestController
public class UserNotificationController {
	
	@Autowired
	CollabProjectService service;
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("view-notification/{userEmailId}")
	public ResponseEntity<Object> viewUserNotification(@PathVariable String userEmailId) {
		JSONArray li = service.viewUserNotification(userEmailId);
		return new ResponseEntity<Object>(li.toList(), HttpStatus.OK);
	}
	
	@PostMapping("/send-invite")
	public Object sendCollabInvite(@RequestBody CollabProject data) {
		JSONObject obj = new JSONObject();
		
		
//		check if the user exists
		UserResponse res = restTemplate.getForObject("http://localhost:8081/user-exists/" + data.getUserEmailId(), UserResponse.class);
		if(res.getStatus().equals("F")) {
			obj.put("Status", "F");
			return new ResponseEntity<>(obj.toMap(), HttpStatus.OK);
		}
		
		
		
		try {
			service.sendCollabInvite(data);
			obj.put("Status", "S");
			return new ResponseEntity<>(obj.toMap(), HttpStatus.OK);
		}catch(Exception e) {
			obj.put("Status", "F");
		}
		return new ResponseEntity<>(obj.toMap(), HttpStatus.OK);
	}
	
	@PostMapping("/invite-action/{inviteId}/{action}")
	public Object collabInviteAction(@PathVariable Integer inviteId, @PathVariable String action) {
		JSONObject obj = new JSONObject();
		try {
			service.collabInviteAction(inviteId, action);
			obj.put("Status", "S");
			return new ResponseEntity<>(obj.toMap(), HttpStatus.OK);
		}catch(Exception e) {
			obj.put("Status", "F");
		}
		return new ResponseEntity<>(obj.toMap(), HttpStatus.OK);
	}
	
	@PostMapping("/fetch-collab-projects/{userEmail}")
	public ResponseEntity<Object> fetchCollabProjects(@PathVariable String userEmail) {
		JSONArray li = new JSONArray();
		
		
//		fetched collab ids of the user
		List<Integer> collabProjList = service.fetchCollabProjects(userEmail);
		System.out.println(collabProjList);
		String encodedIds = "";
		for(Integer i : collabProjList) {
			if(encodedIds.length() == 0) {
				encodedIds =  i + "";
				continue;
			}
			encodedIds = encodedIds + "," + i;
		}
		
//		now we fetch the project details from the collab ids
		// create request body
		JSONObject request = new JSONObject();
		request.put("encodedIds", encodedIds);
		

		// set headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

		// send request and parse result
		ResponseEntity<List<Project>> response = restTemplate
		  .exchange("http://localhost:8082/fetch-collab-project/", HttpMethod.POST, entity,  new ParameterizedTypeReference<List<Project>>() {});
		
		List<Project> li2 = response.getBody();
		
		return new ResponseEntity<Object>(li2, HttpStatus.OK);
	}
	
	@GetMapping("/fetch-proj-collabs/{projId}")
	public ResponseEntity<?> fetchProjectCollab(@PathVariable Integer projId){
		JSONArray obj = service.fetchProjectCollab(projId);
		return new ResponseEntity<Object>(obj.toList(), HttpStatus.OK);
	}
	

}
