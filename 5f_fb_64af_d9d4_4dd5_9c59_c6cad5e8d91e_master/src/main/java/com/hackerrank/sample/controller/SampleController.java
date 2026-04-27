package com.hackerrank.sample.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hackerrank.sample.dto.FilteredProducts;
import com.hackerrank.sample.dto.SortedProducts;

@RestController
public class SampleController {

	
	   final String uri = "https://jsonmock.hackerrank.com/api/inventory";
	   RestTemplate restTemplate = new RestTemplate();
	   String result = restTemplate.getForObject(uri, String.class);			
	   JSONObject root = new JSONObject(result);
	   
	   JSONArray data = root.getJSONArray("data");
	   
	   
		
		@CrossOrigin
		@GetMapping("/filter/price/{initial_price}/{final_price}")  
		private ResponseEntity< ArrayList<FilteredProducts> > filtered_books(@PathVariable("initial_price") int init_price , @PathVariable("final_price") int final_price)   
		{  
			
			
			try {
				ArrayList<FilteredProducts> books = new ArrayList<FilteredProducts>();
				for(int i=0;i<data.length();i++) {
					JSONObject product=data.getJSONObject(i);
					int price=product.getInt("price");
					
					if(price>=init_price && price<final_price) {
						if(product.has("barcode") && !product.isNull("barcode")) {
							String barcode=product.getString("barcode");
							books.add(new FilteredProducts(barcode));
						}
					}
				}
				if(books.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				}
				
				
				
			return new ResponseEntity<ArrayList<FilteredProducts>>(books, HttpStatus.OK);		   			    
			}catch(Exception E)
				{
	   	System.out.println("Error encountered : "+E.getMessage());
	    return new ResponseEntity<ArrayList<FilteredProducts>>(HttpStatus.NOT_FOUND);
				}
			
		}  
		
		
		@CrossOrigin
		@GetMapping("/sort/price")  
		private ResponseEntity<SortedProducts[]> sorted_books()   
		{  
			try {
				List<JSONObject>productList=new ArrayList<>();
				for(int i=0;i<data.length();i++) {
					productList.add(data.getJSONObject(i));
				}
				productList.sort((a,b)-> Integer.compare(a.getInt("price"), b.getInt("price")));
				
				SortedProducts[] ans=new SortedProducts[data.length()];
				for(int i=0;i<productList.size();i++) {
					JSONObject product=productList.get(i);
					ans[i]=new SortedProducts(product.getString("barcode"));
				}
		         
		        return new ResponseEntity<SortedProducts[]>(ans,HttpStatus.OK);
			    
			}catch(Exception E)
				{
	   	System.out.println("Error encountered : "+E.getMessage());
	    return new ResponseEntity<SortedProducts[]>(HttpStatus.NOT_FOUND);
				}
			
		}  
		
		
	
}
