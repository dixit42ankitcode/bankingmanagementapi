package com.bankingmanagement.service;

import com.bankingmanagement.entity.Branch;
import com.bankingmanagement.entity.Customer;
import com.bankingmanagement.entity.Loan;
import com.bankingmanagement.exception.Loandetailsnotfound;
import com.bankingmanagement.model.BranchDTO;
import com.bankingmanagement.model.CustomerDTO;
import com.bankingmanagement.model.LoanDTO;
import com.bankingmanagement.model.LoanRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.bankingmanagement.repositoty.Loanreository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class Loanserviceimpl implements Loanservice {
    @Autowired
    Loanreository loanreository;

    @Override
    public List<LoanDTO> findAll() throws Loandetailsnotfound {
        log.info("inside Loanserviceimpl.findAll()");
        List<Loan> loans = loanreository.findAll();
        log.info("list of loans,Loans:{}", loans);
        if (CollectionUtils.isEmpty(loans)) {
            log.info("loan details are not found");
            throw new Loandetailsnotfound("loan details not found");
        }
        List<LoanDTO> loanDTOList = loans.stream().map(loan -> {
            LoanDTO loanDTO = new LoanDTO();
            loanDTO.setLoanType(loan.getLoanType());
            loanDTO.setLoanamount(loan.getLoanamount());
                BranchDTO branchDTO = new BranchDTO();
                branchDTO.setAddress(branchDTO.getAddress());
                branchDTO.setName(branchDTO.getName());
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setPhoneNo(customerDTO.getPhoneNo());
                customerDTO.setAddress(customerDTO.getAddress());
                return loanDTO;
        }).collect(Collectors.toList());
        return loanDTOList;
    }
    @Cacheable("loan_id")
    @Override
    public LoanDTO findloandetails(int loanId) throws Loandetailsnotfound {
        log.info("input to loanservcieimpl,findloandetails,loanId:{}", loanId);
        if (loanId <= 0) {
            log.info("invalid loanid");
            throw new Loandetailsnotfound("invalid loanid");
        }
        Optional<Loan> loan = loanreository.findById(loanId);
        if (loan==null) {
            log.info("loan details are not found");
            throw new Loandetailsnotfound("loan details are not found");
        }
        log.info("loan details for loanid:{},details:{}", loanId, loan.get());

        Loan loan1 = loan.get();
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setLoanType(loan1.getLoanType());
        loanDTO.setLoanamount(loan1.getLoanamount());
        Branch branch = loan1.getBranch();
        BranchDTO branchDTO = new BranchDTO();
        if(branch!=null) {
            Branch branch1 = new Branch();
            branchDTO.setAddress(branch.getAddress());
            branchDTO.setName(branch.getName());
            loanDTO.setBranchDTOS(branchDTO);
        }
        Customer customer=new Customer();
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.setPhoneNo(customer.getPhoneNo());
                customerDTO.setAddress(customer.getAddress());
                loanDTO.setCustomerDTOS(customerDTO);
            log.info("end of loanserviceipl.findloandetails");
            return loanDTO;
    }
    @CacheEvict(value = "loan_id",allEntries = true)
    @Override
    public void clearcache() {

    }


    @Override
    public LoanDTO save(LoanRequest loanRequest) throws Loandetailsnotfound {
        log.info("input to loanserviceimpl,save,loanrequest:{}", loanRequest);
        if (loanRequest == null) {
            log.info("invalid loan request");
            throw new Loandetailsnotfound("invalid loan request");
        }
        Loan loan = new Loan();
        loan.setLoanType(loan.getLoanType());
        loan.setLoanamount(loan.getLoanamount());
        Loan loan1 = loanreository.save(loan);
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setLoanType(loan.getLoanType());
        loanDTO.setLoanamount(loan.getLoanamount());
        return loanDTO;
    }

    @Override
    public String delete(int loanId) throws Loandetailsnotfound {
        log.info(" input to loanserviceimpl,delete,loanId");
        if (loanId <= 0) {
            log.info("invalid loanId");
            throw new Loandetailsnotfound("invalid loanId");
        }
        loanreository.deleteById(loanId);
        return "loan details are deleted";
    }
}


