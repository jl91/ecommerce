package com.profectusweb.ecommerce.exceptions;

import java.math.BigInteger;

public class RoleNotFoundException extends Exception {
    public RoleNotFoundException(BigInteger id) {
        super(String.format("Role not found for Id: %s", id));
    }
}
