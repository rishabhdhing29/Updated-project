package com.hexaware.fastXBus.service;

import java.util.List;
import java.util.Optional;

import com.hexaware.fastXBus.dto.UserCustomersDTO;
import com.hexaware.fastXBus.entity.Bookings;
import com.hexaware.fastXBus.entity.UserCustomers;



public interface IUserCustomersService {

	public UserCustomers createUser(UserCustomersDTO usercustomerdto);
	public UserCustomers updateUser(UserCustomersDTO usercustomerdto,Long userId);
	public void  deleteUser(Long userId);
	public UserCustomersDTO getUserById(Long userId);
	public List<UserCustomers>getAllUserCustomers();
	UserCustomers getUserCustomersByBookingId(Long bookingId);
	UserCustomers getUserIdByFirstName(String firstName);

}
