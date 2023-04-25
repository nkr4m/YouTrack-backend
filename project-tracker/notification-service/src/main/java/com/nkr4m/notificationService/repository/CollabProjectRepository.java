package com.nkr4m.notificationService.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nkr4m.notificationService.entity.CollabProject;

public interface CollabProjectRepository extends CrudRepository<CollabProject, Integer> {
	List<CollabProject> findByUserEmailId(String emailId);
	List<CollabProject> findByProjectId(Integer projectId);
	
	
}
