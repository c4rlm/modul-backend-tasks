package com.digitalharbor.backend_tasks.controller;

import com.digitalharbor.backend_tasks.jpa.entity.TransactionHistory;
import com.digitalharbor.backend_tasks.jpa.service.TransactionHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Carmen Laura
 * @date 02/04/2025
 */
@RestController
@RequestMapping("/api/transactions/history")
public class TransactionHistoryController {

    private final TransactionHistoryService transactionHistoryService;

    public TransactionHistoryController(TransactionHistoryService transactionHistoryService) {
        this.transactionHistoryService = transactionHistoryService;
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public Page<TransactionHistory> search(@RequestParam(defaultValue = "0") Integer page,
                                           @RequestParam(defaultValue = "20") Integer size,
                                           @RequestParam(defaultValue = "id") String sortBy,
                                           @RequestParam(defaultValue = "desc") String sortDirection,
                                           @RequestParam(defaultValue = "keyword") String keyword) {

        Pageable pageable = transactionHistoryService.buildPageable(page, size, sortBy, sortDirection);

        return transactionHistoryService.getFilteredTransactions(keyword,pageable);
    }
}
