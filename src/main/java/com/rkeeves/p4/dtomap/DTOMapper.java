package com.rkeeves.p4.dtomap;

/**
 * This entity represents an object capable of transforming a model object into a Data Transfer Object.
 * @param <T> Model type
 * @param <D> Data Transfer Object type
 */
public interface DTOMapper<T,D> {

    /**
     * Given a model object transforms it into a Data Transfer Object.
     *
     * @param object model object instance
     * @return instance of the Data Transfer Object
     */
    D toDTO(T object);
}
