package ru.kolyasnikovkv.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.kolyasnikovkv.bank.entity.Account;
import ru.kolyasnikovkv.bank.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long>, QuerydslPredicateExecutor<Client> {
}
