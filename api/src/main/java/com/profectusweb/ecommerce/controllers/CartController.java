package com.profectusweb.ecommerce.controllers;

import com.profectusweb.ecommerce.entities.database.CartEntity;
import com.profectusweb.ecommerce.entities.database.CartItemEntity;
import com.profectusweb.ecommerce.repositories.database.CartsRepository;
import com.profectusweb.ecommerce.requests.CartItemRequestBody;
import com.profectusweb.ecommerce.requests.CartRequestBody;
import com.profectusweb.ecommerce.services.database.CartItemService;
import com.profectusweb.ecommerce.services.database.CartService;
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean remove(
            @PathVariable(name = "id") BigInteger id
    ) {
        return this.cartService
                .remove(id);
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

    @PutMapping("/{id}/items/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CartItemEntity addItemToCart(
            @PathVariable(name = "id") BigInteger id,
            @PathVariable(name = "itemId") BigInteger itemId,
            @Valid @RequestBody CartItemRequestBody incommingRequestBody
    ) {
        incommingRequestBody.id = itemId;
        incommingRequestBody.cartId = id;
        return this.cartItemService
                .updateItemOnCart(incommingRequestBody);
    }

    @DeleteMapping("/{id}/items/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean removeItemToCart(
            @PathVariable(name = "id") BigInteger id,
            @PathVariable(name = "itemId") BigInteger itemId
    ) {
        return this.cartItemService
                .removeItemFromCart(id, itemId);
    }


}
