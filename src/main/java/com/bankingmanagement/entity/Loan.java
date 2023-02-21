package com.bankingmanagement.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "Loan")
public class Loan  implements Serializable {
    public  static  final  long serialversionuid=456;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private  int loanId;
    @Column(name = "loan_type")
    private  String loanType;
    @Column(name = "loan_amount")
    private  double loanamount;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private  Branch branch;

    @ManyToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;

}
