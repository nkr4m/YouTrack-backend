package com.nkr4m.userService.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.nkr4m.userService.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	User findByEmailId(String emailId);
}
