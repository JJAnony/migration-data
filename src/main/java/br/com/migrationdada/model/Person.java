package br.com.migrationdada.model;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Person {

    private Long id;

    private String name;

    private String email;

    private LocalDateTime dateOfBirth;

    private int age;



}
