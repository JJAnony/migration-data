package br.com.migrationdada.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MigrationPersonStep {

    @Autowired
    private StepBuilderFactory builderFactory;

    @Bean
    public Step migrationPerson(@Qualifier("personreader") ItemReader personreader, @Qualifier("personwriter") ItemWriter personwriter){
        return builderFactory
                .get("migration_person")
                .chunk(1)
                .reader(personreader)
                .writer(personwriter)
                .build();
    }

}
