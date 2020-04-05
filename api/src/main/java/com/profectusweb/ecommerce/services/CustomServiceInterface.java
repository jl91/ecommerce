package com.profectusweb.ecommerce.services;

public interface CustomServiceInterface<T, J> {

    T create(J data);

    T update(J data);

    boolean remove(J data);
}
