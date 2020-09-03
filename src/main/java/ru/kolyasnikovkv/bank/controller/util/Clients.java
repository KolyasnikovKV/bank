package ru.kolyasnikovkv.bank.controller.util;

import ru.kolyasnikovkv.bank.entity.Client;

import java.io.Serializable;
import java.util.List;

public class Clients implements Serializable {
    private List<Client> clients;

    public List<Client> getClients(){
        return clients;
    }

    public void setClients(List<Client> clients){
        this.clients = clients;
    }

    public Clients(List<Client> clients){
        this.clients = clients;
    }
}
