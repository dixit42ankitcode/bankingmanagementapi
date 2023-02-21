package com.bankingmanagement.controller;

import com.bankingmanagement.model.LoanDTO;
import com.bankingmanagement.service.Loanservice;
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
@RequestMapping("api/v1/loans")
public class Loancontroller {
    @Autowired
    Loanservice loanservice;
    @GetMapping
    public ResponseEntity<List<LoanDTO>> getloandetails(){
        log.info("inside Loancontroller.getloandetails() ");
        List<LoanDTO>loanDTOS=null;
        try {
            loanDTOS=loanservice.findAll();
            log.info("loan details nmot found");
            if(CollectionUtils.isEmpty(loanDTOS))
            {
                log.info("loan details not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
                    }
        catch (Exception exception)
        {
            log.error("exception while finding loan details,exception");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<LoanDTO>>(loanDTOS,HttpStatus.OK);
    }
}
