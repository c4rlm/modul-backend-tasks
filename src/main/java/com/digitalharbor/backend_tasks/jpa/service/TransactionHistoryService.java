package com.digitalharbor.backend_tasks.jpa.service;

import com.digitalharbor.backend_tasks.jpa.entity.TransactionHistory;
import com.digitalharbor.backend_tasks.jpa.repository.TransactionHistoryRepository;
import com.digitalharbor.backend_tasks.jpa.specification.TransactionHistorySpecification;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class TransactionHistoryService {

    private final TransactionHistoryRepository transactionHistoryRepository;

    public TransactionHistoryService(TransactionHistoryRepository transactionHistoryRepository) {
        this.transactionHistoryRepository = transactionHistoryRepository;
    }

    public Page<TransactionHistory> getFilteredTransactions(String keyword, Pageable pageable) {
        return transactionHistoryRepository.findAll(TransactionHistorySpecification.filterByKeyword(keyword), pageable);
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
}
