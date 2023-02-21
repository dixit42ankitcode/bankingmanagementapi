package com.bankingmanagement.repositoty;

import com.bankingmanagement.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Bankrepository extends JpaRepository<Bank,Integer> {
}
