package com.des.crudTest.crudTest.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) // strategy might be redundant now
    private Long id;
    @NonNull
    private String first_name;
    private String last_name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="personId")
    private List<Address> addresses = new ArrayList<Address>(); // using type in ArrayList i think is redundant
}