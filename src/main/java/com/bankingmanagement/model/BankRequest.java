package com.bankingmanagement.model;

import lombok.Data;

@Data
public class BankRequest {
    private String name;
    private int code;
    private String address;
}
