package com.bankingmanagement.service;

import com.bankingmanagement.entity.Bank;
import com.bankingmanagement.entity.Branch;
import com.bankingmanagement.exception.BankDetailsNotFound;
import com.bankingmanagement.model.BankDTO;
import com.bankingmanagement.repositoty.Bankrepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@RunWith(MockitoJUnitRunner.class)

public class Bankserviceimpltest {
    @Mock
    private Bankrepository bankrepository;
    @InjectMocks
    private Bankserviceimpl bankservice;
    @Test
    public void testfindAll() throws BankDetailsNotFound {
        Bank bank=new Bank();
        bank.setAddress("delhi");
        bank.setName("hdfc");
        bank.setCode(101);
        List<Bank> banks=new ArrayList<>();
        banks.add(bank);
        when(bankrepository.findAll()).thenReturn(banks);
        List<BankDTO> bankDTOList= bankservice.findAll();
        assertEquals(1, bankDTOList.size());
    }

    private void assertEquals(int i, int size) {
    }
    @Test(expected = BankDetailsNotFound.class)
    public void testfindAllwithEmptyData() throws BankDetailsNotFound {

        List<Bank> banks=null;
        when(bankrepository.findAll()).thenReturn(banks);
        List<BankDTO> bankDTOList= bankservice.findAll();
        assertEquals(1, bankDTOList.size());
    }
    @Test
    public void testfindAllwithBranch() throws BankDetailsNotFound {
        Bank bank=new Bank();
        bank.setAddress("delhi");
        bank.setName("hdfc");
        bank.setCode(101);
        Branch branch=new Branch();
        branch.setAddress("lucknowcity");
        branch.setBranchId(101);
        Set<Branch>branches=new HashSet<>();
        branches.add(branch);
        bank.setBranch(branches);
        List<Bank> banks=new ArrayList<>();
        banks.add(bank);
        when(bankrepository.findAll()).thenReturn(banks);
        List<BankDTO> bankDTOList= bankservice.findAll();
        assertEquals(1, bankDTOList.size());
    }
  @Test
   public void testfindBankdetailswithEmptydata() throws BankDetailsNotFound {
        Bank bank=new Bank();
        bank.setName("axis");
        bank.setAddress("delhi");
        bank.setCode(55);
        Set<Bank> banks=new HashSet<>();
        banks.add(bank);
        when(bankrepository.findById(anyInt())).thenReturn(Optional.of(bank));
        BankDTO bankDTO= bankservice.findBankdetails(501);
        assertEquals(1,bankDTO.getCode());
    }
    @Test(expected = BankDetailsNotFound.class)
    public void testfindBankdetails() throws BankDetailsNotFound {

        List<Bank> banks = null;
        when(bankrepository.findById(anyInt())).thenReturn(Optional.empty());
        BankDTO bankDTO = bankservice.findBankdetails(100);
        assertEquals(1,bankDTO.getCode());
    }
   @Test
    public void testfindBankdetailswithbranch() throws BankDetailsNotFound{
        Bank bank=new Bank();
        bank.setCode(56);
        bank.setAddress("lalitpur");
        bank.setName("hdfc");
        Branch branch=new Branch();
        branch.setBranchId(100);
        branch.setName("lko");
        branch.setAddress("kolkata");
        Set<Branch>branches=new HashSet<>();
        branches.add(branch);
        bank.setBranch(branches);
        List<Bank>banks=new ArrayList<>();
        banks.add(bank);
        when(bankrepository.findById(anyInt())).thenReturn(Optional.of(bank));
         BankDTO bankDTO= bankservice.findBankdetails(100);
        assertEquals(1,bankDTO.getCode());
    }
    @Test
    public void testsave(){
        Bank bank=new Bank();

    }

}
