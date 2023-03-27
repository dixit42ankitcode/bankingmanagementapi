package com.bankingmanagement.repositoty;

import com.bankingmanagement.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface Bankrepository extends JpaRepository<Bank,Integer> {

}
