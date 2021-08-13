package com.assignment.events.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="events")
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull(message="Please enter a name for the event!")
	@Size(min=2, max=255, message="The name must be 2-100 characters long!")
	private String name;
	@NotNull(message="Please enter a valid date!")
	@Future(message="You can not add an event with a past or present date!")
	@DateTimeFormat(pattern="yy-MM-dd")
	private Date date;
	@NotNull(message="Please enter the event location!")
	@Size(min=2, message="Location must be at least 2 characters long!")
	private String location;
	@NotNull(message="Please select the state in which the event is being held!")
	private String state;

	//  many to one field: many events can be created by one user
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User host;

	//  many to many field: one event can have many attendees one person can attend many events
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "events_attendees", 
			joinColumns = @JoinColumn(name = "event_id"), 
			inverseJoinColumns = @JoinColumn(name = "user_id")
			)     
	private List<User> attendees;

	//one to many field: one user can be creator of many events
	@OneToMany(mappedBy="event", fetch = FetchType.LAZY)
	private List<Message> messages;

	@Column(updatable=false)
	@DateTimeFormat(pattern="yy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern="yy-MM-dd")
	private Date updatedAt;

	//    CONSTRUCTORS
	//    ----------------
	public Event() {
	}
	public Event(String name, Date date, String location, String state) {
		this.name = name;
		this.date = date;
		this.location = location;
		this.state = state;
	}

	//	PrePersist and PreUpdate methods
	//	------------------------------------
	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}

	//	GETTERS ADN SETTERS
	//	-----------------------------
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public User getHost() {
		return host;
	}
	public void setHost(User host) {
		this.host = host;
	}
	public List<User> getAttendees() {
		return attendees;
	}
	public void setAttendees(List<User> attendees) {
		this.attendees = attendees;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}