package com.profectusweb.ecommerce.requests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CartRequestBody implements RequestBody {

    @Positive()
    @Min(1)
    public BigInteger id;

    @Positive()
    @Min(1)
    public BigInteger userId;

    @NotEmpty()
    public String status;

    public List<CartItemRequestBody> cartItems = new ArrayList<>();

}
