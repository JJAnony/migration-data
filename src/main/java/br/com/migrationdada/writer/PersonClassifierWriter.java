package br.com.migrationdada.writer;

import br.com.migrationdada.model.Person;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PersonClassifierWriter {

    @Bean
    public ClassifierCompositeItemWriter<Person> personclassifierwriter(@Qualifier("personwriter") JdbcBatchItemWriter personwriter ,
                                                                        @Qualifier("personinvalidwriter") FlatFileItemWriter personinvalidwriter){
        return new ClassifierCompositeItemWriterBuilder<Person>()
                .classifier(classifier(personwriter, personinvalidwriter))
                .build();
    }

    private Classifier<Person, ItemWriter<? super Person>> classifier(JdbcBatchItemWriter<Person> personwriter , FlatFileItemWriter personinvalidwriter){
        return new Classifier<Person, ItemWriter<? super Person>>() {
            @Override
            public ItemWriter<? super Person> classify(Person person) {
                if(person.isValid()){
                    return personwriter;
                }
                return personinvalidwriter;
            }
        };
    }

}
