package com.bankingmanagement.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "Branch")
public class Branch  implements Serializable {
    public  static  final  long serialversionuid=123;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int branchId;
    @Column(name = "branch_name")
     private String name;
    @Column(name = "branch_address")
    private  String address;
    @ManyToOne
    @JoinColumn(name = "bank_code")
    private Bank bank;
    @OneToMany(mappedBy = "branch")
    private Set<Account> account;
    @OneToMany(mappedBy = "branch")
    private Set <Loan> loan;



}
