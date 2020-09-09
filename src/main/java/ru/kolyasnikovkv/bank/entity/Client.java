package ru.kolyasnikovkv.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "client")
public class Client extends AbstractEntity {
    private String name ="";
    private String phone ="";
    private String address ="";

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public Client(Long id, String name, String phone){
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
    public Client() {

    }

    @Column
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }
}
