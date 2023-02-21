package com.bankingmanagement.model;

import lombok.Data;

import java.util.List;

@Data
public class AccountDTO {
    private String accountType;

    private double balance;
    private List<CustomerDTO> customerDTOS;
}
