package com.integracion.grupo6.batch.reader;

import com.integracion.grupo6.dto.EntidadDTO;
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
import org.springframework.stereotype.Component;

@Component
public class EntidadCSVReader extends FlatFileItemReader<EntidadDTO> {

    protected final Log logger = LogFactory.getLog(this.getClass());

    public EntidadCSVReader() {
        setResource(new ClassPathResource("entidades.csv"));
        setLineMapper(new DefaultLineMapper<EntidadDTO>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"id", "nombre"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<EntidadDTO>() {{
                setTargetType(EntidadDTO.class);
            }});
        }});
    }

    @Override
    public EntidadDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        EntidadDTO dto = super.read();

        if (dto != null) {
            logger.info(dto.toString());
        }

        return dto;
    }
}
