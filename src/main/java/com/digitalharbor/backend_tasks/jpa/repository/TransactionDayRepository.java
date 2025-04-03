package com.digitalharbor.backend_tasks.jpa.repository;

import com.digitalharbor.backend_tasks.jpa.entity.TransactionDay;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Carmen Laura
 * @date 02/04/2025
 */
public interface TransactionDayRepository extends JpaRepository<TransactionDay, Long> {
}
