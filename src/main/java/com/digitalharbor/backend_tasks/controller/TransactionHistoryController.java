package com.digitalharbor.backend_tasks.controller;

import com.digitalharbor.backend_tasks.jpa.entity.TransactionHistory;
import com.digitalharbor.backend_tasks.jpa.service.TransactionHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
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

    @PostMapping("/not-supported")
    public String endpointNotSupported(@RequestParam String name) {
        try {
            transactionHistoryService.createWithoutTransaction(name);
        } catch (Exception e) {
            return "Caught error: " + e.getMessage();
        }
        return "Attempted save with NOT_SUPPORTED";
    }

    @PostMapping("/never")
    @Transactional  // This will cause an exception when calling a NEVER-propagated method.
    public String endpointNever(@RequestParam String name) {
        try {
            transactionHistoryService.createNeverWithinTransaction(name);
        } catch (Exception e) {
            return "Expected exception: " + e.getMessage();
        }
        return "This should not be reached";
    }

    @PostMapping("/nested")
    public String endpointNested(@RequestParam String name, @RequestParam String name2) {
        transactionHistoryService.createWithTransactionAndNested(name, name2);
        return "Attempted save with NESTED (partial rollback on nested failure)";
    }
}
