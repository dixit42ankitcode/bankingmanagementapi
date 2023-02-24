package com.bankingmanagement.controller;

import com.bankingmanagement.exception.BankDetailsNotFound;
import com.bankingmanagement.model.BankDTO;
import com.bankingmanagement.model.BankRequest;
import com.bankingmanagement.service.Bankservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/banks")
public class BankController {
    @Autowired
    Bankservice bankservice;

    @GetMapping
    public ResponseEntity<List<BankDTO>> getBankDetails()  {
        log.info("inside the bank controller.getBankDetails");
        List<BankDTO> bankDTOS= null;
        try{
            bankDTOS=bankservice.findAll();
            log.info("Bank details not found");
            if(CollectionUtils.isEmpty(bankDTOS)){
                log.info("Bank details are not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception exception)
        {
            log.error("exception while finding bank details,",exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<BankDTO>>(bankDTOS,HttpStatus.OK);
    }
    @GetMapping("/{code}")
    public ResponseEntity<BankDTO>getbankbycode(@PathVariable("code")int code){
        log.info(" input to bankcontroller.getbankbycode.code:{}",code);
        if(code<=0)
        {
            log.info("invalid bank code");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BankDTO bankDTO=null;
        try{
            bankDTO=bankservice.findBankdetails(code);
            if(bankDTO==null)
            {
                log.info("bank details are not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }catch (Exception exception)
        {
            log.error("exception while handling bank details by code",exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<BankDTO>(bankDTO,HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<BankDTO>save(@RequestBody BankRequest bankRequest){
        log.info("inside controller.save().bankRequest",bankRequest);
        if(bankRequest==null)
        {
            log.info("invalid request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BankDTO bankDTO=null;
        try {
            bankDTO=bankservice.save(bankRequest);
            log.info("response,bankDTO:{}",bankDTO);


        }catch (Exception exception)
        {
            log.error("exception while handling bank details by code",exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<BankDTO>(bankDTO,HttpStatus.OK);

    }

}


