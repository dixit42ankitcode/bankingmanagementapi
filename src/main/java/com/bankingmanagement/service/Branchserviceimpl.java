package com.bankingmanagement.service;

import com.bankingmanagement.entity.Account;
import com.bankingmanagement.entity.Branch;
import com.bankingmanagement.entity.Loan;
import com.bankingmanagement.exception.Branchdetailsnotfound;
import com.bankingmanagement.model.AccountDTO;
import com.bankingmanagement.model.BranchDTO;
import com.bankingmanagement.model.LoanDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.bankingmanagement.repositoty.Branchrepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class Branchserviceimpl implements  Branchservice{
    @Autowired
    Branchrepository branchrepository;

    @Override
    public List<BranchDTO> findAll() throws Branchdetailsnotfound {
        log.info("inside Branchserviceimpl.findAll()");
        List<Branch> branches= branchrepository.findAll();
        log.info("list of branch,branch:{}",branches);
        if(CollectionUtils.isEmpty(branches))
        {
            log.info("branch details not found");
            throw new Branchdetailsnotfound("branchdetailsnotfound");
        }
        List<BranchDTO> branchDTOList=branches.stream().map(branch -> {
            BranchDTO branchDTO=new BranchDTO();
            branchDTO.setName(branch.getName());
            branchDTO.setAddress(branch.getAddress());
            Set<Loan> loans =branch.getLoan();
            List<LoanDTO> loanDTOS=loans.stream().map(loan -> {
                LoanDTO loanDTO=new LoanDTO();
                loanDTO.setLoanType(loan.getLoanType());
                loanDTO.setLoanamount(loan.getLoanamount());
                return loanDTO;
            }).collect(Collectors.toList());
            branchDTO.setLoandtos(loanDTOS);
            return branchDTO;
        }).collect(Collectors.toList());
        return branchDTOList;
    }
}
