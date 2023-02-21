package com.bankingmanagement.repositoty;

import com.bankingmanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Customerrepository extends JpaRepository<Customer,Integer> {
}
