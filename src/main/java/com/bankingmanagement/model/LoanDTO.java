package com.bankingmanagement.model;

import lombok.Data;

import java.util.List;

@Data
public class LoanDTO {
    private  String loanType;
    private  double loanamount;
   private List<BranchDTO>branchDTOS;
   private List<CustomerDTO>customerDTOS;



}
