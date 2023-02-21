package com.bankingmanagement.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "customer")
public class Customer implements Serializable {
    public static final long serialversionuid=1238;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "cust_id")
    private  int customerId;
    @Column(name = "cust_name")
    private String name;
    @Column(name = "cust_phone")
    private long phoneNo;
    @Column(name = "cust_add")
    private String address;
    @OneToMany(mappedBy = "customer")
    private Set<Account> account;
    @OneToMany(mappedBy = "customer")
    private Set<Loan >loan;


}
