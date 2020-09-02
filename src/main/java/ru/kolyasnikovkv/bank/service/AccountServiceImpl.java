package ru.kolyasnikovkv.bank.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.kolyasnikovkv.bank.critery.AccountCritery;
import ru.kolyasnikovkv.bank.entity.Account;
import ru.kolyasnikovkv.bank.entity.QAccount;
import ru.kolyasnikovkv.bank.repository.AccountRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("accountservice")
@Transactional
public class AccountServiceImpl extends ACrudService<Account, Long> implements AccountService{
    @Autowired
    AccountRepository accountRepository;

    @Override
    public CrudRepository<Account, Long> getRepository() {
        return accountRepository;
    }

    @Override
    public Account findByName(String name){
        AccountCritery critery = new AccountCritery();
        critery.name =  name;
        List<Account> accounts = findByCritery(critery);
        return accounts.size() > 0 ? accounts.get(0) : null;

    }

    @Override
    public List<Account> findByIdIn(Long[] ids){
        AccountCritery critery = new AccountCritery();
        critery.ids = Arrays.asList(ids);
        return findByCritery(critery);
    }

    @Override
    public List<Account> findByCritery(AccountCritery critery){
        //??? qAccount
        QAccount qAccount = QAccount.account;
        List<BooleanExpression> predicates = new ArrayList<>();
        if(critery.name != null && !critery.name.isEmpty()){
            predicates.add(qAccount.name.containsIgnoreCase(critery.name));
        }
        if (critery.ids.size() > 0) {
            predicates.add(qAccount.id.in(critery.ids));
        }
        if (critery.clientId != null) {
            predicates.add(qAccount.client.id.eq(critery.clientId));
        }
        if (critery.clientName != null) {
            predicates.add(qAccount.client.name.eq(critery.clientName));
        }
        BooleanExpression expression = predicates.stream()
                                        //??? optional
                .reduce((predicate, accum) -> accum.and(predicate)).orElse(null);
        Iterable<Account> iterAccounts = accountRepository.findAll(expression, new Sort(Sort.Direction.ASC, "name"));
        return iterableToList(iterAccounts);
    }
}
