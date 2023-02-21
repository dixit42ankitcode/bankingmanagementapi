package com.bankingmanagement.model;

import com.bankingmanagement.entity.Branch;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;


@Data
public class BankDTO {
    private String name;
    private int code;
    private String address;
    private List<BranchDTO> branchDTOS;

}
