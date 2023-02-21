package com.bankingmanagement.service;

import com.bankingmanagement.exception.Accountdetailsnotfound;
import com.bankingmanagement.model.AccountDTO;

import java.util.List;

public interface Accountservice {
    List<AccountDTO>findAll() throws Accountdetailsnotfound;
}
