package com.assignment.events.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.events.models.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long>{
	
	List<Event> findAll();
	void deleteById(Long id);
	Event save(Event event);
	Optional<Event> findById(Long id);
	List<Event> findByState(String state);
	List<Event> findByStateNot(String state);
}
