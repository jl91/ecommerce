package com.profectusweb.ecommerce.requests;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;

public class RoleRequestBody implements RequestBody {

    public BigInteger id;

    @NotEmpty()
    @Length(min = 1, max = 45)
    public String name;
}
