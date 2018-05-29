package com.poc.sample.repos;

import java.sql.SQLNonTransientException;
import java.sql.SQLTransientException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException;
import com.poc.sample.dtos.CustomDTO;
import com.poc.sample.entity.CaptainCreditLimit;
import com.poc.sample.entity.City;
import com.poc.sample.messages.SearchMessage;
import com.poc.sample.repos.jpa.BookingRepositoryImpl;
import com.poc.sample.repos.jpa.MySampleJPARepo;

@Component
public class MyCaptainCreditRepo {

	@Autowired
	private BookingRepositoryImpl bookingRepositoryImpl;
	
	public List<CustomDTO> getValuesBySearchParam(List<SearchMessage> searchModels)  {
		
		List<Integer> captainIds = searchModels
	            .stream()
	            .map(SearchMessage::getSearchId)
	            .collect(Collectors.toList());
		
		
		System.out.println("Hitting main method for captainIds: " + captainIds);
		try
		{
			List<CaptainCreditLimit> creditLimits =  bookingRepositoryImpl.getCaptainCreditLimits(captainIds);
			List<CustomDTO> dtos = mapCaptainCreditLimitsToDtos(creditLimits);
			dtos = maintainResponseOrder(dtos, searchModels);
			//Thread.sleep(100);
			return dtos;
		} catch(SQLTransientException tex) {
			System.out.println("Transient exception - " + tex.getMessage());
		} catch(SQLNonTransientException comex) {
			System.out.println("DB exception - " + comex.getMessage());
		} catch(Exception ex) {
			System.out.println("DB exception - " + ex.getMessage());
		}
		return null;
	}

	private List<CustomDTO> maintainResponseOrder(List<CustomDTO> dtos, List<SearchMessage> searchModels) {
		
		List<CustomDTO> listCorrectOrder = new ArrayList<>();
		
		searchModels.stream().forEach(searchModel -> {
			
			listCorrectOrder.add(dtos.stream().filter(item -> item.getId()==searchModel.getSearchId()).findFirst().get());
		});
		return listCorrectOrder;
	}

	private List<CustomDTO> mapCaptainCreditLimitsToDtos(List<CaptainCreditLimit> captainCreditLimits) {
		List<CustomDTO> dtos = new ArrayList<>();
		captainCreditLimits.stream().forEach(captainCreditLimit-> {
			dtos.add(new CustomDTO() {{ setId(captainCreditLimit.getCaptainId()); setName(captainCreditLimit.getCreditLimit().toString()); }});
		});
		
		return dtos;
	}
}
