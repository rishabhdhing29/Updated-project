package com.hexaware.fastXBus.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.fastXBus.dto.PaymentHistoryDTO;
import com.hexaware.fastXBus.entity.PaymentHistory;
import com.hexaware.fastXBus.exceptions.PaymentHistoryNotFoundException;
import com.hexaware.fastXBus.service.IPaymentHistoryService;

@RestController
@RequestMapping("/api/v1/paymenthistory")
public class PaymentHistoryRestController {

	@Autowired
	private IPaymentHistoryService paymenthistory;

	private static final Logger logger = LoggerFactory.getLogger(PaymentHistoryRestController.class);
	@PostMapping("/create")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public PaymentHistory createPaymentHistory(@RequestBody PaymentHistoryDTO paymenthistorydto) {
		 logger.info("Payment created");
		return paymenthistory.createPaymentHistory(paymenthistorydto);
	}
	@PutMapping("/update/{paymentId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public PaymentHistory updatePaymentHistory(@RequestBody PaymentHistoryDTO paymenthistorydto,@PathVariable Long paymentId) {
		 logger.info("Payment updated");
		return paymenthistory.updatePaymentHistory(paymenthistorydto,paymentId);
	}
	@DeleteMapping("/delete/{paymentId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public void deletePaymentHistory(@PathVariable Long paymentId)
	{
		 logger.info("Payment deleted");
		paymenthistory.deletePaymentHistory(paymentId);
		
	}
	@GetMapping("/getById/{paymentId}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
	public PaymentHistoryDTO getPaymentHistoryById(@PathVariable Long paymentId) {
		
		if(paymentId==0) {
			throw new PaymentHistoryNotFoundException(HttpStatus.BAD_REQUEST,"payment not found"+paymentId);
		}
		return paymenthistory.getPaymentHistoryById(paymentId);	
		
	}
	@GetMapping("/getall")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public List<PaymentHistory> getAllPaymentHistory(){
		
		 logger.info("All Payment ");
		return paymenthistory.getAllPaymentHistory();
	
	}
	
}
