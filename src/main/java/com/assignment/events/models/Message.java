package com.assignment.events.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="messages")
public class Message {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull(message="You cannot submit an empty comment!")
	@Size(min=2, message="The name must be atleast 2 characters long!")
	private String comment;

	@Column(updatable=false)
	@DateTimeFormat(pattern="yy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern="yy-MM-dd")
	private Date updatedAt;

	//  many to one field: many messages can be written by one user
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User commenter;

	//  many to one field: many messages can be written for one event
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="event_id")
	private Event event;

	//    CONSTRUCTORS
	//    ---------------------------------

	public Message() {

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

	//	GETTERS AND SETTERS
	//	-----------------------------


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public User getCommenter() {
		return commenter;
	}

	public void setCommenter(User commenter) {
		this.commenter = commenter;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Message(String comment) {
		this.comment = comment;
	}




}
