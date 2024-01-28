package com.hexaware.fastXBus.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Customer {
	@Id
	private long customerId;
	private String CustomerName;
	private String seatNo;
	@ManyToOne
	@JoinColumn(name="bookingId")
	private Bookings booking;
	public Customer() {
		super();
	}
	public Customer(long customerId, String customerName, String seatNo, Bookings booking) {
		super();
		this.customerId = customerId;
		CustomerName = customerName;
		this.seatNo = seatNo;
		this.booking = booking;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	public Bookings getBooking() {
		return booking;
	}
	public void setBooking(Bookings booking) {
		this.booking = booking;
	}
	
	

}
