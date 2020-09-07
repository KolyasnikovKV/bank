package ru.kolyasnikovkv.bank.util;

import ru.kolyasnikovkv.bank.entity.Account;

import java.io.Serializable;
import java.util.List;

public class Accounts implements Serializable {
    public List<Account> getAccounts(){
        return accounts;
    }

    public void setAccounts(List<Account> accounts){
        this.accounts = accounts;
    }

    public Accounts(List<Account> accounts){
        this.accounts = accounts;
    }

    private List<Account> accounts;
}
