package ru.kolyasnikovkv.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class SpringBootApplicationBank {
    private static Logger logger = Logger.getLogger(SpringBootApplicationBank.class.getName());
    public static void main(String[] args){
        SpringApplication.run(SpringBootApplicationBank.class, args);
    }
}
