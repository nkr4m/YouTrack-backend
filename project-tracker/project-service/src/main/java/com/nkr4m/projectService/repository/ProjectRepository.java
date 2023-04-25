package com.nkr4m.projectService.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.nkr4m.projectService.document.Project;

public interface ProjectRepository extends MongoRepository<Project, Integer>{
	
	@Query("{ owner: ?0 }")
	public List<Project> fetchProjectFormUserId(String emailId);
	
	@Query("{id: ?0}")
	public Project fetchByProjectId(Integer projectId);
	
	@Query("{ _id: ?0 }")
	public Project fetchProjectFromId(Integer id);
	
	

}
