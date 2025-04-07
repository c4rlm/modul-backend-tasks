package com.digitalharbor.backend_tasks.jpa.service;

import com.digitalharbor.backend_tasks.jpa.entity.TransactionHistory;
import com.digitalharbor.backend_tasks.jpa.repository.TransactionHistoryRepository;
import com.digitalharbor.backend_tasks.jpa.specification.TransactionHistorySpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionHistoryService {

    private final TransactionHistoryRepository repository;

    private final TransactionHistoryNestedService transactionHistoryNestedService;

    public TransactionHistoryService(
            TransactionHistoryRepository repository,
            TransactionHistoryNestedService transactionHistoryNestedService
    ) {
        this.repository = repository;
        this.transactionHistoryNestedService = transactionHistoryNestedService;
    }

    public Page<TransactionHistory> getFilteredTransactions(String keyword, Pageable pageable) {
        return repository.findAll(TransactionHistorySpecification.filterByKeyword(keyword), pageable);
    }

    public Pageable buildPageable(Integer page, Integer size, String sortField, String sortDirection) {

        int defaultPage = (page != null && page >= 0) ? page : 0;
        int defaultSize = (size != null && size > 0) ? size : 10;

        Sort sort = Sort.unsorted();
        if (sortField != null && !sortField.isEmpty()) {
            sort = sortDirection != null && sortDirection.equalsIgnoreCase("desc")
                    ? Sort.by(sortField).descending()
                    : Sort.by(sortField).ascending();
        }

        return PageRequest.of(defaultPage, defaultSize, sort);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void createWithoutTransaction(String name) {
        saveTransactionHistory(name);
        throw new RuntimeException("Error after save with NOT_SUPPORTED"); // No rollback
    }

    @Transactional(propagation = Propagation.NEVER)
    public void createNeverWithinTransaction(String name) {
        saveTransactionHistory(name);
    }

    @Transactional()
    public void createWithTransactionAndNested(String primaryName, String nestedName) {
        saveTransactionHistory(primaryName);
        try {
            transactionHistoryNestedService.createNested(nestedName);
        } catch (Exception e) {
            log.warn("Nested transaction error caught: {}", e.getMessage());
        }
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
