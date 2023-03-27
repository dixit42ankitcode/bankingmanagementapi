package com.bankingmanagement.service;

import com.bankingmanagement.exception.BankDetailsNotFound;
import com.bankingmanagement.model.BankDTO;
import com.bankingmanagement.model.BankRequest;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.concurrent.CompletableFuture;


public interface Bankservice{
    List<BankDTO> findAll() throws BankDetailsNotFound;
    BankDTO findBankdetails(int code) throws BankDetailsNotFound, InterruptedException;

    BankDTO save(BankRequest bankRequest) throws BankDetailsNotFound;
    String delete(int bank_code)throws BankDetailsNotFound;

    public void clearcache();

}
