package ru.kolyasnikovkv.bank.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.kolyasnikovkv.bank.critery.AccountCritery;
import ru.kolyasnikovkv.bank.critery.OperationCritery;
import ru.kolyasnikovkv.bank.entity.Account;
import ru.kolyasnikovkv.bank.entity.Operation;
import ru.kolyasnikovkv.bank.entity.QAccount;
import ru.kolyasnikovkv.bank.entity.QOperation;
import ru.kolyasnikovkv.bank.repository.AccountRepository;
import ru.kolyasnikovkv.bank.repository.OperationRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("operationservice")
@Transactional
public class OperationServiceImpl extends ACrudService<Operation, Long> implements OperationService{
    @Autowired
    OperationRepository operationRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public CrudRepository<Operation, Long> getRepository() {
        return operationRepository;
    }

        @Override
    public List<Operation> findByIdIn(Long[] ids){
        OperationCritery critery = new OperationCritery();
        critery.ids = Arrays.asList(ids);
        return findByCritery(critery);
    }

    @Override
    public List<Operation> findByCritery(OperationCritery critery){
        //??? qOperation
        QOperation qOperation = QOperation.operation;
        List<BooleanExpression> predicates = new ArrayList<>();
        if (critery.ids.size() > 0) {
            predicates.add(qOperation.id.in(critery.ids));
        }
        if (critery.clientId != null) {
            predicates.add(qOperation.ddate.goe(critery.fromDate));
        }
        if (critery.clientId != null) {
            predicates.add(qOperation.ddate.loe(critery.toDate));
        }
        if (critery.srcAccountId != null) {
            predicates.add(qOperation.srcAccount.id.eq(critery.srcAccountId));
        }
        if (critery.dstAccountId != null) {
            predicates.add(qOperation.dstAccount.id.eq(critery.dstAccountId));
        }
        BooleanExpression expression = predicates.stream()
                                        //??? optional
                .reduce((predicate, accum) -> accum.and(predicate)).orElse(null);
        Iterable<Operation> iterAccounts = operationRepository.findAll(expression, new Sort(Sort.Direction.ASC, "name"));
        return iterableToList(iterAccounts);
    }

    public void correctBalance(Account srcAccount, Account dstAccount, BigDecimal amount){
        srcAccount = accountRepository.findById(srcAccount.getId()).orElse(null);
        dstAccount = accountRepository.findById(dstAccount.getId()).orElse(null);
        srcAccount.setBalance(srcAccount.getBalance().subtract(amount));
        srcAccount.setBalance(srcAccount.getBalance().add(amount));
    }

    @Override
    public Operation save(Operation operation){
       if(operation.getId() != null){
           Operation oldOperation = findById(operation.getId());
           correctBalance(operation.getSrcAccount(), operation.getDstAccount(), operation.getAmount());
       }
        correctBalance(operation.getDstAccount(), operation.getSrcAccount(), operation.getAmount());
        return super.save(operation);
    }

    @Override
    public void delete(Operation operation){
        correctBalance(operation.getDstAccount(), operation.getSrcAccount(), operation.getAmount());
        super.delete(operation);
    }

    @Override
    public void delete(Long id){
        Operation operation = findById(id);
        super.delete(operation);
    }
}
