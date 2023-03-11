package com.bankingmanagement.controller;

import com.bankingmanagement.exception.Accountdetailsnotfound;
import com.bankingmanagement.model.AccountDTO;
import com.bankingmanagement.model.AccountRequest;
import com.bankingmanagement.service.Accountservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/accounts")
public class Accountcontroller {
    @Autowired
    Accountservice accountservice;

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getaccountdetails() {
        log.info("inside Accountcontroller.getaccountdetails() ");
        List<AccountDTO> accountDTOS = null;
        try {
            accountDTOS = accountservice.findAll();
            log.info("account details not found");
            if (CollectionUtils.isEmpty(accountDTOS)) {
                log.info("account details are not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            log.error("exception while finding account details", exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<AccountDTO>>(accountDTOS, HttpStatus.OK);
    }

    @GetMapping("/{account_no}")
    public ResponseEntity<AccountDTO> getaccountbyaccountno(@PathVariable("account_no") long account_no) throws Accountdetailsnotfound {
        log.info("input to cotroller,getaccountbyaccountno.account_no:{}", account_no);
        if (account_no <= 0) {
            log.info("invalid accountno");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        AccountDTO accountDTO = null;

            accountDTO = accountservice.findaccountdetails(account_no);
            if (accountDTO == null) {
                log.info("account details not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);



        }
        return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AccountDTO> save(@RequestBody AccountRequest accountRequest) {
        log.info("inside controller,save,accountRequest:{}", accountRequest);
        if (accountRequest == null) {
            log.info("invalid request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        AccountDTO accountDTO = null;
        try {
            accountDTO = accountservice.save(accountRequest);
            log.info("response,accountDTO:{}", accountDTO);
        } catch (Exception exception) {
            log.info("exception while finding accunt details");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AccountDTO> update(@RequestBody AccountRequest accountRequest) {
        log.info("inside controller,update,accountRequest:{}", accountRequest);
        if (accountRequest == null) {
            log.info("invalid request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        AccountDTO accountDTO = null;
        try {
            accountDTO = accountservice.save(accountRequest);
            log.info("response,accountDTO:{}", accountDTO);
        } catch (Exception exception) {
            log.info("exception while finding accunt details");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.OK);

    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam("accountNo") int accountNo) {
        log.info("input to accountcontroller,delete,accountno", accountNo);
        String response = null;
        if (accountNo <= 0) {
            log.info("invalid accountNo");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            response = accountservice.delete(accountNo);
            log.info("delete account details,response", response);
        } catch (Exception exception) {
            log.info("exception while gettng account details");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}
