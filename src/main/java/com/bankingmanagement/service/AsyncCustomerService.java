package com.bankingmanagement.service;

import com.bankingmanagement.exception.Customerdetailsnotfound;
import com.bankingmanagement.model.CustomerDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public interface AsyncCustomerService {


    CompletableFuture<CustomerDTO> findbycustId(int cust_id) throws Customerdetailsnotfound, InterruptedException;

    CompletableFuture<CustomerDTO>findbycustName(String name)throws Customerdetailsnotfound,InterruptedException;

    public void clearcache();
    public void clearcache1();

}