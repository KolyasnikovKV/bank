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
    private int age;

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public Client(Long id, String name, int age, String phone, String address){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.age = age;
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
