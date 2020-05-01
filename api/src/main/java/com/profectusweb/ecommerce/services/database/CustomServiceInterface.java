package com.profectusweb.ecommerce.services.database;

import java.math.BigInteger;

public interface CustomServiceInterface<T, J> {

    T create(J data);

    T update(J data);

    boolean remove(BigInteger id);
}
