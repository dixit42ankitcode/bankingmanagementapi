package com.bankingmanagement.service;

import com.bankingmanagement.exception.Accountdetailsnotfound;
import com.bankingmanagement.model.AccountDTO;
import com.bankingmanagement.model.AccountRequest;

import java.util.List;

public interface Accountservice {
    List<AccountDTO>findAll() throws Accountdetailsnotfound;
    AccountDTO findaccountdetails(long account_no) throws Accountdetailsnotfound;
    AccountDTO save(AccountRequest accountRequest)throws Accountdetailsnotfound;
    String delete(long accountNo)throws Accountdetailsnotfound;
    public void clearcache();
}
