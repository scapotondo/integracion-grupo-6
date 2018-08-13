package com.integracion.grupo6.batch.writer;

import com.integracion.grupo6.domain.Entidad;
import com.integracion.grupo6.service.EntidadService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EntidadCSVWriter implements ItemWriter<Entidad> {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private EntidadService entidadService;

    @Override
    public void write(List<? extends Entidad> list) throws Exception {
        for (Entidad entidad : list) {
            try {
                entidadService.save(entidad);
            } catch (Exception ex) {
                logger.error("Error al guardar entidad en repositorio", ex);
            }
        }
    }
}
