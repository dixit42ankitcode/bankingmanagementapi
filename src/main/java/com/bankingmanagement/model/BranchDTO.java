package com.bankingmanagement.model;

import com.bankingmanagement.entity.Account;
import com.bankingmanagement.entity.Bank;
import com.bankingmanagement.entity.Loan;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class BranchDTO {
    private String name;
    private  String address;
    private int branchId;
    private BankDTO bankDTO;
    private List<AccountDTO> accountdto;
    private List <LoanDTO> loandtos;

}
