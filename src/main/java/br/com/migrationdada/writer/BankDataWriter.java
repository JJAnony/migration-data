package br.com.migrationdada.writer;

import br.com.migrationdada.model.BankData;
import br.com.migrationdada.model.Person;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
public class BankDataWriter {

    @Bean
    public JdbcBatchItemWriter bankdatawriter(@Qualifier("appDatasource") DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<BankData>()
                .dataSource(dataSource)
                .sql("INSERT INTO bank_data (id, person_id, agency, account, bank) VALUES (:id,:person_id,:agency,:account,:bank)")
                .beanMapped()
                .build();
    }

}
