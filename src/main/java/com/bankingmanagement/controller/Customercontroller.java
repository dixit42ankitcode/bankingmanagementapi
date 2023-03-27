package com.bankingmanagement.controller;

import com.bankingmanagement.exception.Customerdetailsnotfound;
import com.bankingmanagement.model.CustomerDTO;
import com.bankingmanagement.model.CustomerRequest;
import com.bankingmanagement.service.Customerservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public class Customercontroller {
    @Autowired
    Customerservice customerservice;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getcustomerdetails() {
        log.info("inside customerdetails.getcustomerdetails()");
        List<CustomerDTO> customerDTOS = null;
        try {
            customerDTOS = customerservice.findAll();
            log.info("customer details not found");
            if (CollectionUtils.isEmpty(customerDTOS)) {
                log.info("customer details not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            log.error("exception while finding customer details.exception");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<CustomerDTO>>(customerDTOS, HttpStatus.OK);

    }

    @GetMapping("/{custid}")
    public ResponseEntity<CustomerDTO> getcustomerbycustid(@PathVariable("custid") int custid) throws Customerdetailsnotfound, InterruptedException {
        log.info("inside Customercontroller.getcustomerbycustid.custid:{}", custid);
        if (custid <= 0) {
            log.info("invalid request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CustomerDTO customerDTO = null;
        customerDTO = customerservice.findcustomerdetails(custid);
        if (customerDTO == null) {
            log.info("customer details not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<CustomerDTO> save(@RequestBody CustomerRequest customerRequest) {
        log.info("inside controller.save().customerRequest:{}", customerRequest);
        if (customerRequest == null) {
            log.info("invalid request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CustomerDTO customerDTO = null;
        try {
            customerDTO = customerservice.save(customerRequest);
            log.info("respose,customerDTO:{}", customerDTO);

        } catch (Exception exception) {
            log.info("customer details not found");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);


    }

    @PutMapping
    public ResponseEntity<CustomerDTO> update(@RequestBody CustomerRequest customerRequest) {
        log.info("inside controller.update().customerRequest:{}", customerRequest);
        if (customerRequest == null) {
            log.info("invalid request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        CustomerDTO customerDTO = null;
        try {
            customerDTO = customerservice.save(customerRequest);
            log.info("respose,customerDTO:{}", customerDTO);

        } catch (Exception exception) {
            log.info("customer details not found");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);


    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam("customerId") int customerId) {
        log.info("input to customercontroller,delete,customerId", customerId);
        String response = null;
        if (customerId <= 0) {
            log.info("invalid customerId");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            response = customerservice.delete(customerId);
            log.info("delete customer details,response", response);
        } catch (Exception exception) {
            log.error("exception while getting customer details");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @GetMapping("/clearcache")
    public ResponseEntity<String> clearcache() {
        try {
            customerservice.clearcache();
        } catch (Exception exception) {
            log.error("exception while calling get", exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("cache has been deleted", HttpStatus.OK);
    }
}
