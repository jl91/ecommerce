package com.profectusweb.ecommerce.exceptions;

import java.math.BigInteger;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(String entity, BigInteger id) {
        super(String.format("%s not found for Id: %d", entity, id));
    }
}
