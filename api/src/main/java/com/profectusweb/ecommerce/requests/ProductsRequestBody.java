package com.profectusweb.ecommerce.requests;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigInteger;

public class ProductsRequestBody implements RequestBody {

    public BigInteger id;

    @NotEmpty
    @Length(min = 1, max = 45)
    public String sku;

    @NotEmpty
    @Length(min = 1, max = 128)
    public String name;

    @NotEmpty
    @PositiveOrZero
    public Float value;

    @NotEmpty
    @Length(min = 1)
    public String description;

}
