package com.bankingmanagement.service;


import com.bankingmanagement.exception.Customerdetailsnotfound;
import com.bankingmanagement.model.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface Customerservice {
    public List<CustomerDTO> findAll() throws Customerdetailsnotfound;
}
