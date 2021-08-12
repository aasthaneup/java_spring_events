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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="events_attendees")
public class EventAttendee {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(updatable=false)
    @DateTimeFormat(pattern="yy-MM-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yy-MM-dd")
    private Date updatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User attendee;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="event_id")
    private Event event;
    
//    CONSTRUCTORS
//    ------------------------------
    
    public EventAttendee() {
    	
    }
    
    public EventAttendee(User attendee, Event event) {
    	this.event = event;
    	this.attendee = attendee;
    }
    
    //    PrePersist and PreUpdate
//    ------------------------------
    
    @PrePersist
    protected void onCreate() {
    	this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate() {
    	this.updatedAt = new Date();
    }

//    GETTERS AND SETTERS
//    ----------------------------------
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public User getAttendee() {
		return attendee;
	}

	public void setAttendee(User attendee) {
		this.attendee = attendee;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}
}
