package ru.kolyasnikovkv.bank.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.kolyasnikovkv.bank.critery.ClientCritery;
import ru.kolyasnikovkv.bank.entity.Client;
import ru.kolyasnikovkv.bank.entity.QClient;
import ru.kolyasnikovkv.bank.repository.ClientRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("clientservice")
@Transactional
public class ClientServiceImpl extends ACrudService<Client, Long> implements ClientService{
    @Autowired
    ClientRepository clientRepository;

    @Override
    public CrudRepository<Client, Long> getRepository() {
        return clientRepository;
    }

    @Override
    public Client findByName(String name){
        ClientCritery critery = new ClientCritery(null, name);
        List<Client> clients = findByCritery(critery);
        return clients.size() > 0 ? clients.get(0) : null;

    }

    @Override
    public List<Client> findByIdIn(Long[] ids){
        ClientCritery critery = new ClientCritery(Arrays.asList(ids), "");
        return findByCritery(critery);
    }

    @Override
    public List<Client> findByCritery(ClientCritery critery){
        //??? qClient
        QClient qClient = QClient.client;
        List<BooleanExpression> predicates = new ArrayList<>();
        if(critery.name != null && !critery.name.isEmpty()){
            predicates.add(qClient.name.containsIgnoreCase(critery.name));
        }
        if (critery.ids.size() > 0) {
            predicates.add(qClient.id.in(critery.ids));
        }
        BooleanExpression expression = predicates.stream()
                                        //??? optional
                .reduce((predicate, accum) -> accum.and(predicate)).orElse(null);
        Iterable<Client> iterClients = clientRepository.findAll(expression, new Sort(Sort.Direction.ASC, "name"));
        return iterableToList(iterClients);
    }
}
