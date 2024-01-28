package com.hexaware.fastXBus.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.fastXBus.entity.UserCustomers;
@Repository
public interface IUserCustomersRepository extends JpaRepository<UserCustomers,Long >{

	Optional<UserCustomers> findByFirstName(String firstName);
	UserCustomers findByBookingsBookingId(Long bookingId);
	UserCustomers getUserIdByFirstName(String firstName);
}
