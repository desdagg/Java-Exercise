package com.des.crudTest.crudTest.control;

import com.des.crudTest.crudTest.AppView;
import com.des.crudTest.crudTest.model.Address;
import com.des.crudTest.crudTest.model.Person;
import com.des.crudTest.crudTest.repository.AddressRepository;
import com.des.crudTest.crudTest.repository.PersonRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManagerFactory;
import java.text.ParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;

@SpringBootApplication
public class AppControl {
    PersonRepository personRepo;
    AddressRepository addressRepo;



    public AppControl(PersonRepository personRepo, AddressRepository addressRepo){
        this.personRepo = personRepo;
        this.addressRepo = addressRepo;
    }

    public AppControl(){

    }


    public void mainControl(){
        Scanner command = new Scanner(System.in);
        boolean running = true;

        setUp();
        //System.out.println(AppView.menu());

        while(running){
            System.out.println(AppView.menu());
            switch(command.nextLine()){

                case "1":
                    System.out.println("1 was selected");
                    addPerson();
                    break;

                case "2":
                    System.out.println("Edit Person");
                    editPerson();
                    break;

                case "3":
                    System.out.println("Delete Person");
                    deletePerson();
                    break;

                case "4":
                    System.out.println("Add Address");
                    addAddress();
                    break;

                case "5":
                    System.out.println("Edit Address");
                    Person personAddressEdit = selectPerson();
                    if(personAddressEdit != null){
                        Address addressToEdit = selectAddress(personAddressEdit);
                        if(addressToEdit != null) {
                            editAddress(addressToEdit);
                        }
                    }
                    break;

                case "6":
                    System.out.println("Delete Address");
                    Person personAddressDelete = selectPerson();
                    if(personAddressDelete != null){
                        Address addressToDelete = selectAddress(personAddressDelete);
                        if(addressToDelete != null) {
                            deleteAddress(addressToDelete);
                        }
                    }
                    break;


                case "7":
                    System.out.println("Person Count: ");
                    countPersons();
                    break;

                case "8":
                    System.out.println("List Persons");
                    listPersons();
                    break;

                case "exit":
                    System.out.println("Stopping Application");
                    running = false;
                    break;


            }
            //System.out.println(AppView.displayBreak());
            //System.out.println(AppView.menu());
        }

        // put termination here
        System.exit(0);

    }


    private void addPerson(){
        Scanner input = new Scanner(System.in);
        System.out.println("First Name: ");
        String firstName = input.nextLine();
        Person newPerson = new Person(firstName);
        System.out.println("Last Name: ");
        String lastName = input.nextLine();
        newPerson.setLast_name(lastName);
        personRepo.save(newPerson);
    }

    private void listPersons(){
        personRepo.findAll();

        for(Person person : personRepo.findAll()){
            System.out.println(person.getFirst_name() + ", " + person.getLast_name() + ", ID: " + person.getId() + " Address: " + person.getAddresses());

        }
    }

    private void editPerson(){
        Scanner input = new Scanner(System.in);

        Long id = idValidation("person");


        if(id >= 0) {
            try {
                Person person = personRepo.findById(id).get();
                System.out.println("Now editing " + person.getFirst_name() + " " + person.getLast_name());

                //input person details. could probably reduce as mostly duplicate of add person
                System.out.println("First Name: ");
                String firstName = input.nextLine();
                System.out.println("Last Name: ");
                String lastName = input.nextLine();
                person.setFirst_name(firstName);
                person.setLast_name(lastName);
                personRepo.save(person);
            } catch (NoSuchElementException e) {
                System.out.println("ID does not exist");
                return;
            }
        }

    }

    private void editAddress(Address address){
        Scanner input = new Scanner(System.in);

        System.out.println("Street Name: ");
        String streetName = input.nextLine();
        System.out.println("City: ");
        String city = input.nextLine();
        System.out.println("state: ");
        String state = input.nextLine();
        System.out.println("PostalCode: ");
        String postcode = input.nextLine();
        address.setStreet(streetName);
        address.setCity(city);
        address.setState(state);
        address.setPostalCode(postcode);
        addressRepo.save(address);
    }

