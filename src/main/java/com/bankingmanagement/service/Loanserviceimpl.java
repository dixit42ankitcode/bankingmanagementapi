package com.bankingmanagement.service;

import com.bankingmanagement.entity.Branch;
import com.bankingmanagement.entity.Customer;
import com.bankingmanagement.entity.Loan;
import com.bankingmanagement.exception.Loandetailsnotfound;
import com.bankingmanagement.model.BranchDTO;
import com.bankingmanagement.model.CustomerDTO;
import com.bankingmanagement.model.LoanDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.bankingmanagement.repositoty.Loanreository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class Loanserviceimpl implements Loanservice{
    @Autowired
    Loanreository loanreository;

    @Override
    public List<LoanDTO> findAll() throws Loandetailsnotfound{
        log.info("inside Loanserviceimpl.findAll()");
        List<Loan>loans=loanreository.findAll();
        log.info("list of loans,Loans:{}",loans);
        if(CollectionUtils.isEmpty(loans))
        {
            log.info("loan details are not found");
            throw new Loandetailsnotfound("loan details not found");
        }
        List<LoanDTO>loanDTOList=loans.stream().map(loan -> {
            LoanDTO loanDTO=new LoanDTO();
            loanDTO.setLoanType(loan.getLoanType());
            loanDTO.setLoanamount(loan.getLoanamount());
            Set<Branch>branches= (Set<Branch>) loan.getBranch();
            List<BranchDTO>branchDTOS=branches.stream().map(branch -> {
                BranchDTO branchDTO=new BranchDTO();
                branchDTO.setAddress(branch.getAddress());
                branchDTO.setName(branch.getName());
                return branchDTO;
            }).collect(Collectors.toList());
            Set<Customer>customers= (Set<Customer>) loan.getCustomer();
            List<CustomerDTO>customerDTOS=customers.stream().map(customer -> {
                CustomerDTO customerDTO=new CustomerDTO();
                customerDTO.setPhoneNo(customer.getPhoneNo());
                customerDTO.setAddress(customer.getAddress());
                return customerDTO;
            }).collect(Collectors.toList());
            loanDTO.setBranchDTOS(branchDTOS);
            loanDTO.setCustomerDTOS(customerDTOS);
            return loanDTO;
        }).collect(Collectors.toList());
        return loanDTOList;

    }


}
