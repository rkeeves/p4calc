package com.rkeeves.p4.dtomap;

/**
 * This entity represents an object capable of transforming a model object into a {@code Data Transfer Object}.
 * @param <T> model type
 * @param <D> {@code Data Transfer Object} type
 */
public interface DTOMapper<T,D> {

    /**
     * Given a model object transforms it into a {@code Data Transfer Object}.
     *
     * @param object model object instance
     * @return instance of the {@code Data Transfer Object}
     */
    D toDTO(T object);
}
