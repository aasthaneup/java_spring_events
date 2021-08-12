package com.assignment.events.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.assignment.events.models.Event;
import com.assignment.events.models.EventAttendee;
import com.assignment.events.models.User;

public interface EventAttendeeRepository extends CrudRepository<EventAttendee, Long>{

	EventAttendee save(EventAttendee ea);
	List<EventAttendee> findAll();
	void deleteById(Long id);
	//	void delete(EventAttendee ea);
	EventAttendee findByAttendeeAndEvent(User user, Event event);
}
