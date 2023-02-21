package com.bankingmanagement.repositoty;

import com.bankingmanagement.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Loanreository extends JpaRepository<Loan,Integer> {
}
