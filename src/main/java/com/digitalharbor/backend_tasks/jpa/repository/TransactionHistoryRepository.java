package com.digitalharbor.backend_tasks.jpa.repository;

import com.digitalharbor.backend_tasks.jpa.entity.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Carmen Laura
 * @date 02/04/2025
 */
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long>, JpaSpecificationExecutor<TransactionHistory> {
}
