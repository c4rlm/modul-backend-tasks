package com.digitalharbor.backend_tasks.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Carmen Laura
 * @date 02/04/2025
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transaction_history")
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @Column(name = "transaction_number")
    private Long transactionNumber;

    @Column(name = "date", columnDefinition = "timestamp default now()")
    private LocalDateTime date = LocalDateTime.now();

    private BigDecimal amount;
    private String type;

}
