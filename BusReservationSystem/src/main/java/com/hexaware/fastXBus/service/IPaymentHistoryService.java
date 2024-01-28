package com.hexaware.fastXBus.service;

import java.util.List;

import com.hexaware.fastXBus.dto.PaymentHistoryDTO;
import com.hexaware.fastXBus.entity.PaymentHistory;



public interface IPaymentHistoryService {
	public PaymentHistory createPaymentHistory(PaymentHistoryDTO paymenthistorydto);
	public  PaymentHistory  updatePaymentHistory(PaymentHistoryDTO  paymenthistorydto,Long paymentHistoryId);
	public void  deletePaymentHistory(Long paymentId);
	public PaymentHistoryDTO getPaymentHistoryById(Long paymentId);
	public List<PaymentHistory>getAllPaymentHistory();

}
	