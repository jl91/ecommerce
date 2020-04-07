package com.profectusweb.ecommerce.services;

import com.profectusweb.ecommerce.entities.CartEntity;
import com.profectusweb.ecommerce.entities.CartItemEntity;
import com.profectusweb.ecommerce.repositories.CartsRepository;
import com.profectusweb.ecommerce.requests.CartRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

@Service("CartService")
public class CartService implements CustomServiceInterface<CartEntity, CartRequestBody> {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartsRepository cartsRepository;

    @Override

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public CartEntity create(CartRequestBody data) {
        CartEntity cartEntity = new CartEntity();

        cartEntity.setStatus(data.status);
        cartEntity.setUserId(data.userId);
        cartEntity.setTotal(0f);

        cartEntity = this.cartsRepository
                .save(cartEntity);

        if (data.cartItems.size() <= 0) {
            return cartEntity;
        }

        List<CartItemEntity> cartItemEntityList = (List<CartItemEntity>) this.cartItemService
                .batchCreate(data.cartItems, cartEntity);

        cartEntity.setItems(cartItemEntityList);

        Float total = this.calcTotal(cartItemEntityList);
        cartEntity.setTotal(total);

        this.cartsRepository
                .save(cartEntity);


        return cartEntity;
    }

    protected Float calcTotal(List<CartItemEntity> cartItemEntityList) {
        return cartItemEntityList
                .stream()
                .filter((cartItemEntity) -> cartItemEntity.getDeletedAt() == null)
                .map(cartItemsEntity -> cartItemsEntity.getValue())
                .reduce(0f, (subTotal, nextValue) -> subTotal + nextValue);
    }

    @Override
    public CartEntity update(CartRequestBody data) {
        return null;
    }

    @Override
    public boolean remove(BigInteger id) {
        return false;
    }
}
