package com.bankingmanagement.repositoty;

import com.bankingmanagement.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Branchrepository extends JpaRepository<Branch,Integer> {
}
