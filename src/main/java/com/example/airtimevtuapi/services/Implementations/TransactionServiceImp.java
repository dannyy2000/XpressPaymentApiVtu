package com.example.airtimevtuapi.services.Implementations;

import com.example.airtimevtuapi.data.models.AppUser;
import com.example.airtimevtuapi.data.models.Transaction;
import com.example.airtimevtuapi.data.repositories.TransactionRepository;
import com.example.airtimevtuapi.services.Interfaces.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class TransactionServiceImp implements TransactionService {

    private final TransactionRepository transactionRepository;


    @Override
    public void Save(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
