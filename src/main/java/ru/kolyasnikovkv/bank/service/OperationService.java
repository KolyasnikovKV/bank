package ru.kolyasnikovkv.bank.service;

import ru.kolyasnikovkv.bank.critery.ClientCritery;
import ru.kolyasnikovkv.bank.critery.OperationCritery;
import ru.kolyasnikovkv.bank.entity.Account;
import ru.kolyasnikovkv.bank.entity.Operation;

import java.util.List;

public interface OperationService extends ICrudService<Operation, Long>{
    List<Operation> findAll();
    List<Operation> findByIdIn(Long[] ids);
    List<Operation> findByCritery(OperationCritery critery);
}
