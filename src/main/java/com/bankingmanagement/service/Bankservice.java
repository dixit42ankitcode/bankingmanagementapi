package com.bankingmanagement.service;

import com.bankingmanagement.exception.BankDetailsNotFound;
import com.bankingmanagement.model.BankDTO;
import com.bankingmanagement.model.BankRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface Bankservice{
    List<BankDTO> findAll() throws BankDetailsNotFound;
    BankDTO findBankdetails(int code) throws BankDetailsNotFound;
    BankDTO save(BankRequest bankRequest) throws BankDetailsNotFound;
    String delete(int bank_code)throws BankDetailsNotFound;

}
