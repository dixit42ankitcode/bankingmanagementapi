package com.bankingmanagement.service;

import com.bankingmanagement.exception.Loandetailsnotfound;
import com.bankingmanagement.model.LoanDTO;
import com.bankingmanagement.model.LoanRequest;

import java.util.List;


public interface Loanservice {
    public List<LoanDTO> findAll() throws Loandetailsnotfound;
    LoanDTO findloandetails(int loanId) throws Loandetailsnotfound;
    LoanDTO save(LoanRequest loanRequest) throws Loandetailsnotfound;
    String delete(int loanId) throws Loandetailsnotfound;

}
