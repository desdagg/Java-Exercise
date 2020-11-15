package com.des.crudTest.crudTest;

import com.des.crudTest.crudTest.control.AppControl;
import com.des.crudTest.crudTest.repository.AddressRepository;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.des.crudTest.crudTest.repository.PersonRepository;

import org.slf4j.Logger;

@SpringBootApplication
@EntityScan("com.des.crudTest.crudTest.model")
@EnableJpaRepositories("com.des.crudTest.crudTest.repository")
public class CrudTestApplication {

	//private Logger log = LoggerFactory.getLogger(this.getClass());
	static PersonRepository pr;
	static AddressRepository ar;

	public static void main(String[] args) {
		SpringApplication.run(CrudTestApplication.class, args);

		AppControl control = new AppControl(pr, ar);
		control.mainControl();

	}

	@Bean
	CommandLineRunner start(PersonRepository personRepository, AddressRepository addressRepository){
		return args -> {
			pr = personRepository;
			ar = addressRepository;
		};
	}



}


