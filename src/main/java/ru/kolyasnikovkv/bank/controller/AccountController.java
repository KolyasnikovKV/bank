package ru.kolyasnikovkv.bank.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolyasnikovkv.bank.controller.util.Accounts;
import ru.kolyasnikovkv.bank.critery.AccountCritery;
import ru.kolyasnikovkv.bank.entity.Account;
import ru.kolyasnikovkv.bank.service.ACrudService;
import ru.kolyasnikovkv.bank.service.AccountService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/account")
public class AccountController extends AbstractSimpleRestController<Account, Long> {
    public static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Accounts> list(
        @RequestParam(value = "name_client", required = false) String name,
        @RequestParam(value = "ids", required = false) List<Long> ids){
        logger.info("Params name: " + name + ";");
        logger.info("Params ids: " + ids + ";");
        AccountCritery accountCritery = new AccountCritery();
        if(name != null && !name.isEmpty()){
            accountCritery.name = name;
        }
        if(ids != null && ids.size() > 0){
            accountCritery.ids = ids;
        }
        List<Account> accounts = accountService.findByCritery(accountCritery);
        if(accounts.isEmpty()){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity<Accounts>(new Accounts(accounts), HttpStatus.OK);

    }

    @Override
    public ACrudService<Account, Long> getService(){
        return (ACrudService<Account, Long>) accountService;
    }

    @Override
    public Logger getLogger(){
        return logger;
    }
}
