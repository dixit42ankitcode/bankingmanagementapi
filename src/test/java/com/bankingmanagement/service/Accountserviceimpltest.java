package com.bankingmanagement.service;

import com.bankingmanagement.entity.Account;
import com.bankingmanagement.entity.Bank;
import com.bankingmanagement.entity.Customer;
import com.bankingmanagement.exception.Accountdetailsnotfound;
import com.bankingmanagement.model.AccountDTO;
import com.bankingmanagement.model.AccountRequest;
import com.bankingmanagement.repositoty.Accountrepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class Accountserviceimpltest {
    @Mock
    private Accountrepository accountrepository;
    @InjectMocks
    private Accountserviceimpl accountservice;
    @Test
    public void testfindall() throws Accountdetailsnotfound {
        Account account=new Account();
        account.setAccountType("saving");
        account.setBalance(200000);
        List<Account>accountList=new ArrayList<>();
        accountList.add(account);
        when(accountrepository.findAll()).thenReturn(accountList);
        List<AccountDTO>accountDTOS=accountservice.findAll();
        assertEquals(1,accountDTOS.size());
    }
    @Test(expected = Accountdetailsnotfound.class)
    public void testfindallwithemptydata()throws Accountdetailsnotfound{
        List<Account>accountList=null;
        when(accountrepository.findAll()).thenReturn(accountList);
        List<AccountDTO>accountDTOS=accountservice.findAll();
        assertNull(accountList);
    }
    @Test
    public void testfindallwithcustomer() throws Accountdetailsnotfound {
        Account account=new Account();
        account.setAccountType("saving");
        account.setBalance(400000);
        Customer customer=new Customer();
        customer.setName("amit");
        customer.setAddress("mumbai");
        account.setCustomer(customer);
        List<Account>accountList=new ArrayList<>();
        accountList.add(account);
        when(accountrepository.findAll()).thenReturn(accountList);
        List<AccountDTO>accountDTOS=accountservice.findAll();
        assertEquals(1,accountDTOS.size());
    }
    @Test
    public void testfindaccountdetails() throws Accountdetailsnotfound {
        Account account=new Account();
        account.setAccountType("saving");
        account.setBalance(100000);
        List<Account>accountList=new ArrayList<>();
        accountList.add(account);
        when(accountrepository.findById(anyLong())).thenReturn(Optional.of(account));
        AccountDTO accountDTO=accountservice.findaccountdetails(70);
        assertEquals("saving",accountDTO.getAccountType());
    }
    @Test(expected = Accountdetailsnotfound.class)
    public void testfindaccountdetailswithemptydata() throws Accountdetailsnotfound {
        List<Account>account=null;
        when(accountrepository.findById(anyLong())).thenReturn(null);
        AccountDTO accountDTO=accountservice.findaccountdetails(71);
    }
    @Test
    public void testfindaccountdetailswithcustomer() throws Accountdetailsnotfound {
        Account account=new Account();
        account.setBalance(100000);
        account.setAccountType("current");
        Customer customer=new Customer();
        customer.setAddress("lucknow");
        customer.setName("ankit");
        account.setCustomer(customer);
        List<Account>accountList=new ArrayList<>();
        accountList.add(account);
        when(accountrepository.findById(anyLong())).thenReturn(Optional.of(account));
        AccountDTO accountDTO=accountservice.findaccountdetails(71);
        assertEquals("current",accountDTO.getAccountType());
    }
    @Test
    public  void  testsave() throws Accountdetailsnotfound {
        Account account=new Account();
        account.setBalance(10000);
        account.setAccountType("current");
        List<Account>accountList=new ArrayList<>();
        accountList.add(account);
        when(accountrepository.save(any())).thenReturn(account);
        AccountDTO accountDTO=accountservice.save(new AccountRequest());
        assertEquals("saving",accountDTO.getAccountType());
    }
    @Test(expected = Accountdetailsnotfound.class)
    public void testsavewithemptydata() throws Accountdetailsnotfound {
        List<Account>accountList=null;
        when(accountrepository.save(any())).thenReturn(null);
        AccountDTO accountDTO=accountservice.save(new AccountRequest());
    }


}
