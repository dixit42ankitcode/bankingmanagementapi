package com.bankingmanagement.model;

import lombok.Data;

import java.util.List;

@Data
public class LoanDTO {
    private  String loanType;
    private  double loanamount;
   private BranchDTO branchDTOS;
   private CustomerDTO customerDTOS;



}
