package com.bankingmanagement.service;


import com.bankingmanagement.exception.Branchdetailsnotfound;
import com.bankingmanagement.exception.Customerdetailsnotfound;
import com.bankingmanagement.model.CustomerDTO;
import com.bankingmanagement.model.CustomerRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface Customerservice {
    public List<CustomerDTO> findAll() throws Customerdetailsnotfound;
    CustomerDTO findcustomerdetails(int custid)throws Customerdetailsnotfound;
    CustomerDTO save(CustomerRequest customerRequest)throws Customerdetailsnotfound;
}
