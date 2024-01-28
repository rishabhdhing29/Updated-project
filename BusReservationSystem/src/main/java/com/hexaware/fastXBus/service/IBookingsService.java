package com.hexaware.fastXBus.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.hexaware.fastXBus.dto.BookingsDTO;
import com.hexaware.fastXBus.entity.Bookings;
import com.hexaware.fastXBus.entity.Buses;
import com.hexaware.fastXBus.entity.PaymentHistory;



public interface IBookingsService {
	
	public BookingsDTO createBookings(BookingsDTO bookingsdto,Long userId,Long busId);
	public Bookings updateBookings(BookingsDTO bookingsdto,Long bookingId);
	public void  deleteBookings(Long bookingId);
	public BookingsDTO getBookingsById(Long bookingId);
	public List<Bookings>getAllBookings();
	public List<String> fetchBookedSeats(LocalDate date, Long busId);
	public void sendBookingConfirmationEmail(Long bookingId);
	 Bookings findBookingWithBusesById(Long bookingId);
	
}
