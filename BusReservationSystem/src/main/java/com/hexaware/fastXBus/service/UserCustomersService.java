package com.hexaware.fastXBus.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hexaware.fastXBus.dto.UserCustomersDTO;
import com.hexaware.fastXBus.entity.Bookings;
import com.hexaware.fastXBus.entity.UserCustomers;
import com.hexaware.fastXBus.repository.IUserCustomersRepository;


@Service
public class UserCustomersService implements IUserCustomersService {

	
	@Autowired
	IUserCustomersRepository repository;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	private PasswordEncoder passwordEncoder;
	private static final Logger logger = LoggerFactory.getLogger(UserCustomersService.class);
	@Override
	public UserCustomers createUser(UserCustomersDTO usercustomerdto) {
		UserCustomers usercustomer=new UserCustomers();
		usercustomer.setFirstName(usercustomerdto.getFirstName());
		usercustomer.setLastName(usercustomerdto.getLastName());
		usercustomer.setEmail(usercustomerdto.getEmail());
		usercustomer.setPassword(passwordEncoder.encode(usercustomerdto.getPassword()));
		usercustomer.setPhoneNumber(usercustomerdto.getPhoneNumber());
		usercustomer.setAddress(usercustomerdto.getAddress());
		usercustomer.setState(usercustomerdto.getState());
		usercustomer.setCity(usercustomerdto.getCity());
		usercustomer.setZipCode(usercustomerdto.getZipCode());
		return repository.save(usercustomer);
		
	}

	@Override
	public UserCustomers updateUser(UserCustomersDTO usercustomerdto, Long userId) {
	  
	    Optional<UserCustomers> userOptional = repository.findById(userId);
	    
	    if (userOptional.isPresent()) {
	     
	        UserCustomers existingUser = userOptional.get();
	        
	        
	        existingUser.setFirstName(usercustomerdto.getFirstName());
	        existingUser.setLastName(usercustomerdto.getLastName());
	        existingUser.setEmail(usercustomerdto.getEmail());
	        existingUser.setPhoneNumber(usercustomerdto.getPhoneNumber());
	        existingUser.setAddress(usercustomerdto.getAddress());
	        existingUser.setState(usercustomerdto.getState());
	        existingUser.setCity(usercustomerdto.getCity());
	        existingUser.setZipCode(usercustomerdto.getZipCode());
	        
	        
	        return repository.save(existingUser);
	    } else {
	    	logger.error("user not found");
	        return null;
	    }
	}

	@Override
	public void deleteUser(Long userId) {
		repository.deleteById(userId);
		
	}

	@Override
	public UserCustomersDTO getUserById(Long userId) {
		UserCustomers usercustomers=repository.findById(userId).orElse(new UserCustomers());
		return new UserCustomersDTO(usercustomers.getUserId(),usercustomers.getFirstName(),usercustomers.getLastName(),usercustomers.getEmail(),usercustomers.getPassword(),usercustomers.getPhoneNumber(),usercustomers.getAddress(),usercustomers.getCity(),usercustomers.getState(),usercustomers.getZipCode());
	}

	@Override
	public List<UserCustomers> getAllUserCustomers() {
		
		return repository.findAll(Sort.by("firstName"));
	}

	@Override
	public UserCustomers getUserCustomersByBookingId(Long bookingId) {
		// TODO Auto-generated method stub
		return repository.findByBookingsBookingId(bookingId);
	}

	@Override
	public UserCustomers getUserIdByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return repository.getUserIdByFirstName(firstName);
	}



	

}
