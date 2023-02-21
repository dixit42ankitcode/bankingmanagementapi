package com.bankingmanagement.controller;

import com.bankingmanagement.exception.Branchdetailsnotfound;
import com.bankingmanagement.model.BranchDTO;
import com.bankingmanagement.service.Branchservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
