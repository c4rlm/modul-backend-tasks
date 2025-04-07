package com.digitalharbor.backend_tasks.jpa.service;

import com.digitalharbor.backend_tasks.jpa.entity.TransactionHistory;
import com.digitalharbor.backend_tasks.jpa.repository.TransactionHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Carmen Laura
 * @date 07/04/2025
 */
@Service
@Slf4j
public class TransactionHistoryNestedService {

    private final TransactionHistoryRepository repository;

    public TransactionHistoryNestedService(TransactionHistoryRepository repository) {
        this.repository = repository;
    }

    @Transactional(propagation = Propagation.NESTED)
    public void createNested(String name) {
        saveTransactionHistory(name);
        throw new RuntimeException("Failure in nested method"); // Only this part rolls back
    }

    private void saveTransactionHistory(String name) {
        TransactionHistory history = new TransactionHistory();
        history.setName(name);
        repository.save(history);
    }

}
