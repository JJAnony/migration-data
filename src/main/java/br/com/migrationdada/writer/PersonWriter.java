package br.com.migrationdada.writer;

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
public class PersonWriter {

    @Bean
    public JdbcBatchItemWriter personwriter(@Qualifier("appDatasource") DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Person>()
                .dataSource(dataSource)
                .sql("INSERT INTO person (id, name, email, date_of_birth, age) VALUES (?,?,?,?,?)")
                .itemPreparedStatementSetter(itemPreparedStatementSetter())
                .build();
    }

    private ItemPreparedStatementSetter<Person> itemPreparedStatementSetter(){
        return new ItemPreparedStatementSetter<Person>() {
            @Override
            public void setValues(Person person, PreparedStatement ps) throws SQLException {
                ps.setLong(1, person.getId());
                ps.setString(2, person.getName());
                ps.setString(3, person.getEmail());
                ps.setTimestamp(4, Timestamp.valueOf(person.getDateOfBirth()));
                ps.setInt(5, person.getAge());
            }
        };
    }


}
