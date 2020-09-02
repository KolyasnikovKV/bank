package ru.kolyasnikovkv.bank.service;

import java.io.Serializable;

public interface ICrudService<T, PK extends Serializable> {
    T findById(PK Id);
    T save(T o);
    void delete(T o);
}
