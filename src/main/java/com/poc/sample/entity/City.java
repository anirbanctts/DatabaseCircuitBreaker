package com.poc.sample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CITY")
public class City {

	private static final long serialVersionUID = 3886003470849765887L;
	
	@Id
	@Column(name="ID")
	private Integer id;
	
	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="NAME")
	private String countryCode;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}
