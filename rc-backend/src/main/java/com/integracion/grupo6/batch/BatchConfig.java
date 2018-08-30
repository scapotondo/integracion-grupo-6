package com.integracion.grupo6.batch;

import com.integracion.grupo6.batch.processor.OrderIntegrationProcessor;
import com.integracion.grupo6.batch.reader.OrderIntegrationReader;
import com.integracion.grupo6.batch.writer.OrderIntegrationWriter;
import com.integracion.grupo6.domain.Order;
import com.integracion.grupo6.dto.OrderIntegrationDTO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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
    private OrderIntegrationReader reader;

    @Autowired
    private OrderIntegrationProcessor processor;

    @Autowired
    private OrderIntegrationWriter writer;

    @Bean
    Step csvFileToDatabaseStep() {
        return stepBuilderFactory.get("order-csv-to-database-step")
                .<OrderIntegrationDTO, Order>chunk(1)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    Job csvFileToDatabaseJob() {
        return jobBuilderFactory.get("order-csv-to-database-job")
                .flow(csvFileToDatabaseStep())
                .end()
                .build();
    }


}
