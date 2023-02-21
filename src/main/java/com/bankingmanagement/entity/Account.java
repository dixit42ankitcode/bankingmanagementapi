package com.bankingmanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "Account")
public class Account implements Serializable {
    public static final long serialversionuid = 12345;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_no")

    private long accontNo;
    @Column(name = "account_type")

    private String accountType;

    private double balance;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;

}
