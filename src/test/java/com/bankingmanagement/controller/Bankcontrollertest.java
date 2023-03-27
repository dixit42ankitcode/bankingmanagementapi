package com.bankingmanagement.controller;

import com.bankingmanagement.exception.BankDetailsNotFound;
import com.bankingmanagement.model.BankDTO;
import com.bankingmanagement.model.BranchDTO;
import com.bankingmanagement.service.Bankservice;
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
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.org.hamcrest.Matchers;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc

public class Bankcontrollertest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    Bankservice bankservice;
    @Test
    public void testgetbank() throws Exception {
        List<BankDTO> bankDTOList=new ArrayList<>();
        BankDTO bankDTO=new BankDTO();
        bankDTO.setAddress("lucknow");
        bankDTO.setName("sbi");
        bankDTOList.add(bankDTO);
        when(bankservice.findAll()).thenReturn(bankDTOList);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/api/v1/banks").contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }
    @Test
    public void testgetbankwithException() throws Exception {

        when(bankservice.findAll()).thenThrow(new NullPointerException());
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/api/v1/banks").contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isInternalServerError());
    }
    @Test
    public void testgetbankwithemptydata() throws Exception {
        List<BankDTO> bankDTOList=null;
        when(bankservice.findAll()).thenReturn(bankDTOList);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/api/v1/banks").contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
    }
    @Test
    public void testgetbankwithcode() throws Exception {
        BankDTO bankDTO=new BankDTO();
        bankDTO.setName("hdfc");
        bankDTO.setAddress("delhi");
        List<BranchDTO>branchDTOS=new ArrayList<>();
        BranchDTO branchDTO=new BranchDTO();
        branchDTO.setName("tedi pulia");
        branchDTO.setAddress("lucknow");
        branchDTOS.add(branchDTO);
        bankDTO.setBranchDTOS(branchDTOS);
        when(bankservice.save(any())).thenReturn(bankDTO);
        RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/api/v1/banks/11").content(asJsonString(bankDTO)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isInternalServerError());
    }
    @Test
    public void testsavewithemptydata() throws Exception {
        BankDTO bankDTO=null;
        when(bankservice.save(any())).thenReturn(bankDTO);
        RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/api/v1/banks/11").content(asJsonString(bankDTO)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isInternalServerError());
    }
    @Test
    public void testsave() throws Exception {
      BankDTO bankDTO=new BankDTO();
      bankDTO.setName("sbi");
      bankDTO.setAddress("mumbai");
      when(bankservice.save(any())).thenReturn(bankDTO);
      RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/api/v1/banks").content(asJsonString(bankDTO)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
      mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }
    @Test
    public void testsavewithexceptrion() throws Exception {
        when(bankservice.save(any())).thenThrow(new NullPointerException());
        RequestBuilder requestBuilder=MockMvcRequestBuilders.post("/api/v1/banks").contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isInternalServerError());
    }
s


    public String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
