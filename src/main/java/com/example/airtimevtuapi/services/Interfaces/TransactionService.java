package com.example.airtimevtuapi.services.Interfaces;

import com.example.airtimevtuapi.data.models.AppUser;
import com.example.airtimevtuapi.data.models.Transaction;

import java.math.BigDecimal;

public interface TransactionService {

    void Save(Transaction transaction);
}
