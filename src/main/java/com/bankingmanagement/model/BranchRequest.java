package com.bankingmanagement.model;

import lombok.Data;

@Data
public class BranchRequest {
    private String name;
    private  String address;
    private int branchId;

}
