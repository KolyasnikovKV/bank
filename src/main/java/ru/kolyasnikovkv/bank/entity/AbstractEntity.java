package ru.kolyasnikovkv.bank.entity;

import org.omg.CORBA.portable.IDLEntity;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class AbstractEntity implements Serializable {
    protected Long id;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    public Long getId(){return id;}

    public void SetId(Long id) {this.id = id;}
}
