package com.integracion.grupo6.adapter;

public interface EntityDtoAdapter<T, K> {

    T toEntity(K dto);
    K toDTO(T entity);

}
