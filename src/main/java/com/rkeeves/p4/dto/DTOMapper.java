package com.rkeeves.p4.dto;

public interface DTOMapper<T,D> {

    D toDTO(T object);
}
