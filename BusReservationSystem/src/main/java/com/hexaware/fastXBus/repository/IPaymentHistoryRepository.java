package com.hexaware.fastXBus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.fastXBus.entity.PaymentHistory;
@Repository
public interface IPaymentHistoryRepository extends JpaRepository<PaymentHistory,Long > {

}
