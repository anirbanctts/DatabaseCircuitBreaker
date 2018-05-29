package com.poc.sample.repos.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poc.sample.entity.City;

public interface MySampleJPARepo extends JpaRepository<City, Integer> {
	City findById(int id);
	List<City> findByIdIn(List<Integer> ids);
}
