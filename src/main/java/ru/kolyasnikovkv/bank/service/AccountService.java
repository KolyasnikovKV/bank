package ru.kolyasnikovkv.bank.service;

import ru.kolyasnikovkv.bank.critery.AccountCritery;
import ru.kolyasnikovkv.bank.critery.ClientCritery;
import ru.kolyasnikovkv.bank.entity.Account;
import ru.kolyasnikovkv.bank.entity.Client;

import java.util.List;

public interface AccountService extends ICrudService<Account, Long>{
    List<Account> findAll();
    Account findByName(String Name);
    List<Account> findByIdIn(Long[] ids);
    List<Account> findByCritery(AccountCritery critery);
}
