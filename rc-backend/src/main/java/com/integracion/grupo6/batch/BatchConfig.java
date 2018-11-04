package com.integracion.grupo6.batch;

import com.integracion.grupo6.batch.processor.OrderIntegrationProcessor;
import com.integracion.grupo6.batch.reader.OrderIntegrationReader;
import com.integracion.grupo6.batch.utils.FileUtils;
import com.integracion.grupo6.batch.writer.OrderIntegrationWriter;
import com.integracion.grupo6.domain.Order;
import com.integracion.grupo6.dto.OrderIntegrationDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.File;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    protected final Log logger = LogFactory.getLog(this.getClass());


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

    @Autowired
    private FileUtils fileUtils;

    @Value("${ftpserver.user.reclamos.homedirectory}")
    private String homeDir;

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

    @Bean
    public MapJobRepositoryFactoryBean mapJobRepositoryFactoryBean(PlatformTransactionManager resourcelessTransactionManager) throws Exception {
        MapJobRepositoryFactoryBean factoryBean = new MapJobRepositoryFactoryBean(resourcelessTransactionManager);
        factoryBean.afterPropertiesSet();
        return factoryBean;
    }

    @Bean
    public JobRepository jobRepository(MapJobRepositoryFactoryBean factoryBean) throws Exception {
        return factoryBean.getObject();
    }

    @Bean
    public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(jobRepository);
        return launcher;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void runScheduledJob() throws Exception {
        logger.warn("runScheduledJob()");
        String[] files = fileUtils.listCsvFiles(homeDir);
        String fullPath;
        if(files != null) {
            for (String file : files) {
                fullPath = homeDir + File.separator + file;
                logger.info(String.format("Scheduled Job Started for file: '%s'", fullPath));
                JobParameters param = new JobParametersBuilder()
                        .addString("JobID", String.valueOf(System.currentTimeMillis()))
                        .addString("filePath", fullPath)
                        .toJobParameters();
                JobExecution execution = jobLauncher.run(csvFileToDatabaseJob(), param);

                if (execution.getStatus() == BatchStatus.COMPLETED) {
                    fileUtils.deleteCsv(fullPath);
                }
                logger.info(String.format("Scheduled Job finished for file: '%s' with status: %s", fullPath, execution.getStatus()));
            }
        }

    }


}
