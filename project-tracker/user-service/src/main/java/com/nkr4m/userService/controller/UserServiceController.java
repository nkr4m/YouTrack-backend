package com.nkr4m.userService.controller;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nkr4m.userService.entity.User;
import com.nkr4m.userService.service.UserService;

@RestController
@CrossOrigin
public class UserServiceController {
	
	@Autowired
	UserService service;
	
	@GetMapping("/")
	public ResponseEntity<String> test(){
		return new ResponseEntity<>("user-service works!", HttpStatus.OK);
	}
	
	@PostMapping("register-user")
	public ResponseEntity<Object> register(@RequestBody User user){
		JSONObject obj = service.registerUser(user);
		return new ResponseEntity<Object>(obj.toMap(), HttpStatus.OK);
	}
	
	@PostMapping("login-user")
	public ResponseEntity<Object> login(@RequestBody User user){
		JSONObject obj = service.loginUser(user);
		return new ResponseEntity<Object>(obj.toMap(), HttpStatus.OK);
	}
	
	@GetMapping("user-exists/{emailId}")
	public ResponseEntity<Object> userExists(@PathVariable String emailId){
		JSONObject obj = service.userExists(emailId);
		return new ResponseEntity<Object>(obj.toMap(), HttpStatus.OK);
	}
	
	
	
	
	
}
