package com.poc.sample.repos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poc.sample.dtos.CustomDTO;
import com.poc.sample.entity.City;
import com.poc.sample.messages.SearchMessage;
import com.poc.sample.repos.jpa.MySampleJPARepo;

@Component
public class MyRepo {

	@Autowired
	private MySampleJPARepo mySampleJPARepo;
	
	public List<CustomDTO> getValuesBySearchParam(List<SearchMessage> searchModels)  {
		
		List<Integer> ids = searchModels
	            .stream()
	            .map(SearchMessage::getSearchId)
	            .collect(Collectors.toList());
		
		System.out.println("Hitting main method" + ids);
		List<City> cities =  mySampleJPARepo.findByIdIn(ids);
		List<CustomDTO> dtos = mapCitiesToDtos(cities);
		
		return dtos;
	}

	private List<CustomDTO> mapCitiesToDtos(List<City> cities) {
		
		List<CustomDTO> dtos = new ArrayList<>();
		cities.stream().forEach(city-> {
			dtos.add(new CustomDTO() {{ setId(city.getId()); setName(city.getCountryCode()); }});
		});
		
		return dtos;
	}
}
