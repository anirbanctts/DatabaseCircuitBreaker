package com.poc.sample.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poc.sample.dtos.CustomDTO;
import com.poc.sample.messages.SearchMessage;
import com.poc.sample.services.MyService;

@RestController
public class MyController {
	
	@Autowired
	private MyService myService;
	
	//This end point is for non transactional flow - to test multiple requests together
	@RequestMapping(value = "/sample/{id}", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<CustomDTO> searchByObject(@PathVariable int id, @RequestBody SearchMessage searchModel) throws Exception{
				
		List<SearchMessage> s = new ArrayList<SearchMessage>(); 
		s.add(searchModel);
		CustomDTO val = myService.getValue(s).get(0);
		
		return new ResponseEntity<>(val, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update/{id}", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<String> updateObjects(@PathVariable int id, @RequestBody SearchMessage searchModel) throws Exception {
		try
		{
			myService.updateValues(searchModel);
		}
		catch(RuntimeException ex)
		{
			System.out.println("ssss");
		}
		catch(Exception ex)
		{
			throw ex;
			//System.out.println("ssss");
		}
		return new ResponseEntity<String>("hi", HttpStatus.OK);
	}
}
