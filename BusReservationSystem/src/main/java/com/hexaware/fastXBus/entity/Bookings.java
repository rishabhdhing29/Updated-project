package com.hexaware.fastXBus.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

/*
 * Author:Vishal Anand
 * Date: 20-11-23
 */


@Entity
public class Bookings implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;
    private LocalDate bookingDate;
	private String email;
	private int totalcustomer;
	private double amount;
	private String seatNo;
	
	
	
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private UserCustomers userCustomers;
	@ManyToOne
	@JoinColumn(name="busId")
	private Buses buses;
	
	
	
	@OneToOne(mappedBy="booking")
	private PaymentHistory paymenthistory;
	
	
	public Bookings() {
		super();
	}


	public Bookings(Long bookingId, LocalDate bookingDate, String email, int totalcustomer, double amount, String seatNo,
			UserCustomers userCustomers, Buses buses, List<Customer> customer, PaymentHistory paymenthistory) {
		super();
		this.bookingId = bookingId;
		this.bookingDate = bookingDate;
		this.email = email;
		this.totalcustomer = totalcustomer;
		this.amount = amount;
		this.seatNo = seatNo;
		this.userCustomers = userCustomers;
		this.buses = buses;
		
		this.paymenthistory = paymenthistory;
	}


	public Long getBookingId() {
		return bookingId;
	}


	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}


	public LocalDate getBookingDate() {
		return bookingDate;
	}


	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getTotalcustomer() {
		return totalcustomer;
	}


	public void setTotalcustomer(int totalcustomer) {
		this.totalcustomer = totalcustomer;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public String getSeatNo() {
		return seatNo;
	}


	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}


	public UserCustomers getUserCustomers() {
		return userCustomers;
	}


	public void setUserCustomers(UserCustomers userCustomers) {
		this.userCustomers = userCustomers;
	}


	public Buses getBuses() {
		return buses;
	}


	public void setBuses(Buses buses) {
		this.buses = buses;
	}



	public PaymentHistory getPaymenthistory() {
		return paymenthistory;
	}


	public void setPaymenthistory(PaymentHistory paymenthistory) {
		this.paymenthistory = paymenthistory;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}