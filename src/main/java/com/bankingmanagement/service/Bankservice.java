package com.bankingmanagement.service;

import com.bankingmanagement.exception.BankDetailsNotFound;
import com.bankingmanagement.model.BankDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface Bankservice{
    List<BankDTO> findAll() throws BankDetailsNotFound;

}
