package com.nkr4m.userService.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nkr4m.userService.entity.User;
import com.nkr4m.userService.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepo;

	@Override
	public JSONObject registerUser(User user) {
		
		JSONObject obj = new JSONObject();
		
		try {
			
			User u = userRepo.findByEmailId(user.getEmailId());
			if(u != null) {
				obj.put("status", "F");
				obj.put("message", "email already exists!");
				return obj;
				
			}
			
			user.setCreated(LocalDate.now());
			userRepo.save(user);
			obj.put("status", "S");
			obj.put("message", "SC200");
		} catch (Exception e) {
			// TODO: handle exception
			obj.put("status", "F");
			obj.put("message", "ER500");
			return obj;
			
		}
		

		
		
		return obj;
	}

	@Override
	public JSONObject loginUser(User user) {
		JSONObject obj = new JSONObject();
		try {
			
			User u = userRepo.findByEmailId(user.getEmailId());
			if(u == null) {
				obj.put("status", "F");
				obj.put("message", "Invalid Credentials!");
				return obj;
			}else {
				
				if(u.getPassword().equals(user.getPassword())) {
					obj.put("status", "S");
					obj.put("message", "Successfull login!");
					obj.put("org", u.getOrg());
					obj.put("profession", u.getProfession());
					obj.put("name", u.getName());
					return obj;
				}else {
					obj.put("status", "F");
					obj.put("message", "Invalid Credentials!");
					return obj;
				}
				
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		return obj;
	}

	@Override
	public JSONObject userExists(String emailId) {
		// TODO Auto-generated method stub
		User u = userRepo.findByEmailId(emailId);
		JSONObject res = new JSONObject();
		if(u != null) {
			res.put("status", "S");
			return res;
		}else {
			res.put("status", "F");
			return res;
		}

	}
	
}
