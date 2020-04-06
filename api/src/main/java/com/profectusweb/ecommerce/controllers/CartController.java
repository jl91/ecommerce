package com.profectusweb.ecommerce.controllers;

import com.profectusweb.ecommerce.entities.CartEntity;
import com.profectusweb.ecommerce.repositories.CartsRepository;
import com.profectusweb.ecommerce.requests.CartItemRequestBody;
import com.profectusweb.ecommerce.requests.CartRequestBody;
import com.profectusweb.ecommerce.services.CartItemService;
import com.profectusweb.ecommerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RestController()
@RequestMapping("/carts")
public class CartController extends BaseController<CartEntity> {

    private CartService cartService;

    private CartItemService cartItemService;

    @Autowired
    CartController(
            CartsRepository cartsRepository,
            CartService cartService,
            CartItemService cartItemService
    ) {
        super(
                "Cart",
                cartsRepository
        );
        this.cartService = cartService;
        this.cartItemService = cartItemService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartEntity create(
            @Valid @RequestBody CartRequestBody incommingRequestBody
    ) {
        return this.cartService
                .create(incommingRequestBody);
    }

    @PostMapping("/{id}/items")
    @ResponseStatus(HttpStatus.CREATED)
    public CartEntity addItemToCart(
            @PathVariable(name = "id") BigInteger id,
            @Valid @RequestBody List<CartItemRequestBody> incommingRequestBody
    ) {
        return this.cartItemService
                .addItemsToCart(id, incommingRequestBody);
    }


}
