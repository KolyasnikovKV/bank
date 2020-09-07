package ru.kolyasnikovkv.bank;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolyasnikovkv.bank.service.ACrudService;

import java.io.Serializable;
import org.slf4j.Logger;

public abstract class AbstractSimpleRestController<T, PK extends Serializable> {
    public abstract ACrudService<T, PK> getService();
    public abstract Logger getLogger();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<T> getById(@PathVariable("id") PK id){
        getLogger().info("Fetching Client with id {}", id);
        T entity = (T) getService().findById(id);
        if(entity == null){
            getLogger().error("Client with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Client with id " + id
            +  " no found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<T>(entity, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public T create(@RequestBody T o) throws Exception{
        getLogger().info("Create: " + o);
        T ret = getService().save(o);
        getLogger().info("Successfully created: " + o);
        return ret;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public T update(@RequestBody T o, @PathVariable PK id) throws Exception{
        getLogger().info("Update: " + o);
        T ret = getService().save(o);
        getLogger().info("Successfully update: " + o);
        return ret;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@PathVariable PK id) throws Exception{
        getLogger().info("Delete: " + id);
        getService().delete(id);
        getLogger().info("Successfully delete: " + id);
        return "";
    }


}
