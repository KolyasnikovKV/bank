package ru.kolyasnikovkv.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.kolyasnikovkv.bank.entity.Client;
import ru.kolyasnikovkv.bank.entity.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long>, QuerydslPredicateExecutor<Operation> {
}
