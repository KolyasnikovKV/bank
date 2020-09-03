package ru.kolyasnikovkv.bank.critery;

import java.util.ArrayList;
import java.util.List;

public class ClientCritery {
    public List<Long> ids = new ArrayList<>();
    public String name;

    public ClientCritery() {
    }

    public ClientCritery(List<Long> ids, String name){
      if (ids !=  null){
            this.ids = ids;
        }
        this.name = name;
    }

}
