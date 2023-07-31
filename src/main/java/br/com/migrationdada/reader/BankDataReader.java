package br.com.migrationdada.reader;

import br.com.migrationdada.model.BankData;
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
public class BankDataReader {

    @Bean
    public FlatFileItemReader<BankData> bankdatareader(){
        return new FlatFileItemReaderBuilder<BankData>()
                .name("bank_data_reader")
                .resource(new FileSystemResource("files/bank_data.csv"))
                .delimited()
                .names("person_id", "agency", "account", "bank", "id")
                .addComment("--")
                .targetType(BankData.class)
                .build();
    }

}
