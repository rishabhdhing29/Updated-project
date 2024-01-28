package com.hexaware.fastXBus.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import com.hexaware.fastXBus.dto.BookingsDTO;
import com.hexaware.fastXBus.entity.Bookings;
import com.hexaware.fastXBus.entity.Buses;
import com.hexaware.fastXBus.entity.UserCustomers;
import com.hexaware.fastXBus.repository.IBookingsRepository;
import com.hexaware.fastXBus.repository.IBusesRepository;
import com.hexaware.fastXBus.repository.IUserCustomersRepository;

@Service
public class BookingsService implements IBookingsService {
	@Autowired
	private IBookingsRepository repository;
	@Autowired
	IBusesRepository busRepository;
	@Autowired
	IUserCustomersRepository ucrepository;
	@Autowired
	RestTemplate restTemplate;
	 @Autowired
	 JavaMailSender mail;
	private static final Logger logger = LoggerFactory.getLogger(BookingsService.class);
	@Override
	public BookingsDTO createBookings(BookingsDTO bookingdto, Long userId, Long busId) {
	    // Fetch the bus
	    Buses bus = busRepository.findById(busId).orElse(null);
	    if (bus == null) {
	        // Handle case where bus is not found
	        return null;
	    }

	    // Check if the bus has available seats
	    if (bus.getCapacity() <= 0) {
	        // Handle case where bus capacity is zero
	        return null;
	    }

	    // Fetch user details
	    UserCustomers user = ucrepository.findById(userId).orElse(null);
	    if (user == null) {
	        // Handle case where user is not found
	        return null;
	    }

	    // Create the booking only if there are available seats
	    if (bus.getCapacity() > 0) {
	        Bookings booking = new Bookings();
	        booking.setBookingDate(bookingdto.getBookingDate());
	        booking.setEmail(bookingdto.getEmail());
	        booking.setTotalcustomer(bookingdto.getTotalcustomer());
	        booking.setAmount(bookingdto.getAmount());
	        booking.setSeatNo(bookingdto.getSeatNo());

	        // Decrease the bus capacity after booking
	        int remainingCapacity = bus.getCapacity() - booking.getTotalcustomer();
	        bus.setCapacity(remainingCapacity);

	        // Save the updated bus capacity
	        busRepository.save(bus);

	        // Save the booking
	        booking = repository.save(booking);

	        return new BookingsDTO(booking.getBookingId(), booking.getBookingDate(), booking.getEmail(), booking.getTotalcustomer(), booking.getAmount(), booking.getSeatNo());
	    } else {
	        // Handle case where no seats are available
	        return null;
	    }
	}


	@Override
	public Bookings updateBookings(BookingsDTO bookingsdto, Long bookingId) {
	    // Find the existing booking by ID
	    Optional<Bookings> bookingOptional = repository.findById(bookingId);
	    
	    if (bookingOptional.isPresent()) {
	        // Get the existing booking
	        Bookings existingBooking = bookingOptional.get();
	        
	        // Update the fields with new values from DTO
	       
	        existingBooking.setTotalcustomer(bookingsdto.getTotalcustomer());
	        existingBooking.setBookingDate(bookingsdto.getBookingDate());
	        existingBooking.setAmount(bookingsdto.getAmount());
	        existingBooking.setEmail(bookingsdto.getEmail());
		
	        
	        // Save the updated booking
	        return repository.save(existingBooking);
	    } else {
	    	logger.error("Booking not found");
	        return null;
	    }
	}

	@Override
	public void deleteBookings(Long bookingId) {
		
		repository.deleteById(bookingId);
		
	}

	@Override
	public BookingsDTO getBookingsById(Long bookingId) {
		
		Bookings booking=repository.findById(bookingId).orElse(new Bookings());
		return new BookingsDTO(booking.getBookingId(),booking.getBookingDate(),booking.getEmail(),booking.getTotalcustomer(),booking.getAmount(),booking.getSeatNo());
	}

	@Override
	public List<Bookings> getAllBookings() {
	
		return repository.findAll(Sort.by("bookingDate"));
	}

	@Override
	public List<String> fetchBookedSeats(LocalDate date, Long busId) {
		// TODO Auto-generated method stub
		return repository.fetchBookedSeats(date, busId);
	}


	@Override
	public void sendBookingConfirmationEmail(Long bookingId) {
	    // Fetch the booking details by ID from the database with associated Buses
	    Bookings booking = repository.findBookingWithBusesById(bookingId);
	    

	    if (booking != null) {
	        String subject = "Booking Confirmation";
	        String text = "Hi " + booking.getEmail() + ",\n"
	                + "Your booking details:\n\n"
	                + "Booking ID: " + booking.getBookingId() + "\n"
	                + "Booking Date: " + booking.getBookingDate() + "\n"

	                + "Total Customers: " + booking.getTotalcustomer() + "\n"
	                + "Seat Number: " + booking.getSeatNo() + "\n"
	                + "Amount: " + booking.getAmount() + "\n";

	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(booking.getEmail());
	        message.setSubject(subject);
	        message.setText(text);

	        mail.send(message);
	    } else {
	        // Handle case where booking is not found
	        logger.error("Booking not found");
	    }
	}



	@Override
	public Bookings findBookingWithBusesById(Long bookingId) {
		// TODO Auto-generated method stub
		return repository.findBookingWithBusesById(bookingId);
	}

	
	
  

}
