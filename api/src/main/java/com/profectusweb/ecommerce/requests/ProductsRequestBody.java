package com.profectusweb.ecommerce.requests;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigInteger;

public class ProductsRequestBody implements RequestBody {

    @Positive()
    @Min(1)
    public BigInteger id;

    @NotEmpty()
    @Length(min = 1, max = 45)
    public String sku;

    @NotEmpty()
    @Length(min = 1, max = 128)
    public String name;

    @PositiveOrZero()
    public Float value;

    @NotEmpty()
    @Length(min = 1)
    public String description;

}
