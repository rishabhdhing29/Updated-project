package com.hexaware.fastXBus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.fastXBus.entity.Admin;
import com.hexaware.fastXBus.entity.BusOperators;
@Repository
public interface IBusOperatorsRepository extends JpaRepository<BusOperators,Long >{
	BusOperators getOperatorIdByoperatorName(String operatorName);
	Optional<BusOperators> findByoperatorName(String operatorName);
}
