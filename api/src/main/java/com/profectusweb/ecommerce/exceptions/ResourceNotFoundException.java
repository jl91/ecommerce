package com.profectusweb.ecommerce.exceptions;

import java.math.BigInteger;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String entity, BigInteger id) {
        super(String.format("%s not found for Id: %d", entity, id));
    }
}
