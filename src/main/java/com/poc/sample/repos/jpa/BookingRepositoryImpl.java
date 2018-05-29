package com.poc.sample.repos.jpa;

import java.sql.SQLNonTransientException;
import java.sql.SQLTransientException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.poc.sample.entity.CaptainCreditLimit;
import org.springframework.data.repository.query.Param;

@Repository
public interface BookingRepositoryImpl extends JpaRepository<CaptainCreditLimit, Integer> {

	@Query(value = "SELECT captain_id, credit_limit FROM driver_detail WHERE captain_id in (:driverIds)", nativeQuery = true)
	List<CaptainCreditLimit> getCaptainCreditLimits(@Param("driverIds") List<Integer> driverIds) throws SQLNonTransientException, SQLTransientException;
}
