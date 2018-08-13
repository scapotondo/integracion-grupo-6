package com.integracion.grupo6.batch;

import com.integracion.grupo6.batch.processor.EntidadCSVProcessor;
import com.integracion.grupo6.batch.reader.EntidadCSVReader;
import com.integracion.grupo6.batch.writer.EntidadCSVWriter;
import com.integracion.grupo6.domain.Entidad;
import com.integracion.grupo6.dto.EntidadDTO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private EntidadCSVReader entidadCSVReader;

    @Autowired
    private EntidadCSVProcessor entidadCSVProcessor;

    @Autowired
    private EntidadCSVWriter entidadCSVWriter;

    @Bean
    Step csvFileToDatabaseStep() {
        return stepBuilderFactory.get("entidad-csv-to-database-step")
                .<EntidadDTO, Entidad>chunk(1)
                .reader(entidadCSVReader)
                .processor(entidadCSVProcessor)
                .writer(entidadCSVWriter)
                .build();
    }

    @Bean
    Job csvFileToDatabaseJob() {
        return jobBuilderFactory.get("entidad-csv-to-database-job")
                .flow(csvFileToDatabaseStep())
                .end()
                .build();
    }


}
