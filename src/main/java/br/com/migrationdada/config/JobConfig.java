package br.com.migrationdada.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobConfig {

    @Autowired
    private JobBuilderFactory builderFactory;


    @Bean
    public Job migrationDataJob(@Qualifier("migrationPerson") Step migrationPerson, @Qualifier("migrationBankData") Step migrationBankData){
        return builderFactory
                .get("migration_data_job")
                .start(migrationPerson)
                .next(migrationBankData)
                .incrementer(new RunIdIncrementer())
                .build();

    }

}
