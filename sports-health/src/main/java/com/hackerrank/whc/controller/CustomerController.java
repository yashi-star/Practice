package com.hackerrank.whc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.whc.model.Customer;
import com.hackerrank.whc.repository.CustomerRepository;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    
    @PostMapping()
    public ResponseEntity<Customer> create(@RequestBody Customer cust){
    	Customer c=customerRepository.save(cust);
    	return new ResponseEntity<Customer>(c,HttpStatus.CREATED);
    	
    }
    
    @GetMapping()
    public ResponseEntity<List<Customer>> getAll(Customer cust){
    	List<Customer> c=customerRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
    	return new ResponseEntity<List<Customer>>(c,HttpStatus.OK);	
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getById(@PathVariable Integer id){
    	Customer c=customerRepository.findById(id).orElse(null);
    	return new ResponseEntity<Customer>(c,HttpStatus.OK);	
    }
    
}
