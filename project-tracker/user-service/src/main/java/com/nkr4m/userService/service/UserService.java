package com.nkr4m.userService.service;

import org.json.JSONObject;

import com.nkr4m.userService.entity.User;

public interface UserService {

	JSONObject registerUser(User user);

	JSONObject loginUser(User user);

	JSONObject userExists(String emailId);

}
