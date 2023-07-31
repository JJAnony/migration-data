package br.com.migrationdada.reader;

import br.com.migrationdada.model.Person;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class PersonReader {

    @Bean
    public FlatFileItemReader<Person> personreader(){
        return new FlatFileItemReaderBuilder<Person>()
                .name("person_reader")
                .resource(new FileSystemResource("files/person.csv"))
                .delimited()
                .names("name", "email", "dateOfBirth", "age", "id")
                .addComment("--")
                .fieldSetMapper(fieldSetMapper())
                .build();
    }

    private FieldSetMapper<Person> fieldSetMapper(){
        return  new FieldSetMapper<Person>() {
            @Override
            public Person mapFieldSet(FieldSet fieldSet) throws BindException {
                Person person = new Person();
                person.setId(fieldSet.readLong("id"));
                person.setName(fieldSet.readString("name"));
                person.setEmail(fieldSet.readString("email"));
                DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                person.setDateOfBirth(LocalDateTime.parse(fieldSet.readString("dateOfBirth"), parser));
                person.setAge(fieldSet.readInt("age"));
                return person;
            }
        };
    }

}
