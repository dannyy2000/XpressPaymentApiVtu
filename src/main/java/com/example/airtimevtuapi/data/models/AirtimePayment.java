package com.example.airtimevtuapi.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirtimePayment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
   private String id;
   @ManyToOne(cascade = CascadeType.ALL)
    private AppUser appUser;
    private BigDecimal amount;
    private String uniqueCode;
    private String phoneNumber;
    private LocalDateTime time;

}
