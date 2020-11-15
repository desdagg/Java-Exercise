package com.des.crudTest.crudTest;

import com.des.crudTest.crudTest.model.Person;
import com.des.crudTest.crudTest.repository.PersonRepository;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;


import javax.annotation.Resource;

@SpringBootTest
class CrudTestApplicationTests {

	@Resource
	private PersonRepository personRepository;


	@Test
	void contextLoads() {

		Person person = personRepository.save(new Person("personTest"));
		Long id = person.getId();

		Person foundPerson = personRepository.findById(id).get();
		assertNotNull(foundPerson);
		assertEquals(foundPerson.getFirst_name(), person.getFirst_name());
	}

}
