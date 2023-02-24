package com.bankingmanagement.controller;

import com.bankingmanagement.exception.Branchdetailsnotfound;
import com.bankingmanagement.model.BankRequest;
import com.bankingmanagement.model.BranchDTO;
import com.bankingmanagement.model.BranchRequest;
import com.bankingmanagement.service.Branchservice;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/branches")
public class Branchcontroller{
    @Autowired
    Branchservice branchservice;
    @GetMapping
    public ResponseEntity<List<BranchDTO>> getbranchdetails() {
        log.info("inside Branchcontroller.getbranchdetails()");
        List<BranchDTO> branchDTOS=null;
        try{
            branchDTOS =branchservice.findAll();
            log.info("branch details not found");
            if(CollectionUtils.isEmpty(branchDTOS))
            {
                log.info("branch details not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        }
        catch (Exception exception)
        {
            log.error("exception while finding bank details,exception");
            return  new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<List<BranchDTO>>(branchDTOS,HttpStatus.OK);
    }


    @GetMapping("/{branch_id}")
    public ResponseEntity<BranchDTO> getbranchbyid(@PathVariable("branch_id") int branchid){
        log.info("inside branchcontroller.getbranchbyuid.branchid:{}",branchid);
        if(branchid<=0)
        {
            log.info("invalid branchid");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BranchDTO branchdto=null;
        try {
            branchdto=branchservice.findbranchdetails(branchid);
            if(branchdto==null)
            {
                log.info("branch details not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }catch (Exception exception)
            {
                log.info("branch details not found ");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        return new ResponseEntity<BranchDTO>(branchdto,HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<BranchDTO>save(@RequestBody BranchRequest branchRequest){
        log.info("inside controller.save().branchrequest:{}",branchRequest);
        if(branchRequest==null)
        {
            log.info("invalid request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BranchDTO branchDTO=null;
        try {
            branchDTO=branchservice.save(branchRequest);
            log.info("response,branchDTO:{}",branchDTO);


        }catch (Exception exception)
        {
            log.info("branch details not found ");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<BranchDTO>(branchDTO,HttpStatus.OK);


    }
}



