package com.assignment.events.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.assignment.events.models.Event;
import com.assignment.events.models.EventAttendee;
import com.assignment.events.models.Message;
import com.assignment.events.models.User;
import com.assignment.events.repositories.EventAttendeeRepository;
import com.assignment.events.repositories.EventRepository;
import com.assignment.events.repositories.MessageRepository;

@Service
public class EventService {

	private final EventRepository eventRepository;
	private final EventAttendeeRepository eventAttendeeRepository;
	private final MessageRepository messageRepository;
	
	public EventService(
			EventRepository eventRepository, 
			EventAttendeeRepository eventAttendeeRepository, 
			MessageRepository messageRepository) {
		this.eventRepository = eventRepository;
		this.eventAttendeeRepository = eventAttendeeRepository;
		this.messageRepository = messageRepository;
	}
	
	public List<Event> allEvents(){
		return eventRepository.findAll();
	}
	
	public Event findEvent(Long id) {
		Optional<Event> optEvent = eventRepository.findById(id);
		if(optEvent.isPresent()) {
			return optEvent.get();
		} else {
			return null;
		}
	}
	
//	find all events in a particular state:
	
	public List<Event> findEventsInState(String state){
		return eventRepository.findByState(state);
	}
	
//	find all events in states other than the one specified:

	public List<Event> findEventsOutOfState(String state){
		return eventRepository.findByStateNot(state);
	}
	
	public Event saveEvent(Event event) {
		return eventRepository.save(event);
	}
	
	public void deleteEvent(Long id) {
		eventRepository.deleteById(id);
	}
	
	public List<Event> findAllEventsAttendees(){
		return eventRepository.findAll();
	}
	
	public EventAttendee saveRelationship(EventAttendee ea) {
		return eventAttendeeRepository.save(ea);
	}
	
	public EventAttendee findEventAttendeeRelationshipObject(User user, Event event) {
		return eventAttendeeRepository.findByAttendeeAndEvent(user, event);	
	}
	
	public void deleteRelationship(EventAttendee ea) {
		eventAttendeeRepository.delete(ea);;
	}
	
	public Message saveMessage(Message msg) {
		return messageRepository.save(msg);
	}
	
}
