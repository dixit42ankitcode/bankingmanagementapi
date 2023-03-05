package com.bankingmanagement.model;

import lombok.Data;

@Data

public class AccountRequest {
    private String accountType;

    private double balance;
}
