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
	
	@RequestMapping(value = "/sample/{id}", produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<CustomDTO> searchByObject(@PathVariable int id, @RequestBody SearchMessage searchModel) throws Exception{
		
		// Initialize Hystrix context
		//HystrixRequestContext context = HystrixRequestContext.initializeContext();

		// HystrixCollapser calls here...
		//String val = myService.getValueTest(id).get();
		//CustomDTO val = myService.getValueBySearchParam(searchModel).get();
		
		List<SearchMessage> s = new ArrayList<SearchMessage>(); 
		s.add(searchModel);
		CustomDTO val = myService.getValue(s).get(0);
		
		// Shutdown Hystrix context
		//context.shutdown();
				
		return new ResponseEntity<CustomDTO>(val, HttpStatus.OK);
	}
}
