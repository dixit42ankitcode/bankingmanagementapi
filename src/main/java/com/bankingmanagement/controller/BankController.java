package com.bankingmanagement.controller;

import com.bankingmanagement.exception.BankDetailsNotFound;
import com.bankingmanagement.model.BankDTO;
import com.bankingmanagement.service.Bankservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

}


