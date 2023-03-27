package com.bankingmanagement.service;

import com.bankingmanagement.entity.Account;
import com.bankingmanagement.entity.Customer;
import com.bankingmanagement.entity.Loan;

import com.bankingmanagement.exception.Customerdetailsnotfound;
import com.bankingmanagement.model.AccountDTO;
import com.bankingmanagement.model.CustomerDTO;
import com.bankingmanagement.model.CustomerRequest;
import com.bankingmanagement.model.LoanDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.bankingmanagement.repositoty.Customerrepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setPhoneNo(customer.getPhoneNo());
            customerDTO.setAddress(customer.getAddress());
            Set<Account> accounts = customer.getAccount();
            List<AccountDTO> accountDTOS = null;
            if (!CollectionUtils.isEmpty(accounts)) {
                accountDTOS = accounts.stream().map(account -> {
                    AccountDTO accountDTO = new AccountDTO();
                    accountDTO.setAccountType(account.getAccountType());
                    accountDTO.setBalance(account.getBalance());
                    return accountDTO;
                }).collect(Collectors.toList());
            }
            Set<Loan> loans = customer.getLoan();
            List<LoanDTO>loanDTOS1=null;
            if (!CollectionUtils.isEmpty(loans)){
            List<LoanDTO> loanDTOS = loans.stream().map(loan -> {
                LoanDTO loanDTO = new LoanDTO();
                loanDTO.setLoanType(loan.getLoanType());
                loanDTO.setLoanamount(loan.getLoanamount());
                return loanDTO;
            }).collect(Collectors.toList());
            }
            customerDTO.setLoanDTOS(loanDTOS1);
            customerDTO.setAccountDTOS(accountDTOS);
            return customerDTO;
        }).collect(Collectors.toList());
            return customerDTOS;
    }
    @Cacheable("custid")
    @Override
    public CustomerDTO findcustomerdetails(int custid) throws Customerdetailsnotfound, InterruptedException {
        log.info("inside customerserviceimpl.findcustomerdetails.custid:{}",custid);
        if(custid<=0)
        {
            log.info("customer details not found");
            throw new Customerdetailsnotfound("invalid customer details");
        }
        Optional<Customer>customer= customerrepository.findById(100);
        Thread.sleep(2000l);
        if(customer==null)
        {
            log.info("customer details not found for custid:{}",custid);
            throw new Customerdetailsnotfound("customer details not found");
        }
        log.info("customer details for custid:{},details:{}",custid,customer.get());
        Customer customer1=customer.get();
        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setPhoneNo(customer1.getPhoneNo());
        customerDTO.setAddress(customer1.getAddress());
        Set<Account>accounts=customer1.getAccount();
        List<Account>accountList=new ArrayList<>();
        if (!CollectionUtils.isEmpty(accounts)) {
            List<AccountDTO> accountDTOS = accounts.stream().map(account -> {
                AccountDTO accountDTO = new AccountDTO();
                accountDTO.setAccountType(account.getAccountType());
                accountDTO.setBalance(account.getBalance());
                return accountDTO;
            }).collect(Collectors.toList());
            customerDTO.setAccountDTOS(accountDTOS);
        }
        Set<Loan>loans=customer1.getLoan();
        List<Loan>loanList=new ArrayList<>();
        if (!CollectionUtils.isEmpty(loans)) {
            List<LoanDTO> loanDTOS = loans.stream().map(loan -> {
                LoanDTO loanDTO = new LoanDTO();
                loanDTO.setLoanType(loan.getLoanType());
                loanDTO.setLoanamount(loan.getLoanamount());
                return loanDTO;
            }).collect(Collectors.toList());
            customerDTO.setLoanDTOS(loanDTOS);
        }

        log.info("end of customerserviceimpl.findcustomerdetails");
        return customerDTO;
    }
    @CacheEvict(value = "custid",allEntries = true)
    @Override
    public void clearcache() {

    }
    @Override
    public CustomerDTO save(CustomerRequest customerRequest) throws Customerdetailsnotfound {
        log.info("inside customerimpl.save().Customerdetailsnotfound:{}",customerRequest);
        if(customerRequest==null)
        {
            log.info("invalid customer details");
            throw new Customerdetailsnotfound("invalid request");
        }
        Customer customer=new Customer();
        customer.setPhoneNo(customerRequest.getPhoneNo());
        customer.setAddress(customerRequest.getAddress());
        Customer customer1=customerrepository.save(customer);
        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setPhoneNo(customer1.getPhoneNo());
        customerDTO.setAddress(customer1.getAddress());
        return customerDTO;
    }

    @Override
    public String delete(int customerId) throws Customerdetailsnotfound {
        log.info("input to customerserviceimpl,delete,customerId");
        if (customerId<=0)
        {
            log.info("invalid customerId");
            throw new Customerdetailsnotfound("invalid customerId");
        }
        customerrepository.deleteById(customerId);
        return "customer details are deleted";
    }


}
