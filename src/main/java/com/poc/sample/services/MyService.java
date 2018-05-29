package com.poc.sample.services;

import java.util.List;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.poc.sample.dtos.CustomDTO;
import com.poc.sample.messages.SearchMessage;
import com.poc.sample.repos.MyCaptainCreditRepo;

@Component
public class MyService {

	@Autowired
	private MyCaptainCreditRepo myCaptainCreditRepo;
	
	
	@HystrixCollapser(scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL, batchMethod = "getValue",
			collapserProperties = {
					@com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty(
							name = "maxRequestsInBatch", value = "2"),
					@com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty(
							name = "timerDelayInMilliseconds", value = "500")})
	public Future<CustomDTO> getValueBySearchParam(SearchMessage model) {
		return null;	
	}
	
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

}
