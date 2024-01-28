package com.hexaware.fastXBus.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hexaware.fastXBus.dto.PaymentHistoryDTO;
import com.hexaware.fastXBus.entity.PaymentHistory;
import com.hexaware.fastXBus.repository.IPaymentHistoryRepository;

@Service
public class PaymentHistoryService implements IPaymentHistoryService {

	@Autowired
	IPaymentHistoryRepository repository;
	@Autowired
	RestTemplate restTemplate;
	private static final Logger logger = LoggerFactory.getLogger(PaymentHistoryService.class);
	@Override
	public PaymentHistory createPaymentHistory(PaymentHistoryDTO paymenthistorydto) {
		PaymentHistory paymenthistory =new PaymentHistory();
		paymenthistory.setPaymentId(paymenthistorydto.getPaymentId());
		
		paymenthistory.setAmountPaid(paymenthistorydto.getAmountPaid());
		paymenthistory.setPaymentDate(paymenthistorydto.getPaymentDate());
		
		return repository.save(paymenthistory);
	}

	@Override
	public PaymentHistory updatePaymentHistory(PaymentHistoryDTO paymenthistorydto, Long paymentId) {
	    // Find the existing payment history by ID
	    Optional<PaymentHistory> paymentOptional = repository.findById(paymentId);
	    
	    if (paymentOptional.isPresent()) {
	        // Get the existing payment history
	        PaymentHistory existingPaymentHistory = paymentOptional.get();
	        
	        // Update the fields with new values from DTO
	        
	        existingPaymentHistory.setAmountPaid(paymenthistorydto.getAmountPaid());
	        existingPaymentHistory.setPaymentDate(paymenthistorydto.getPaymentDate());
	      
	        // Save the updated payment history
	        return repository.save(existingPaymentHistory);
	    } else {
	    	logger.error("Payment not found");
	        return null;
	    }
	}

	@Override
	public void deletePaymentHistory(Long paymentId) {
		repository.deleteById(paymentId);
		
	}

	@Override
	public PaymentHistoryDTO getPaymentHistoryById(Long paymentId) {
		PaymentHistory paymenthistory=repository.findById(paymentId).orElse(new PaymentHistory());
		return new PaymentHistoryDTO(paymenthistory.getPaymentId(),paymenthistory.getAmountPaid(),paymenthistory.getPaymentDate());
	}

	@Override
	public List<PaymentHistory> getAllPaymentHistory() {
		return repository.findAll(Sort.by("bookingId"));
	}
	
}
