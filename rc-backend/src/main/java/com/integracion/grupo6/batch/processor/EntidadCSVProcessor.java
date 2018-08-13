package com.integracion.grupo6.batch.processor;

import com.integracion.grupo6.batch.adapter.EntidadAdapter;
import com.integracion.grupo6.domain.Entidad;
import com.integracion.grupo6.dto.EntidadDTO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntidadCSVProcessor implements ItemProcessor<EntidadDTO, Entidad> {

    @Autowired
    private EntidadAdapter entidadAdapter;

    @Override
    public Entidad process(EntidadDTO dto) throws Exception {
        return entidadAdapter.adapt(dto);
    }

}
