package com.nkr4m.projectService.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.nkr4m.projectService.document.Ticket;

public interface TicketRepository extends MongoRepository<Ticket, Integer>{

	
	@Query("{projectLinkId : ?0}")
	List<Ticket> fetchTicketsFromProjectId(Integer projectId);
}
