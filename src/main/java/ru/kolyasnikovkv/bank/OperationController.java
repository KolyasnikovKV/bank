package ru.kolyasnikovkv.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolyasnikovkv.bank.util.Operations;
import ru.kolyasnikovkv.bank.critery.OperationCritery;
import ru.kolyasnikovkv.bank.entity.Operation;
import ru.kolyasnikovkv.bank.service.ACrudService;
import ru.kolyasnikovkv.bank.service.OperationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/operation")
public class OperationController extends AbstractSimpleRestController<Operation, Long> {
    public static final Logger logger = LoggerFactory.getLogger(OperationController.class);
    final static SimpleDateFormat parser =  new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    OperationService operationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Operations> list(
        @RequestParam(value = "ids", required = false) List<Long> ids,
        @RequestParam(value = "src_account_id", required = false) Long srcAccountId,
        @RequestParam(value = "dst_account_id", required = false) Long dstAccountId,
        @RequestParam(value = "from_date", required = false) String fromDate,
        @RequestParam(value = "to_date", required = false) String toDate){
        logger.info("Params ids: " + ids + ";");
        OperationCritery operationCritery = new OperationCritery();
         if(ids != null && ids.size() > 0){
            operationCritery.ids = ids;
        }
        operationCritery.srcAccountId = srcAccountId;
        operationCritery.dstAccountId = dstAccountId;
        if(fromDate != null && !fromDate.isEmpty()){
            Date ddate = null;
            try{
                ddate = parser.parse(fromDate);
            } catch (ParseException e){
                e.printStackTrace();
                return new ResponseEntity(new CustomErrorType("Error Operation.fromDate " + e.getMessage()),
                        HttpStatus.NOT_FOUND);
            }
            operationCritery.fromDate = ddate;
        }
        if(toDate != null && !fromDate.isEmpty()){
            Date ddate = null;
            try{
                ddate = parser.parse(toDate);
            } catch (ParseException e){
                e.printStackTrace();
                return new ResponseEntity(new CustomErrorType("Error Operation.toDate " + e.getMessage()),
                        HttpStatus.NOT_FOUND);
            }
            operationCritery.toDate = ddate;
        }
        List<Operation> operations = operationService.findByCritery(operationCritery);
        if(operations.isEmpty()){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity<Operations>(new Operations(operations), HttpStatus.OK);

    }

    @Override
    public ACrudService<Operation, Long> getService(){
        return (ACrudService<Operation, Long>) operationService;
    }

    @Override
    public Logger getLogger(){
        return logger;
    }
}
