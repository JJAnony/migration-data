package br.com.migrationdada.step;

import br.com.migrationdada.model.Person;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MigrationPersonStep {

    @Autowired
    private StepBuilderFactory builderFactory;

    @Bean
    public Step migrationPerson(@Qualifier("personreader") ItemReader personreader,
                                @Qualifier("personclassifierwriter") ClassifierCompositeItemWriter<Person> personclassifierwriter,
                                @Qualifier("personinvalidwriter") FlatFileItemWriter personinvalidwriter){
        return builderFactory
                .get("migration_person")
                .<Person, Person>chunk(1)
                .reader(personreader)
                .writer(personclassifierwriter)
                .stream(personinvalidwriter)
                .build();
    }

}
