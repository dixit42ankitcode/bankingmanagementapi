package com.bankingmanagement.repository;

import com.bankingmanagement.entity.Branch;
import com.bankingmanagement.repositoty.Branchrepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest

public class TestBranchReository {
    @Autowired
    Branchrepository branchrepository;
    public void savebranch(){
        Branch branch=new Branch();
        branch.setAddress("delhi");
        branch.setName("hdfc");
        branchrepository.save(branch);
    }

    @Test
    public void FindBybranchname(){
        Branch branch=new Branch();
        savebranch();
        Optional<Branch>branchOptional=branchrepository.findByaddress("delhi");
        assertEquals("delhi",branchOptional.get().getAddress());

    }

}
