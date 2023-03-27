package com.bankingmanagement.repositoty;

import com.bankingmanagement.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Customerrepository extends JpaRepository<Customer,Integer> {

    static Optional<Customer> findBycustName() {
        return null;
    }

}
