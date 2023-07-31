package br.com.migrationdada.writer;

import br.com.migrationdada.model.Person;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
public class PersonInvalidWriter {

    @Bean
    public FlatFileItemWriter<Person> personinvalidwriter() {
        return new FlatFileItemWriterBuilder<Person>()
                .name("personinvalidwriter")
                .resource(new FileSystemResource("files/person_invalid.csv"))
                .delimited()
                .names("id")
                .build();
    }

}
