package com.bankingmanagement.service;

import com.bankingmanagement.entity.Account;
import com.bankingmanagement.entity.Branch;
import com.bankingmanagement.entity.Loan;
import com.bankingmanagement.exception.Branchdetailsnotfound;
import com.bankingmanagement.model.AccountDTO;
import com.bankingmanagement.model.BranchDTO;
import com.bankingmanagement.model.BranchRequest;
import com.bankingmanagement.model.LoanDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import com.bankingmanagement.repositoty.Branchrepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
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

    @Override
    public BranchDTO findbranchdetails(int branchid) throws Branchdetailsnotfound {
        log.info("input to branchserviceimpl.findbranchdetails.branchid:{}",branchid);
        if(branchid<=0)
        {
            log.info("branch details not found");
            throw new Branchdetailsnotfound("invalid branch code");

        }
        Optional<Branch> branch=branchrepository.findById(branchid);
        log.info("branch details for branchid:{},details:{}",branchid,branch.get());
        if(!branch.isPresent())
        {
            log.info("branch details not found for branch id:{}",branchid);
            throw new Branchdetailsnotfound("branch details not found");
        }
        Branch branch1=branch.get();
        BranchDTO branchDTO=new BranchDTO();
        branchDTO.setName(branch1.getName());
        branchDTO.setAddress(branch1.getAddress());
        Set<Loan> loans =branch1.getLoan();
        List<LoanDTO> loanDTOS=loans.stream().map(loan -> {
            LoanDTO loanDTO=new LoanDTO();
            loanDTO.setLoanType(loan.getLoanType());
            loanDTO.setLoanamount(loan.getLoanamount());
            return loanDTO;
        }).collect(Collectors.toList());
        branchDTO.setLoandtos(loanDTOS);
        log.info("end of branchserviceimpl.findbranchdetails");
        return branchDTO;

    }

    @Override
    public BranchDTO save(BranchRequest branchRequest) throws Branchdetailsnotfound {
        log.info("input to branchserviceimpl.save().branchRequest:{}",branchRequest);
        if(branchRequest==null)
        {
            log.info("invalid request");
            throw new Branchdetailsnotfound("invalid request");
        }
        Branch branch=new Branch();
        branch.setName(branchRequest.getName());
        branch.setAddress(branchRequest.getAddress());
        branch.setBranchId(branchRequest.getBranchId());
        Branch branch1=branchrepository.save(branch);
        BranchDTO branchDTO=new BranchDTO();
        branchDTO.setName(branch.getName());
        branchDTO.setAddress(branch.getAddress());
        return branchDTO;
    }

    @Override
    public String delete(int branchId) throws Branchdetailsnotfound {
        log.info("input to branchserviceimpl,delete,branchid",branchId);
        if(branchId<=0)
        {
            log.info("invalid branchId");
            throw new Branchdetailsnotfound("invalid branchId");
        }
        branchrepository.deleteById(branchId);
        return "branch details deleted";
    }
}
