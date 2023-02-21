package com.bankingmanagement.service;

import com.bankingmanagement.entity.Bank;
import com.bankingmanagement.entity.Branch;
import com.bankingmanagement.exception.BankDetailsNotFound;
import com.bankingmanagement.model.BankDTO;
import com.bankingmanagement.model.BranchDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.bankingmanagement.repositoty.Bankrepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class Bankserviceimpl implements Bankservice{
    @Autowired
    Bankrepository bankrepository;
    @Override
    public List<BankDTO> findAll()throws BankDetailsNotFound {
        log.info("inside Bankserviceimpl.findall()");
        List<Bank> banks=bankrepository.findAll();
        log.info("list of Banks,banks:{}",banks);
       if(CollectionUtils.isEmpty(banks))
        {
            log.info("bank details not found");
            throw new BankDetailsNotFound("bank details not found");
        }
        List<BankDTO> bankDTOList= banks.stream().map(bank -> {
            BankDTO bankDTO = new BankDTO();
            bankDTO.setName(bank.getName());
            bankDTO.setAddress(bank.getAddress());
            Set<Branch> branches = bank.getBranch();
            List<BranchDTO> branchDTOS = branches.stream().map(branch -> {
                BranchDTO branchDTO = new BranchDTO();
                branchDTO.setName(branch.getName());
                branchDTO.setAddress(branch.getAddress());
                return branchDTO;
            }).collect(Collectors.toList());
            bankDTO.setBranchDTOS(branchDTOS);
            return bankDTO;
        }).collect(Collectors.toList());
       return bankDTOList;
    }
}
