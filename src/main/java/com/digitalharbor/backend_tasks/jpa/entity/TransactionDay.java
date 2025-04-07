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
@Table(name = "transaction_day")
public class TransactionDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @Column(name = "transaction_number", nullable = false)
    private Long transactionNumber;

    @Column(name = "date", nullable = false, columnDefinition = "timestamp default now()")
    private LocalDateTime date = LocalDateTime.now();

    private BigDecimal amount;
    private String type;

}