    private void deletePerson(){
        Long id = idValidation("person");

        if(id >= 0) {
            try {
                deleteAddressByPerson(id);
                Person person = personRepo.findById(id).get();
                System.out.println("Now deleting " + person.getFirst_name() + " " + person.getLast_name());

                personRepo.delete(person);
            } catch (NoSuchElementException e) {
                System.out.println("ID does not exist");
                return;
            }
        }
    }

    private void addAddress(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Person ID for address : ");
        String idString = input.nextLine();
        Long id = 0L;

        try{
            id = Long.parseLong(idString);
        }catch(NumberFormatException e){
            System.out.println("not a valid id");
            return;
        }

        try{
            Person person = personRepo.findById(id).get();
            System.out.println("Adding address for " + person.getFirst_name() + " " + person.getLast_name());

            //input address details.
            Address address = new Address();
            address.setPersonId(id);

            System.out.println("Street Name: ");
            String streetName = input.nextLine();
            System.out.println("City: ");
            String city = input.nextLine();
            System.out.println("state: ");
            String state = input.nextLine();
            System.out.println("PostalCode: ");
            String postcode = input.nextLine();
            address.setStreet(streetName);
            address.setCity(city);
            address.setState(state);
            address.setPostalCode(postcode);
            addressRepo.save(address);
        }catch(NoSuchElementException e){
            System.out.println("This person does not exist");
            return;
        }
    }

    private Person selectPerson(){

        // this try catch is essentially a duplicate and should be its own method
        Long id = idValidation("person");

        if(id >= 0) {
            try {
                Person person = personRepo.findById(id).get();
                System.out.println("Now editing address for " + person.getFirst_name() + " " + person.getLast_name());
                return person;

            } catch (NoSuchElementException e) {
                System.out.println("ID does not exist");
                return null;
            }
        }
        return null;
    }

    private Address selectAddress(Person person){

        for(Address address : addressRepo.findAll()){
            if(address.getPersonId() == person.getId()){
                System.out.println(address);
            }
        }
        System.out.println("Select address: ");
        Long id = idValidation("address");

        if(id >= 0) {
            try {
                return addressRepo.findById(id).get();

            } catch (NoSuchElementException e) {
                System.out.println("ID does not exist");
                return null;
            }
        }

        return null;
    }

    private void deleteAddressByPerson(Long personid){


        for(Address address : addressRepo.findAll()){
            if(address.getPersonId() == personid){
                deleteAddress(address);
            }
        }

    }

    private void countPersons(){
        System.out.print(personRepo.findAll().size());
    }

    private void deleteAddress(Address address){
        addressRepo.delete(address);
    }

    private Long idValidation(String type){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter " + type + " ID : ");
        String idString = input.nextLine();
        Long id = 0L;

        try{
            id = Long.parseLong(idString);
            return id;
        }catch(NumberFormatException e){
            System.out.println("not a valid id");
            return -1L;
        }
    }

    // populate the db a bit
    private void setUp(){
        Person person  = new Person("jim");
        person.setLast_name("pete");
        personRepo.save(person);


        //System.out.println("finding by id   " + personRepo.findById(1L));

        Person personTwo = new Person("bill");
        personTwo.setLast_name("bright");
        personRepo.save(personTwo);


        Address address = new Address();
        address.setPersonId(2L);
        address.setCity("dublin");

        addressRepo.save(address);

        Address addressTwo = new Address();
        addressTwo.setPersonId(2L);
        addressTwo.setCity("Cork");

        addressRepo.save(addressTwo);
        //System.out.println("find by id  " + personRepo.findById(2L));
        personRepo.findAll().forEach(System.out::println);
    }

}
