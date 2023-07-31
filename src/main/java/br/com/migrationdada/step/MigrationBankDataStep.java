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
public class MigrationBankDataStep {

    @Autowired
    private StepBuilderFactory builderFactory;

    @Bean
    public Step migrationBankData(@Qualifier("bankdatareader") ItemReader bankdatareader, @Qualifier("bankdatawriter") ItemWriter bankdatawriter){
        return builderFactory
                .get("migration_bank_data")
                .chunk(1)
                .reader(bankdatareader)
                .writer(bankdatawriter)
                .build();
    }

}
