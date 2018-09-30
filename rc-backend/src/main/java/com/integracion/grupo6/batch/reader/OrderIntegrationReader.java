package com.integracion.grupo6.batch.reader;

import com.integracion.grupo6.dto.OrderIntegrationDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

@Component
@JobScope
public class OrderIntegrationReader extends FlatFileItemReader<OrderIntegrationDTO> {

    private final Log logger = LogFactory.getLog(this.getClass());

    public OrderIntegrationReader() {

        setLineMapper(new DefaultLineMapper<OrderIntegrationDTO>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(
                        "id",
                        "etaDate",
                        "amount",
                        "clientIdentification",
                        "clientFullName",
                        "clientEmail",
                        "productId",
                        "productDescription");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<OrderIntegrationDTO>() {{
                setTargetType(OrderIntegrationDTO.class);
            }});
        }});
    }

    @BeforeStep
    public void loadParameters(StepExecution stepExecution) {
        String fileName = stepExecution.getJobExecution().getJobParameters().getString("filePath");
        setResource(new FileSystemResource(fileName));
    }

    @Override
    public OrderIntegrationDTO read() throws Exception {
        OrderIntegrationDTO dto = super.read();

        if (dto != null) {
            // logger.info(dto.toString());
        }

        return dto;
    }
}
