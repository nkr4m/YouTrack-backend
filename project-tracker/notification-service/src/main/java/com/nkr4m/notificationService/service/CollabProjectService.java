package com.nkr4m.notificationService.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nkr4m.notificationService.entity.CollabProject;
import com.nkr4m.notificationService.repository.CollabProjectRepository;

@Service
@Transactional
public class CollabProjectService {

	@Autowired
	CollabProjectRepository collabRep;
	
	
	public JSONArray viewUserNotification(String userEmailId) {
		// TODO Auto-generated method stub
		JSONArray res = new JSONArray();
		List<CollabProject> li = collabRep.findByUserEmailId(userEmailId);
		for(CollabProject c : li) {
			
			if(!c.getActionTaken().equals("P")) {
				continue;
			}
			
			JSONObject obj = new JSONObject();
			obj.put("collabId", c.getId());
			obj.put("projectName", c.getProjectName());
			obj.put("sender", c.getSender());
			obj.put("projectId", c.getProjectId());
			obj.put("role", c.getRole());
			res.put(obj);
		}
		
		return res;
	}

	public void sendCollabInvite(CollabProject data) {
		// TODO Auto-generated method stub
		
		CollabProject obj = new CollabProject();
		obj.setProjectId(data.getProjectId());
		obj.setActionTaken("P");
		obj.setRole(data.getRole());
		obj.setUserEmailId(data.getUserEmailId());
		obj.setProjectName(data.getProjectName());
		obj.setSender(data.getSender());
		
		collabRep.save(obj);
		
	}

	public void collabInviteAction(Integer inviteId, String action) {
		// TODO Auto-generated method stub
		collabRep.findById(inviteId).get().setActionTaken(action);
		
	}

	public List<Integer> fetchCollabProjects(String userEmail) {
		// TODO Auto-generated method stub
				List<CollabProject> li = collabRep.findByUserEmailId(userEmail);
					List<Integer> res = new ArrayList<>();
				for(int i=0; i<li.size(); i++) {
					if(li.get(i).getActionTaken().equals("A")) {
						res.add(li.get(i).getProjectId());
					}
				}
				return res;
	}

	public JSONArray fetchProjectCollab(Integer projId) {
		// TODO Auto-generated method stub
		List<CollabProject> li = collabRep.findByProjectId(projId);
		JSONArray res = new JSONArray();
		for(CollabProject c : li) {
			
			if(!c.getActionTaken().equals("A")) {
				continue;
			}
			
			JSONObject o = new JSONObject();
			o.put("email", c.getUserEmailId());
			o.put("role", c.getRole());
			res.put(o);
		}
		return res;
	}

}
