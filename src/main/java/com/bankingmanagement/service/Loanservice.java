package com.bankingmanagement.service;

import com.bankingmanagement.exception.Loandetailsnotfound;
import com.bankingmanagement.model.LoanDTO;

import java.util.List;


public interface Loanservice {
    public List<LoanDTO> findAll() throws Loandetailsnotfound;

}
