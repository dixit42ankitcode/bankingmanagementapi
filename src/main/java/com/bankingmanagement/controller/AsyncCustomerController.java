package com.bankingmanagement.controller;

import com.bankingmanagement.model.CustomerDTO;
import com.bankingmanagement.service.AsyncCustomerService;
import com.bankingmanagement.service.Customerservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
@Slf4j
@RestController
@RequestMapping("api/v1/customers1")

public class AsyncCustomerController {
    @Autowired
    AsyncCustomerService asyncCustomerService;

    @GetMapping("/byidandname")
    public ResponseEntity<List<CustomerDTO>> getcustomerbyNameandId(@RequestParam("cust_id") int cust_id, @RequestParam("name")String name){
        log.info("inside customercontroller,getcustomerbyNameandId");
        CompletableFuture<CustomerDTO> customerDTO=null;
        CompletableFuture<CustomerDTO>customerDTO1=null;
        List<CustomerDTO>customerDTOS=new ArrayList<>();
        try{
            customerDTO = asyncCustomerService.findbycustId(cust_id);
            log.info("get customer by id response",customerDTO);
            customerDTO1=asyncCustomerService.findbycustName(name);
            log.info("get customer by name response",customerDTO1);
            log.info("get customer by name response",customerDTO1);
            CompletableFuture.allOf(customerDTO,customerDTO1).join();
            customerDTOS.add(customerDTO.get());
            customerDTOS.add(customerDTO1.get());
            if (customerDTOS.isEmpty()){
                log.info("customer details are not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception){
        log.error("exception while calling getcustomer",exception);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
        return new ResponseEntity<List<CustomerDTO>>(customerDTOS,HttpStatus.OK);
}
}
