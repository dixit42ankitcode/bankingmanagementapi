package com.bankingmanagement.controller;

import com.bankingmanagement.model.CustomerDTO;
import com.bankingmanagement.service.Customerservice;
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
@RequestMapping("api/v1/customers")
public class Customercontroller {
    @Autowired
    Customerservice customerservice;
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getcustomerdetails() {
        log.info("inside customerdetails.getcustomerdetails()");
        List<CustomerDTO> customerDTOS=null;
        try
        {   customerDTOS=customerservice.findAll();
            log.info("customer details not found");
        if (CollectionUtils.isEmpty(customerDTOS))
        {
            log.info("customer details not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } catch (Exception exception)
        {log.error("exception while finding customer details.exception");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<CustomerDTO>>(customerDTOS,HttpStatus.OK);

        }

}

