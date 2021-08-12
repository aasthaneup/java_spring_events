package com.assignment.events.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.assignment.events.models.Event;
import com.assignment.events.models.EventAttendee;
import com.assignment.events.models.Message;
import com.assignment.events.models.User;
import com.assignment.events.services.EventService;
import com.assignment.events.services.UserService;
import com.assignment.events.validators.UserValidator;


@Controller
public class MainController {
	private final UserService userService;
	private final EventService eventService;
	private final UserValidator userValidator;

	public MainController(UserService userService, EventService eventService, UserValidator userValidator) {
		this.userService = userService;
		this.eventService = eventService;
		this.userValidator = userValidator;
	}

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("user", new User());
		return "index.jsp";
	}

	//	Route for registering a new user
	//	-----------------------------------------

	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		userValidator.validate(user, result);
		if(result.hasErrors()) {
			return "index.jsp";
		} else {
			User us = userService.registerUser(user);
			session.setAttribute("userId", us.getId());
			return "redirect:/events";
		}
	}

	//	Route for registering a new user
	//	-----------------------------------------

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginUser(
			@RequestParam("email") String email, 
			@RequestParam("password") String password, Model model, 
			HttpSession session, 
			RedirectAttributes redirect) {

		//	authentic login
		if(userService.authenticateUser(email, password)) {
			User us = userService.findByEmail(email);
			session.setAttribute("userId", us.getId());
			System.out.println("logged user :"+us.getId());
			return "redirect:/events";
		}
		else {
			//	not authentic login
			redirect.addFlashAttribute("error", "Invalid Login Credential(s)!");
			return "redirect:/";
		}
	}	

	//	get route to the events page
	//	------------------------------------

	@RequestMapping("/events")
	public String dashboard(Model model, HttpSession session, RedirectAttributes redirect) {
		//		allow access to dashboard only if user is in session
		if(session.getAttribute("userId") != null){		
			model.addAttribute("event", new Event());
			User logged_user = userService.findUserById((Long) session.getAttribute("userId"));
			model.addAttribute("logged_user", logged_user);
			model.addAttribute("eventsInState", eventService.findEventsInState(logged_user.getState()));
			model.addAttribute("eventsOutState", eventService.findEventsOutOfState(logged_user.getState()));
			return "dashboard.jsp";
		}
		//		if user not in session, flash error message and block access to dashboard
		else {
			redirect.addFlashAttribute("accessError", "*** Please login or register before attempting to access the dashboard!! ***");
			return "redirect:/";

		}
	}
	
	//	Route for adding a new event
	//	-----------------------------------------

	@RequestMapping(value="/events", method=RequestMethod.POST)
	public String register(@Valid @ModelAttribute("event") Event event, BindingResult result, HttpSession session, Model model) {
	
//		model.addAttribute("event", new Event());
		
		User logged_user = userService.findUserById((Long) session.getAttribute("userId"));
		model.addAttribute("logged_user", logged_user);
		model.addAttribute("eventsInState", eventService.findEventsInState(logged_user.getState()));
		model.addAttribute("eventsOutState", eventService.findEventsOutOfState(logged_user.getState()));
		if(result.hasErrors()) {
			return "dashboard.jsp";
		} else {
			eventService.saveEvent(event);
			return "redirect:/events";
		}
	}
	
	
//	joining the user to an event
//	---------------------
	@RequestMapping(value="/events/{id}/join", method=RequestMethod.POST)
	public String joinAsAttendee(@RequestParam("attendeeId") Long attendeeId, @RequestParam("eventId") Long eventId) {
		User attendee = userService.findUserById(attendeeId);
		Event joinEvent = eventService.findEvent(eventId);
		EventAttendee eventAttendee = new EventAttendee(attendee, joinEvent);
		eventService.saveRelationship(eventAttendee);
		return "redirect:/events";
	}

	
	//	canceling the user from an event
//	--------------------------------
	
	@RequestMapping(value="/events/{id}/cancel", method=RequestMethod.POST)
	public String cancelAttendee(@RequestParam("attendeeId") Long attendeeId, @RequestParam("eventId") Long eventId) {
		User attendee = userService.findUserById(attendeeId);
		Event joinEvent = eventService.findEvent(eventId);
		EventAttendee eveAttObject = eventService.findEventAttendeeRelationshipObject(attendee, joinEvent);
		eventService.deleteRelationship(eveAttObject);
		return "redirect:/events";
	}
	
//	edit page route
//	--------------------------------------
	
	@RequestMapping(value="/events/{id}/edit")
	public String editPage(@PathVariable("id") Long id, Model model, HttpSession session, RedirectAttributes redirect) {
		if(session.getAttribute("userId") != null){	
		model.addAttribute("event", eventService.findEvent(id));
		return "edit.jsp";
		}
		else {
			redirect.addFlashAttribute("accessError", "*** Please login or register before attempting to access the dashboard!! ***");
			return "redirect:/";
		}
	}
	
//	update route for event:
//	----------------------------------------
	
	@RequestMapping(value="/events/{id}/edit", method=RequestMethod.PUT)
	public String updateEvent(@Valid @ModelAttribute("event") Event event, BindingResult result) {
		if(result.hasErrors()) {
			return "edit.jsp";
		} else {
			eventService.saveEvent(event);
			return "redirect:/events";
		}
	}
	
//	view event page route
//	--------------------------------------
	
	@RequestMapping("/events/{id}")
	public String viewPage(@PathVariable("id") Long id, Model model, HttpSession session, RedirectAttributes redirect) {
		
		if(session.getAttribute("userId") != null){	
		Event eve = eventService.findEvent(id);
		model.addAttribute("count", eve.getAttendees().size());
		model.addAttribute("event", eve);
		model.addAttribute("message", new Message());
		User logged_user = userService.findUserById((Long) session.getAttribute("userId"));
		model.addAttribute("logged_user", logged_user);
		model.addAttribute("messages", eventService.findEvent(id).getMessages());
		return "view.jsp";
		}
		else {
			redirect.addFlashAttribute("accessError", "*** Please login or register before attempting to access the dashboard!! ***");
			return "redirect:/";
		}
	}
	
//	Posting a comment
//	------------------------------
	
	@RequestMapping(value="/message/{id}", method=RequestMethod.POST)
	public String postMessage(@Valid @ModelAttribute("message") Message message, BindingResult result, Model model, @PathVariable("id") Long id) {
		if(result.hasErrors()) {
			Event eve = eventService.findEvent(id);
			model.addAttribute("count", eve.getAttendees().size());
			model.addAttribute("event", eve);
			return "view.jsp";
		} else {
			eventService.saveMessage(message);
			return "redirect:/events/"+id;
		}
		
		
	}
	
//	delete event route
//	---------------------------------
	
	@RequestMapping(value="/events/{id}", method=RequestMethod.DELETE)
	public String deleteEvent(@PathVariable("id") Long id) {
		eventService.deleteEvent(id);
		return "redirect:/events";
	}
	

	//	logout and clear session:
	//	-----------------------------------

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

}
