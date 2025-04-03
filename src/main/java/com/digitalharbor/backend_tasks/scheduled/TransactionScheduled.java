package com.digitalharbor.backend_tasks.scheduled;

import com.digitalharbor.backend_tasks.jpa.entity.TransactionDay;
import com.digitalharbor.backend_tasks.jpa.entity.TransactionHistory;
import com.digitalharbor.backend_tasks.jpa.repository.TransactionDayRepository;
import com.digitalharbor.backend_tasks.jpa.repository.TransactionHistoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Carmen Laura
 * @date 02/04/2025
 */
@Service
public class TransactionScheduled {

    private final TransactionDayRepository transactionDayRepository;

    private final TransactionHistoryRepository transactionHistoryRepository;

    public TransactionScheduled(TransactionDayRepository transactionDayRepository,
                                TransactionHistoryRepository transactionHistoryRepository) {
        this.transactionDayRepository = transactionDayRepository;
        this.transactionHistoryRepository = transactionHistoryRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?") // Se ejecuta a las 00:00:00 todos los días
    @Transactional
    public void moveTransactionsToHistory() {
        List<TransactionDay> transactions = transactionDayRepository.findAll();

        if (!transactions.isEmpty()) {
            List<TransactionHistory> historyList = transactions.stream()
                    .map(transaction -> {
                        TransactionHistory history = new TransactionHistory();
                        history.setName(transaction.getName());
                        history.setTransactionNumber(transaction.getTransactionNumber());
                        history.setDate(transaction.getDate());
                        history.setAmount(transaction.getAmount());
                        history.setType(transaction.getType());
                        return history;
                    })
                    .toList();

            transactionHistoryRepository.saveAll(historyList);

            transactionDayRepository.deleteAll();

            System.out.println("✅ Transactions moved to history successfully.");
        } else {
            System.out.println("⚠️ No transactions to move.");
        }
    }

}
