package ru.kolyasnikovkv.bank.util;

import ru.kolyasnikovkv.bank.entity.Operation;

import java.io.Serializable;
import java.util.List;

public class Operations implements Serializable {
    List<Operation> operations;

    public List<Operation> getOperations(){
        return operations;
    }

    public void setOperations(List<Operation> operations){
        this.operations = operations;
    }

    public Operations(List<Operation> operations){
        this.operations = operations;
    }
}
