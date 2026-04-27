package com.hackerrank.gevents.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.gevents.model.Event;
import com.hackerrank.gevents.repository.EventRepository;

@RestController
@RequestMapping("")
public class EventController {
	
	@Autowired
	private EventRepository repo;
	
	@PostMapping("/events")
	@ResponseStatus(HttpStatus.CREATED)
	public Event create(@RequestBody Event event) {
		return repo.save(event);
	}
	
	@GetMapping("/events")
	@ResponseStatus(HttpStatus.OK)
	public List<Event> getAll() {
		return repo.findAll(Sort.by(Sort.Direction.ASC,"id"));
	}
	
	@GetMapping("/events/{eventId}")
	public ResponseEntity<Event> getById(@PathVariable Integer eventId){
		Optional<Event> e=repo.findById(eventId);
		if(e.isPresent()) {
		return new ResponseEntity<Event>(e.get(),HttpStatus.OK);}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/repos/{repoId}/events")
	public ResponseEntity<List<Event>> getByRepoId(@PathVariable Integer repoId){
		List<Event> e=repo.findByRepoIdEquals(repoId);
		if(e.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
		return new ResponseEntity<List<Event>>(e,HttpStatus.OK);
		
	}
	
}
