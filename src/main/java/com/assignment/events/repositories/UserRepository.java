package com.assignment.events.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.events.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	User findByEmail(String email);
	List<User> findAll();
	void deleteById(Long id);
	User save(User user);
	Optional<User> findById(Long id);
	
}
