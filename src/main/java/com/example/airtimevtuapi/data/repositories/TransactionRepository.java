package com.example.airtimevtuapi.data.repositories;

import com.example.airtimevtuapi.data.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
