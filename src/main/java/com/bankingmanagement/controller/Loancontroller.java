package com.bankingmanagement.controller;

import com.bankingmanagement.exception.Loandetailsnotfound;
import com.bankingmanagement.model.LoanDTO;
import com.bankingmanagement.model.LoanRequest;
import com.bankingmanagement.service.Loanservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/loans")
public class Loancontroller {
    @Autowired
    Loanservice loanservice;

    @GetMapping
    public ResponseEntity<List<LoanDTO>> getloandetails() {
        log.info("inside Loancontroller.getloandetails() ");
        List<LoanDTO> loanDTOS = null;
        try {
            loanDTOS = loanservice.findAll();
            log.info("loan details not found");
            if (CollectionUtils.isEmpty(loanDTOS)) {
                log.info("loan details not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            log.error("exception while finding loan details,exception");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<LoanDTO>>(loanDTOS, HttpStatus.OK);
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<LoanDTO> getloanbyloanId(@PathVariable("loanId") int loanId) throws Loandetailsnotfound {
        log.info("input to controller,getloanbyloanId,loanId:{}", loanId);
        if (loanId <= 0) {
            log.info("invalid loan request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        LoanDTO loanDTO = null;

            loanDTO = loanservice.findloandetails(loanId);
            if (loanDTO == null) {
                log.info("loan details not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<LoanDTO>(loanDTO, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<LoanDTO> save(@RequestBody LoanRequest loanRequest) {
        log.info("inside controller,save,loanrequest");
        if (loanRequest == null) {
            log.info("loan request is invalid");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        LoanDTO loanDTO = null;
        try {
            loanDTO = loanservice.save(loanRequest);
            log.info("response,loanDTO:}", loanDTO);
        } catch (Exception exception) {
            log.info("exception while getting loan details");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<LoanDTO>(loanDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<LoanDTO> update(@RequestBody LoanRequest loanRequest) {
        log.info("inside controller,update,loanrequest");
        if (loanRequest == null) {
            log.info("loan request is invalid");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        LoanDTO loanDTO = null;
        try {
            loanDTO = loanservice.save(loanRequest);
            log.info("response,loanDTO:}", loanDTO);
        } catch (Exception exception) {
            log.info("exception while getting loan details");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<LoanDTO>(loanDTO, HttpStatus.OK);

    }
    @DeleteMapping
    public ResponseEntity<String>delete(@RequestParam("loanId")int loanId){
        log.info("input to loancontroller,delete,loanId",loanId);
        String response=null;
        if (loanId<=0)
        {
            log.info("invalid loanId");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            response= loanservice.delete(loanId);
            log.info("delete loan details,response",response);

        }catch (Exception exception)
        {
            log.info("exception while getting loan details");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(response,HttpStatus.OK);
    }
}


