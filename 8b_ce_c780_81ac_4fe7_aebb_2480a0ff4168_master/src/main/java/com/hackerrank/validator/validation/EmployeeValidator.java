package com.hackerrank.validator.validation;

import com.hackerrank.validator.model.Employee;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class EmployeeValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Employee.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object employeeObject, Errors errors) {
       Employee emp= (Employee) employeeObject;
       
       if(emp.getFullName()==null || emp.getFullName().trim().isEmpty()) {
    	   errors.rejectValue("fullName","","The fullName is a mandatory field");
       }
       
       if(emp.getMobileNumber()==null || emp.getMobileNumber()<1_000_000_000L || emp.getMobileNumber()>9_999_999_999L) {
    	   errors.rejectValue("mobileNumber","","The mobileNumber is a mandatory field");
       }
       if(emp.getEmailId()==null ||emp.getEmailId().isEmpty()) {
    	   errors.rejectValue("emailId","","The emailId is a mandatory field");
       }
       else if(!emp.getEmailId().contains("@")) {
    	   errors.rejectValue("emailId","","The emailId should be in a valid email format");
       }
       if(emp.getDateOfBirth()==null || emp.getDateOfBirth().isEmpty()) {
    	   errors.rejectValue("dateOfBirth","","The dateOfBirth is a mandatory field");
       }
       else {
    	  DateTimeFormatter format= DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	   try {
    		   LocalDate.parse(emp.getDateOfBirth(), format) ;
    		   }catch(Exception e) {
    		   errors.rejectValue("dateOfBirth","","The dateOfBirth should be in YYYY-MM-DD format");
    	   }
       }
       
    }
}
