package com.poc.sample.services;

import java.sql.SQLNonTransientException;
import java.sql.SQLTransientException;
import java.util.List;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.poc.sample.dtos.CustomDTO;
import com.poc.sample.messages.SearchMessage;
import com.poc.sample.repos.MyCaptainCreditRepo;

@Component
public class MyService {

	@Autowired
	private MyCaptainCreditRepo myCaptainCreditRepo;
	
	
	
	/*
	 * For DB circuit breaker operation - start
	 * */
	@HystrixCommand(commandKey="getValue", fallbackMethod="fallbackMethod", threadPoolKey="myThreadPool",
			threadPoolProperties = {
					@com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty(name = "coreSize", value = "20"),
					@com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty(name = "maxQueueSize", value = "5")
					})
	public List<CustomDTO> getValue(List<SearchMessage> models)
	{
		return myCaptainCreditRepo.getValuesBySearchParam(models);
	}
	
	public List<CustomDTO> fallbackMethod(List<SearchMessage> models) throws Exception
	{
		System.out.println("from fallback");
		return null;
	}
	/*
	 * For DB circuit breaker operation - end
	 * */
	
	
	
	
	
	
	
	
	/*
	 * For Hytrix with spring transaction - start
	 * */
	@HystrixCommand(commandKey="updateCreditInfo", fallbackMethod="fallbackMethodCreditInfo",		threadPoolProperties = {
			@com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty(name = "coreSize", value = "1"),
			@com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty(name = "maxQueueSize", value = "1")
			})
	public void updateCreditInfo(SearchMessage model) throws Exception
	{
		myCaptainCreditRepo.updateValue(model);
		//throw new RuntimeException("d");
	}
	
	@HystrixCommand(commandKey="updateCreditInfoAlt", threadPoolProperties = {
			@com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty(name = "coreSize", value = "1"),
			@com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty(name = "maxQueueSize", value = "1")
			})
	public void updateCreditInfoAlt(SearchMessage model) throws Exception
	{
		myCaptainCreditRepo.updateValue(model);
		//throw new RuntimeException("d");
	}
	
	public void fallbackMethodCreditInfo(SearchMessage model)
	{
		System.out.println("a");
		throw new RuntimeException("a");
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateValues(SearchMessage model) throws Exception
	{
		SearchMessage model1 = new SearchMessage();
		model1.setSearchId(2118);
		updateCreditInfo(model1);
		
		updateCreditInfoAlt(model);
		
		//throw new RuntimeException("d");
	}
	/*
	 * For Hytrix with spring transaction - end 
	 * */
}
