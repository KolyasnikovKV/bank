package ru.kolyasnikovkv.bank;

public class CustomErrorType {
    private String errorMessage;

    public String getErrorMessage(){
        return errorMessage;
    }

    public CustomErrorType(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
