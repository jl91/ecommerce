package com.profectusweb.ecommerce.requests;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.math.BigInteger;

public class UsersRequestBody implements RequestBody {

    @Positive()
    @Min(1)
    public BigInteger id;

    @Positive()
    public BigInteger roleId;

    @NotEmpty()
    @Length(min = 1, max = 128)
    public String username;

    @NotEmpty()
    @Length(min = 1, max = 128)
    public String password;

    @NotEmpty()
    @Length(min = 1, max = 128)
    public String name;
}
