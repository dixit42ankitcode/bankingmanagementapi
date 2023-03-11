package com.bankingmanagement.service;

import com.bankingmanagement.entity.Bank;
import com.bankingmanagement.entity.Branch;
import com.bankingmanagement.exception.BankDetailsNotFound;
import com.bankingmanagement.model.BankDTO;
import com.bankingmanagement.model.BankRequest;
import com.bankingmanagement.model.BranchDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import com.bankingmanagement.repositoty.Bankrepository;

import java.util.List;
import java.util.Optional;
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
            List<BranchDTO> branchDTOS=null;
            if (!CollectionUtils.isEmpty(branches)) {
                branchDTOS = branches.stream().map(branch -> {
                    BranchDTO branchDTO = new BranchDTO();
                    branchDTO.setName(branch.getName());
                    branchDTO.setAddress(branch.getAddress());
                    return branchDTO;
                }).collect(Collectors.toList());
            }
                bankDTO.setBranchDTOS(branchDTOS);
                return bankDTO;

        }).collect(Collectors.toList());
       return bankDTOList;
    }

    @Override
    public BankDTO findBankdetails(int code) throws BankDetailsNotFound {
         log.info("input to bankerviceimpl.findBankdetails.code:{}",code);
         if(code<=0)
         {
             log.info("bank details not found");
             throw new BankDetailsNotFound("invalid bank code");
         }
        Optional<Bank>bank=bankrepository.findById(code);
         log.info("bank details for code:{} and the details:{}",code,bank.get());
         if(!bank.isPresent())
         {
             log.info("bank details are not found for bank code:{}",code);
             throw new BankDetailsNotFound("bank details are not found");
         }
         Bank bank1=bank.get();
         BankDTO bankDTO=new BankDTO();
         bankDTO.setName(bank1.getName());
         bankDTO.setAddress(bank1.getAddress());
        Set<Branch> branches = bank1.getBranch();
        List<BranchDTO>branchDTOS=null;
        if (!CollectionUtils.isEmpty(branches)) {
            branchDTOS = branches.stream().map(branch -> {
                BranchDTO branchDTO = new BranchDTO();
                branchDTO.setName(branch.getName());
                branchDTO.setAddress(branch.getAddress());
                return branchDTO;
            }).collect(Collectors.toList());
        }
        bankDTO.setBranchDTOS(branchDTOS);
        log.info("end of the bankserviceimpl.findbankdetails");
        return bankDTO;
    }

    @Override
    public BankDTO save(BankRequest bankRequest) throws BankDetailsNotFound{
        log.info("input to bankserviceimpl.save().bankRequest:{}",bankRequest);
        if(bankRequest==null)
        {
            log.info("invalid request");
            throw new BankDetailsNotFound("invalid input");
        }
        Bank bank=new Bank();
        bank.setName(bankRequest.getName());
        bank.setAddress(bankRequest.getAddress());
        bank.setCode(bankRequest.getCode());
        Bank bank1=bankrepository.save(bank);
        BankDTO bankDTO = new BankDTO();
        bankDTO.setName(bank.getName());
        bankDTO.setAddress(bank.getAddress());
        return bankDTO;
    }

    @Override
    public String delete( int bank_code) throws BankDetailsNotFound{
        log.info("input to bankserviceimpl,delete,bankcode:{}",bank_code);
        if (bank_code<=0)
        {
            log.info("invalid bankcode");
            throw new BankDetailsNotFound("invalid bankcode");
        }
        bankrepository.deleteById(bank_code);
        return "bank details has been deleted ";

    }
}





