package com.poc.sample.dtos;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CustomDTOs {

	@JsonProperty("dtos")
	private List<CustomDTO> dtos;

	public List<CustomDTO> getDtos() {
		return dtos;
	}

	public void setDtos(List<CustomDTO> dtos) {
		this.dtos = dtos;
	}
	
	/*@Override
	public String toString() {
		return "ProductEnrichmentMessage [products=" + products + ", total=" + total + ", missingSet="
				+ Arrays.toString(missingSet) + "]";
	}*/
	
}
