package com.profectusweb.ecommerce.requests;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.math.BigInteger;

public class CartItemRequestBody {

    @Positive()
    @Min(1)
    public BigInteger id;

    @Positive()
    @Min(1)
    public BigInteger productId;

    @Positive()
    @Min(1)
    public BigInteger cartId;

    @Positive()
    @Min(1)
    public Integer quantity;
}
