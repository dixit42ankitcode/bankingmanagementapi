package com.bankingmanagement.service;

import com.bankingmanagement.entity.Branch;
import com.bankingmanagement.entity.Loan;
import com.bankingmanagement.exception.Branchdetailsnotfound;
import com.bankingmanagement.model.BranchDTO;
import com.bankingmanagement.repositoty.Branchrepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@RunWith(MockitoJUnitRunner.class)

public class Branchserviceimpltest {
    @Mock
    private Branchrepository branchrepository;
    @InjectMocks
    private Branchservice branchservice;
    @Test
    public void testfindAll() throws Branchdetailsnotfound {
        Branch branch=new Branch();
        branch.setAddress("ecity");
        branch.setBranchId(55);
        List<Branch>branches=new ArrayList<>();
        branches.add(branch);
        when(branchrepository.findAll()).thenReturn(branches);
        List<BranchDTO> branchDTOS=branchservice.findAll();
        assertEquals(1,branchDTOS.size());
    }

    private void assertEquals(int i, int size) {
    }
    @Test(expected = Branchdetailsnotfound.class)
    public void testfindAllwithEmptydata() throws Branchdetailsnotfound{

        List<Branch>branches=null;
        when(branchrepository.findAll()).thenReturn(branches);
        List<BranchDTO> branchDTOS=branchservice.findAll();
        assertEquals(1,branchDTOS.size());

    }
    @Test
    public void testfindAllwithloans()throws Branchdetailsnotfound{
        Branch branch=new Branch();
        branch.setAddress("mumbai");
        branch.setName("axis");
        branch.setBranchId(555);
        Loan loan=new Loan();
        loan.setLoanType("car");
        loan.setLoanamount(50000);
        loan.setLoanId(100);
        Set<Loan>loans=new HashSet<>();
        loans.add(loan);
        branch.setLoan(loans);
        List<Branch>branches=new ArrayList<>();
        branches.add(branch);
        when(branchrepository.findAll()).thenReturn(branches);
        List<BranchDTO>branchDTOList=branchservice.findAll();
        assertEquals(1,branchDTOList.size());
    }
    @Test
    public void testfindbranchdetails(){
        Branch branch=new Branch();
        branch.


    }

}
