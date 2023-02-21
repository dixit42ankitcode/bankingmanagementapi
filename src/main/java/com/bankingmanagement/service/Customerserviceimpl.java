package com.bankingmanagement.service;

import com.bankingmanagement.entity.Account;
import com.bankingmanagement.entity.Customer;
import com.bankingmanagement.entity.Loan;

import com.bankingmanagement.exception.Customerdetailsnotfound;
import com.bankingmanagement.model.AccountDTO;
import com.bankingmanagement.model.CustomerDTO;
import com.bankingmanagement.model.LoanDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.bankingmanagement.repositoty.Customerrepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class Customerserviceimpl implements Customerservice {
    @Autowired
    Customerrepository customerrepository;

    @Override
    public List<CustomerDTO> findAll() throws Customerdetailsnotfound {
        log.info("inside Customerserviceimpl.findAll()");
        List<Customer> customers=customerrepository.findAll();
        log.info("list of Customers,customers:{}",customers);
        if(CollectionUtils.isEmpty(customers))
        {
            log.info("customers details not found");
            throw new Customerdetailsnotfound("customer details not found");
        }
        List<CustomerDTO> customerDTOS=customers.stream().map(customer -> {
            CustomerDTO customerDTO=new CustomerDTO();
            customerDTO.setPhoneNo(customer.getPhoneNo());
            customerDTO.setAddress(customer.getAddress());
            Set<Account>accounts=customer.getAccount();
            List<AccountDTO>accountDTOS=accounts.stream().map(account -> {
                        AccountDTO accountDTO = new AccountDTO();
                        accountDTO.setAccountType(account.getAccountType());
                        accountDTO.setBalance(account.getBalance());
                        return accountDTO;
                    }).collect(Collectors.toList());
                Set<Loan>loans=customer.getLoan();
                List<LoanDTO>loanDTOS=loans.stream().map(loan -> {
                    LoanDTO loanDTO=new LoanDTO();
                    loanDTO.setLoanType(loan.getLoanType());
                    loanDTO.setLoanamount(loan.getLoanamount());
                    return loanDTO;
                }).collect(Collectors.toList());
                customerDTO.setLoanDTOS(loanDTOS);
                customerDTO.setAccountDTOS(accountDTOS);
                return customerDTO;
        }).collect(Collectors.toList());
            return customerDTOS;



    }
}
