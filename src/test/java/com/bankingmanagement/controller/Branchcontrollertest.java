package com.bankingmanagement.controller;

import com.bankingmanagement.exception.Branchdetailsnotfound;
import com.bankingmanagement.model.BranchDTO;
import com.bankingmanagement.model.LoanDTO;
import com.bankingmanagement.service.Branchservice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.org.hamcrest.Matchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class Branchcontrollertest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    Branchservice branchservice;
    @Test
    public void testgetbranch() throws Exception {
        List<BranchDTO>branchDTOS=new ArrayList<>();
        BranchDTO branchDTO=new BranchDTO();
        branchDTO.setName("behta saboli");
        branchDTO.setAddress("lucknow");
        List<LoanDTO>loanDTOS=new ArrayList<>();
        LoanDTO loanDTO=new LoanDTO();
        loanDTO.setLoanType("car");
        loanDTO.setLoanamount(200000);
        loanDTOS.add(loanDTO);
        branchDTO.setLoandtos(loanDTOS);
        branchDTOS.add(branchDTO);
        when(branchservice.findAll()).thenReturn(branchDTOS);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("api/v1/branches").contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect((ResultMatcher) jsonPath("$", Matchers.hasSize(1))).andDo(print());


    }

}
