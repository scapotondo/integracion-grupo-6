package com.integracion.grupo6.batch.reader;

import com.integracion.grupo6.dto.OrderIntegrationDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

public class OrderIntegrationReader extends FlatFileItemReader<OrderIntegrationDTO> {

    private final Log logger = LogFactory.getLog(this.getClass());

    public OrderIntegrationReader() {
        setResource(new ClassPathResource("ventas.csv"));
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

    @Override
    public OrderIntegrationDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        OrderIntegrationDTO dto = super.read();

        if (dto != null) {
            logger.info(dto.toString());
        }

        return dto;
    }
}
