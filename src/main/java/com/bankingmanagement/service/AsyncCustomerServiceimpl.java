package com.bankingmanagement.service;

import com.bankingmanagement.entity.Customer;
import com.bankingmanagement.exception.Customerdetailsnotfound;
import com.bankingmanagement.model.CustomerDTO;
import com.bankingmanagement.repositoty.Customerrepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AsyncCustomerServiceimpl implements AsyncCustomerService {
    @Autowired
    Customerrepository customerrepository;

    @Cacheable("cust_id")
    @Async("asyncbean")
    @Override
    public CompletableFuture<CustomerDTO> findbycustId(int cust_id) throws Customerdetailsnotfound, InterruptedException {
        log.info("inside customrerserviceimpl,findbycustId,cust_id", cust_id);
        Thread.sleep(1000l);
        CustomerDTO customerDTO = null;
        if (cust_id <= 0) {
            log.info("invalid cust_id", cust_id);
            throw new Customerdetailsnotfound("invalid cust_id");
        }
        Optional<Customer> customer = customerrepository.findById(71);
        if (customer.isPresent()) {
            log.info("customer details for cust_id:{} and value:{}", cust_id, customer.get());
            customerDTO = new CustomerDTO();
            customerDTO.setPhoneNo(customer.get().getPhoneNo());
            customerDTO.setCustomerId(customer.get().getCustomerId());
            customerDTO.setAddress(customer.get().getAddress());
            return CompletableFuture.completedFuture(customerDTO);
        }

        return null;
    }
    @Cacheable("name")
    @Async("asyncbean")
    @Override
    public CompletableFuture<CustomerDTO> findbycustName(String name) throws Customerdetailsnotfound,InterruptedException{
        log.info("inside asynccustomerserviceimpl,findbycustName,name",name);
        Thread.sleep(1000l);
        CustomerDTO customerDTO=null;
        if (name==null){
            log.info("invalid name",name);
            throw new Customerdetailsnotfound("invalid name");
        }
        Optional<Customer>customer= Customerrepository.findBycustName();
        if(customer.isPresent()){
            log.info("customer details for custname:{} and value:{}",name,customer.get());
            customerDTO = new CustomerDTO();
            customerDTO.setPhoneNo(customer.get().getPhoneNo());
            customerDTO.setCustomerId(customer.get().getCustomerId());
            customerDTO.setAddress(customer.get().getAddress());
            return CompletableFuture.completedFuture(customerDTO);
        }
        return null;
    }

    @CacheEvict(value = "cust_id",allEntries = true)
    @Override
    public void clearcache() {

    }
    @CacheEvict(value = "name",allEntries = true)
    @Override
    public void clearcache1() {

    }


}

