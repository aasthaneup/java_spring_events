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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull(message="Please enter a first name!")
	@Size(min=2, max=100, message="First name must be 2-100 characters long!")
	private String firstName;

	@NotNull(message="Please enter a last name!")
	@Size(min=2, max=100, message="Last name must be 2-100 characters long!")
	private String lastName;

	@NotNull(message="Please enter an email!")
	@Email(message="Please enter a valid email address!")
	@Size(min=2, max=100, message="Email must be between 2-100 characters!")
	private String email;

	@NotNull(message="Please enter your location!")
	@Size(min=2, message="Location must be at least 2 characters long!")
	private String location;

	@NotNull(message="Please enter you state!")
	@Size(min=2, message="State must be at least 2 characters long!")
	private String state;

	@NotNull(message="Please enter a password!")
	@Size(min=5, message="Password must be at least 5 characters long!")
	private String password;

	@Transient
	private String passwordConfirmation;

	@Column(updatable=false)
	@DateTimeFormat(pattern="yy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern="yy-MM-dd")
	private Date updatedAt;

	//    one to many field: one user can be creator of many events
	@OneToMany(mappedBy="host", fetch = FetchType.LAZY)
	private List<Event> events;


	//    many to many field: one event can have many attendees one person can attend many events
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "events_attendees", 
			joinColumns = @JoinColumn(name = "user_id"), 
			inverseJoinColumns = @JoinColumn(name = "event_id")
			)     
	private List<Event> joinedEvents;

	//one to many field: one user can write many messages
	@OneToMany(mappedBy="commenter", fetch = FetchType.LAZY)
	private List<Message> comments;

	//    CONSTRUCTORS
	//    ---------------------------------

	public User() {
	}

	public User(String fname, String lname, String email, String location, String state, String password) {
		this.firstName = fname;
		this.lastName = lname;
		this.email = email;
		this.location = location;
		this.state = state;
		this.password = password;
	}

	//	PrePersist and PreUpdate methods
	//	-------------------------------------

	@PrePersist
	protected void onCreate(){
		this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
		this.updatedAt = new Date();
	}

	//	GETTERS AND SETTERS
	//	------------------------------------

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}


	public List<Event> getJoinedEvents() {
		return joinedEvents;
	}

	public void setJoinedEvents(List<Event> joinedEvents) {
		this.joinedEvents = joinedEvents;
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

	public List<Message> getComments() {
		return comments;
	}

	public void setComments(List<Message> comments) {
		this.comments = comments;
	}
}
