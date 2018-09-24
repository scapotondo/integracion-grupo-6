package com.integracion.grupo6.batch;

import com.integracion.grupo6.batch.processor.OrderIntegrationProcessor;
import com.integracion.grupo6.batch.reader.OrderIntegrationReader;
import com.integracion.grupo6.batch.writer.OrderIntegrationWriter;
import com.integracion.grupo6.domain.Order;
import com.integracion.grupo6.dto.OrderIntegrationDTO;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

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

    @Autowired
    private SimpleJobLauncher jobLauncher;

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

    @Scheduled(cron = "0 * * * * *")
    public void runScheduledJob() throws Exception {
        System.out.println(" Job Started at :" + new Date());
        JobParameters param = new JobParametersBuilder().addString("JobID",
                String.valueOf(System.currentTimeMillis())).toJobParameters();
        JobExecution execution = jobLauncher.run(csvFileToDatabaseJob(), param);
        System.out.println("Job finished with status :" + execution.getStatus());
    }


}
