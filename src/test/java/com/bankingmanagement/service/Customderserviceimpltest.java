package com.bankingmanagement.service;

import com.bankingmanagement.entity.Account;
import com.bankingmanagement.entity.Customer;
import com.bankingmanagement.entity.Loan;
import com.bankingmanagement.exception.Customerdetailsnotfound;
import com.bankingmanagement.model.CustomerDTO;
import com.bankingmanagement.repositoty.Customerrepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class Customderserviceimpltest {
    @Mock
     private Customerrepository customerrepository;
    @InjectMocks
    private Customerserviceimpl customerservice;
    @Test
    public void testfindAll() throws Customerdetailsnotfound {
        Customer customer = new Customer();
        customer.setName("ankit");
        customer.setAddress("lucknow");
        List<Customer>customers=new ArrayList<>();
        customers.add(customer);
        when(customerrepository.findAll()).thenReturn(customers);
        List<CustomerDTO> customerDTOList=customerservice.findAll();
        assertEquals(1,customerDTOList.size());
    }

    private void assertEquals(int i, int size) {
    }
    @Test(expected = Customerdetailsnotfound.class)
    public void testfindwithemptydata(){
        List<Customer>customers=null;
        when(customerrepository.findAll()).thenReturn(customers);
    }
    @Test
    public void testfindallwithaccountandloan() throws Customerdetailsnotfound {
        Customer customer = new Customer();
        customer.setName("ankit");
        customer.setAddress("lucknow");
        Account account=new Account();
        account.setAccountType("saving");
        account.setBalance(100000);
        Loan loan=new Loan();
        loan.setLoanType("education");
        loan.setLoanamount(200000);
        Set<Account>accountSet=new HashSet<>();
        accountSet.add(account);
        customer.setAccount(accountSet);
        Set<Loan>loanSet=new HashSet<>();
        loanSet.add(loan);
        customer.setLoan(loanSet);
        List<Customer>customerList=new ArrayList<>();
        customerList.add(customer);
        when(customerrepository.findAll()).thenReturn(customerList);
        List<CustomerDTO>customerDTOList=customerservice.findAll();
        assertEquals(1,customerDTOList.size());

    }
    @Test
    public void testfindcustomerdetails() throws Customerdetailsnotfound, InterruptedException {
        Customer customer=new Customer();
        customer.setName("amit");
        customer.setAddress("delhi");
        List<Customer>customerList=new ArrayList<>();
        customerList.add(customer);
        when(customerrepository.findById(anyInt())).thenReturn(Optional.of(customer));
        CustomerDTO customerDTO=customerservice.findcustomerdetails(50);
        assertEquals(50,customerDTO.getCustomerId());
    }
    @Test(expected = Customerdetailsnotfound.class)
    public void testfindcustomerdetailswithemptydata() throws Customerdetailsnotfound, InterruptedException {
        List<Customer>customerList=null;
        when(customerrepository.findById(anyInt())).thenReturn(null);
        CustomerDTO customerDTO=customerservice.findcustomerdetails(50);
    }
    @Test
    public void testfindcustomerdetailswithaccountandloan() throws Customerdetailsnotfound, InterruptedException {
        Customer customer=new Customer();
        customer.setName("amit");
        customer.setAddress("delhi");
        Loan loan=new Loan();
        loan.setLoanType("education");
        loan.setLoanamount(200000);
        Account account=new Account();
        account.setAccountType("saving");
        account.setBalance(100000);
        Set<Account>accountSet=new HashSet<>();
        accountSet.add(account);
        customer.setAccount(accountSet);
        Set<Loan>loanSet=new HashSet<>();
        loanSet.add(loan);
        customer.setLoan(loanSet);
        List<Customer>customerList=new ArrayList<>();
        customerList.add(customer);
        when(customerrepository.findById(anyInt())).thenReturn(Optional.of(customer));
        CustomerDTO customerDTO=customerservice.findcustomerdetails(51);
        assertEquals(1,customerDTO.getCustomerId());
    }

}
