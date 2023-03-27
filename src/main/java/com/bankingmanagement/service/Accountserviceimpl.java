package com.bankingmanagement.service;

import com.bankingmanagement.entity.Account;
import com.bankingmanagement.entity.Customer;
import com.bankingmanagement.exception.Accountdetailsnotfound;
import com.bankingmanagement.model.AccountDTO;
import com.bankingmanagement.model.AccountRequest;
import com.bankingmanagement.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.bankingmanagement.repositoty.Accountrepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class Accountserviceimpl implements  Accountservice{
    @Autowired
    Accountrepository accountrepository;


    @Override
    public List<AccountDTO> findAll() throws Accountdetailsnotfound{
        log.info("inside Accountserviceimpl.findAll() ");
        List<Account>accounts=accountrepository.findAll();
        log.info("list of Account,account:{}",accounts);
        if (CollectionUtils.isEmpty(accounts))
        {
            log.info("account details not found");
            throw new Accountdetailsnotfound("account details not found");
        }
        List<AccountDTO>accountDTOS=accounts.stream().map(account -> {
            AccountDTO accountDTO=new AccountDTO();
            accountDTO.setAccountType(account.getAccountType());
            accountDTO.setBalance(account.getBalance());
                CustomerDTO customerDTO=new CustomerDTO();
                customerDTO.setPhoneNo(customerDTO.getPhoneNo());
                customerDTO.setAddress(customerDTO.getAddress());
                return accountDTO;
        }).collect(Collectors.toList());
        return accountDTOS;

    }
    @Cacheable("account_no")
    @Override
    public AccountDTO findaccountdetails(long account_no)throws Accountdetailsnotfound {
        log.info("input to accountserviceimpl.findaccountdetails.acccountno:{}",account_no);
        if(account_no<=0)
        {
            log.info("invalid account_no");
            throw new Accountdetailsnotfound("invalid account_no");
        }
        Optional<Account>account=accountrepository.findById(account_no);
        if(account==null)
        {
            log.info("accoupresentnt details are not found ");
            throw new Accountdetailsnotfound("accoupresentnt details are not found");
        }
        log.info("account details for accountno:{},details:{}",account_no,account.get());
        Account account1=account.get();
        AccountDTO accountDTO=new AccountDTO();
        accountDTO.setAccountType(account1.getAccountType());
        accountDTO.setBalance(account1.getBalance());
        Customer customer=account1.getCustomer();
            CustomerDTO customerDTO=new CustomerDTO();
            customerDTO.setPhoneNo(customerDTO.getPhoneNo());
            customerDTO.setAddress(customerDTO.getAddress());
            accountDTO.setCustomerDTOS(customerDTO);
        log.info("end of accountserviceimpl.findaccountdetails");
        return accountDTO;
    }
    @CacheEvict(value = "account_no",allEntries = true)
    @Override
    public void clearcache() {

    }

    @Override
    public AccountDTO save(AccountRequest accountRequest) throws Accountdetailsnotfound {
        log.info("input to accpuntservicedimpl,save,accountrequest:{}",accountRequest);
        if (accountRequest==null)
        {
            log.info("invalid account request");
            throw new Accountdetailsnotfound("invalid account request");
        }
        Account account=new Account();
        account.setAccountType(account.getAccountType());
        account.setBalance(account.getBalance());
        Account account1 = accountrepository.save(account);
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setAccountType(account1.getAccountType());
            accountDTO.setBalance(account1.getBalance());
            return accountDTO;
    }

    @Override
    public String delete(long accountNo) throws Accountdetailsnotfound{
        log.info("input to accountservcieimpl,delete,accountNo",accountNo);
        if (accountNo<=0)
        {
            log.info("invalid request");
            throw new Accountdetailsnotfound("invalid request");
        }
        accountrepository.deleteById(accountNo);

        return "account details are deleted ";
    }
}
