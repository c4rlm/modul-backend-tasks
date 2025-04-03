package com.digitalharbor.backend_tasks.jpa.specification;

import com.digitalharbor.backend_tasks.jpa.entity.TransactionHistory;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Carmen Laura
 * @date 02/04/2025
 */
public class TransactionHistorySpecification {

    public static Specification<TransactionHistory> filterByKeyword(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if (keyword == null || keyword.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + keyword.toLowerCase() + "%"));

            try {
                Long transactionNumber = Long.valueOf(keyword);
                predicates.add(criteriaBuilder.equal(root.get("transactionNumber"), transactionNumber));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("type")), "%" + keyword.toLowerCase() + "%"));

            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
