package br.com.migrationdada.model;


import lombok.Data;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDateTime;

@Data
public class Person {

    private Long id;

    private String name;

    private String email;

    private LocalDateTime dateOfBirth;

    private int age;

    public boolean isValid(){
        return !Strings.isBlank(name) && !Strings.isBlank(email) && dateOfBirth != null;
    }


}
