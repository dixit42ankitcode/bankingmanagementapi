package com.bankingmanagement.repositoty;

import com.bankingmanagement.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Branchrepository extends JpaRepository<Branch,Integer> {
    Optional<Branch> findByaddress(String delhi);
}
