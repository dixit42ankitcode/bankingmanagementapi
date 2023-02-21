package com.bankingmanagement.controller;

import com.bankingmanagement.model.AccountDTO;
import com.bankingmanagement.service.Accountservice;
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
@RequestMapping("api/v1/accounts")
public class Accountcontroller {
    @Autowired
    Accountservice accountservice;
    @GetMapping
    public ResponseEntity<List<AccountDTO>>getaccountdetails(){
        log.info("inside Accountcontroller.getaccountdetails() ");
        List<AccountDTO>accountDTOS=null;
        try {
            accountDTOS=accountservice.findAll();
            log.info("account details not found");
            if (CollectionUtils.isEmpty(accountDTOS))
            {
                log.info("account details are not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception exception)
        {
            log.error("exception while finding account details",exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<AccountDTO>>(accountDTOS,HttpStatus.OK);
    }
}
