package com.integracion.grupo6.batch.adapter;

import com.integracion.grupo6.domain.Entidad;
import com.integracion.grupo6.dto.EntidadDTO;
import org.springframework.stereotype.Component;

@Component
public class EntidadAdapter {

    public Entidad adapt(EntidadDTO dto) {
        Entidad entidad = new Entidad();

        entidad.setId(dto.getId());
        entidad.setNombre(dto.getNombre());

        return entidad;
    }

}
