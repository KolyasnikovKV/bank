package ru.kolyasnikovkv.bank.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "account")
public class Account extends AbstractEntity{
    private Client client;
    private String name="";

    @JsonSerialize(using =MoneySerializer.class)
    private BigDecimal balance = new BigDecimal("0.00");

    @ManyToOne
    @JoinColumn(name = "client_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Client getClient(){
        return client;
    }

    public void setClient(Client client){
        this.client = client;
    }

    @Column
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Column
    public BigDecimal getBalance(){
        return balance;
    }

    public void setBalance(BigDecimal balance){
        this.balance = balance;
    }

    @Override
    public String toString(){
        return "Account{" +
                "id=" + id +
                ", name=" + name +
                ", client=" + client +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if(!(obj instanceof Account)) return false;
        Account account = (Account) obj;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, client, balance);
    }

}
