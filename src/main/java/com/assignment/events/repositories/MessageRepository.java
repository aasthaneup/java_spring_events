package com.assignment.events.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.events.models.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long>{
	Message save(Message msg);
}