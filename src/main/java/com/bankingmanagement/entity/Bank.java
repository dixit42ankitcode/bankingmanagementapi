package com.bankingmanagement.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Bank")
public class Bank  implements Serializable {
    public static final long serialevrsionuid=123456;
    @Id
    @Column(name="bank_code")
    private int code;

    private String name;
    private String address;
    @OneToMany(mappedBy = "bank")
    private Set<Branch> branch;


}
