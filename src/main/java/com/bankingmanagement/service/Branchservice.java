package com.bankingmanagement.service;

import com.bankingmanagement.exception.Branchdetailsnotfound;
import com.bankingmanagement.model.BranchDTO;
import com.bankingmanagement.model.BranchRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface Branchservice {
    List<BranchDTO> findAll() throws Branchdetailsnotfound;
    BranchDTO findbranchdetails(int branchid) throws Branchdetailsnotfound;
    BranchDTO save(BranchRequest branchRequest)throws Branchdetailsnotfound;
    String delete( int branchId) throws Branchdetailsnotfound;

}
