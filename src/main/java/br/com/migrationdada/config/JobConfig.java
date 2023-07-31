package br.com.migrationdada.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@EnableBatchProcessing
public class JobConfig {

    @Autowired
    private JobBuilderFactory builderFactory;


    @Bean
    public Job migrationDataJob(@Qualifier("migrationPerson") Step migrationPerson, @Qualifier("migrationBankData") Step migrationBankData){
        return builderFactory
                .get("migration_data_job")
                .start(flowParallel(migrationPerson, migrationBankData))
                .end()
                .incrementer(new RunIdIncrementer())
                .build();

    }

    private Flow flowParallel(Step migrationPerson, Step migrationBankData){
        Flow flowMigrationPerson = new FlowBuilder<Flow>("migrationPerson")
                .start(migrationPerson)
                .build();

        Flow flow = new FlowBuilder<Flow>("migration")
                .start(migrationBankData)
                .split(new SimpleAsyncTaskExecutor())
                .add(flowMigrationPerson)
                .build();

        return flow;
    }

}
