package com.des.crudTest.crudTest.repository;

import com.des.crudTest.crudTest.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Long>{

    Address save(Address address);


}