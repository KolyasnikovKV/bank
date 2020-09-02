package ru.kolyasnikovkv.bank.service;

import ru.kolyasnikovkv.bank.critery.ClientCritery;
import ru.kolyasnikovkv.bank.entity.Client;

import java.util.List;

public interface ClientService extends ICrudService<Client, Long>{
    List<Client> findAll();
    Client findByName(String Name);
    List<Client> findByIdIn(Long[] ids);
    List<Client> findByCritery(ClientCritery critery);
}
