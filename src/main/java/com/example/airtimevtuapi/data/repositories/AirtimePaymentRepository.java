package com.example.airtimevtuapi.data.repositories;

import com.example.airtimevtuapi.data.models.AirtimePayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirtimePaymentRepository extends JpaRepository<AirtimePayment,Long> {
}
