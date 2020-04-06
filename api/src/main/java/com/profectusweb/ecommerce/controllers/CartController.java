package com.profectusweb.ecommerce.controllers;

import com.profectusweb.ecommerce.entities.CartEntity;
import com.profectusweb.ecommerce.entities.ProductEntity;
import com.profectusweb.ecommerce.repositories.CartsRepository;
import com.profectusweb.ecommerce.requests.CartRequestBody;
import com.profectusweb.ecommerce.requests.ProductsRequestBody;
import com.profectusweb.ecommerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping("/carts")
public class CartController extends BaseController<CartEntity> {

    private CartService cartService;

    @Autowired
    CartController(
            CartsRepository cartsRepository,
            CartService cartService
    ) {
        super(
                "Cart",
                cartsRepository
        );
        this.cartService = cartService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartEntity create(
            @Valid @RequestBody CartRequestBody incommingRequestBody
    ) {
        return this.cartService
                .create(incommingRequestBody);
    }


}
