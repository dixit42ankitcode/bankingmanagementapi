package com.bankingmanagement.service;

import com.bankingmanagement.exception.Branchdetailsnotfound;
import com.bankingmanagement.model.BranchDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface Branchservice {
    List<BranchDTO> findAll() throws Branchdetailsnotfound;

}
