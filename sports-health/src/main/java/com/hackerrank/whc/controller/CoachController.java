package com.hackerrank.whc.controller;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.whc.model.Coach;
import com.hackerrank.whc.model.Customer;
import com.hackerrank.whc.repository.CoachRepository;

@RestController
@RequestMapping("/api/coach")
public class CoachController {

    final CoachRepository coachRepository;

    public CoachController(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }
    
    @PostMapping()
    public ResponseEntity<Coach> create(@RequestBody Coach cust){
    	Coach c=coachRepository.save(cust);
    	return new ResponseEntity<Coach>(c,HttpStatus.CREATED);
    	
    }
    
    @GetMapping()
    public ResponseEntity<List<Coach>> getAll(Coach cust){
    	List<Coach> c=coachRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
    	return new ResponseEntity<List<Coach>>(c,HttpStatus.OK);	
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Coach> getById(@PathVariable Integer id){
    	Coach c=coachRepository.findById(id).orElse(null);
    	return new ResponseEntity<Coach>(c,HttpStatus.OK);	
    }
    
}
