package com.bankingmanagement.service;

import com.bankingmanagement.entity.Branch;
import com.bankingmanagement.entity.Customer;
import com.bankingmanagement.entity.Loan;
import com.bankingmanagement.exception.Loandetailsnotfound;
import com.bankingmanagement.model.LoanDTO;
import com.bankingmanagement.repositoty.Loanreository;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class Loanserviceimpltest {
    @Mock
    private Loanreository loanreository;
    @InjectMocks
    private  Loanserviceimpl loanservice;
    @Test
    public void testfindall() throws Loandetailsnotfound {
        Loan loan=new Loan();
        loan.setLoanType("personal");
        loan.setLoanamount(100000);
        List<Loan>loanList=new ArrayList<>();
        loanList.add(loan);
        when(loanreository.findAll()).thenReturn(loanList);
        List<LoanDTO>loanDTOS=loanservice.findAll();
        assertEquals(1,loanDTOS.size());
    }
    @Test(expected = Loandetailsnotfound.class)
    public void testfindallwithemptydata() throws Loandetailsnotfound {
        List<Loan>loanList=null;
        when(loanreository.findAll()).thenReturn(loanList);
        List<LoanDTO>loanDTOS=loanservice.findAll();
        assertNull(loanList);
    }
    @Test
    public void testfindallwithbranchandcustomer() throws Loandetailsnotfound {
        Loan loan=new Loan();
        loan.setLoanType("personal");
        loan.setLoanamount(100000);
        Branch branch=new Branch();
        branch.setName("behta saboli");
        branch.setAddress("lucknow");
        Customer customer=new Customer();
        customer.setAddress("delhi");
        customer.setName("ankit");
        loan.setBranch(branch);
        loan.setCustomer(customer);
        List<Loan>loanList=new ArrayList<>();
        loanList.add(loan);
        when(loanreository.findAll()).thenReturn(loanList);
        List<LoanDTO>loanDTOS=loanservice.findAll();
        assertEquals(1,loanDTOS.size());
    }
    @Test
    public void testfindloandetails() throws Loandetailsnotfound {
        Loan loan=new Loan();
        loan.setLoanType("car");
        loan.setLoanamount(200000);
        List<Loan>loanList=new ArrayList<>();
        loanList.add(loan);
        when(loanreository.findById(anyInt())).thenReturn(Optional.of(loan));
        LoanDTO loanDTO=loanservice.findloandetails(60);
        assertEquals("car",loanDTO.getLoanType());
    }
    @Test(expected = Loandetailsnotfound.class)
    public void testfindloandetailswithemptydata() throws Loandetailsnotfound {
        List<Loan>loanList=null;
        when(loanreository.findById(anyInt())).thenReturn(null);
        LoanDTO loanDTO=loanservice.findloandetails(60);
        assertNull(loanList);
    }

}
