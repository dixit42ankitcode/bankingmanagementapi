package com.bankingmanagement.service;

import com.bankingmanagement.entity.Account;
import com.bankingmanagement.entity.Customer;
import com.bankingmanagement.exception.Accountdetailsnotfound;
import com.bankingmanagement.model.AccountDTO;
import com.bankingmanagement.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.bankingmanagement.repositoty.Accountrepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
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
            Set<Customer>customers= (Set<Customer>) account.getCustomer();
            List<CustomerDTO>customerDTOS=customers.stream().map(customer -> {
                CustomerDTO customerDTO=new CustomerDTO();
                customerDTO.setPhoneNo(customer.getPhoneNo());
                customerDTO.setAddress(customer.getAddress());
                return customerDTO;
            }).collect(Collectors.toList());
            accountDTO.setCustomerDTOS(customerDTOS);
            return accountDTO;
        }).collect(Collectors.toList());
        return accountDTOS;

    }
}
