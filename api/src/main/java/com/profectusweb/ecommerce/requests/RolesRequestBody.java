package com.profectusweb.ecommerce.requests;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.math.BigInteger;

public class RolesRequestBody implements RequestBody {

    @Positive()
    @Min(1)
    public BigInteger id;

    @NotEmpty()
    @Length(min = 1, max = 45)
    public String name;
}
