package com.des.crudTest.crudTest.repository;

import java.util.List;

import com.des.crudTest.crudTest.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Long>{

    Person save(Person person);
    List<Person> findAll();
    //Person findById();

}