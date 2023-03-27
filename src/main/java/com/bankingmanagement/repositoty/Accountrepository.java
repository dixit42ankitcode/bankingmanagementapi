package com.bankingmanagement.repositoty;

import com.bankingmanagement.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Accountrepository extends JpaRepository<Account,Long> {

}
