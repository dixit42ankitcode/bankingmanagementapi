package com.bankingmanagement.model;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDTO {
    private long phoneNo;
    private String address;
    private int customerId;
    private List<LoanDTO> loanDTOS;
    private List<AccountDTO>accountDTOS;

}
