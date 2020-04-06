package com.profectusweb.ecommerce.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.profectusweb.ecommerce.entities.CartEntity;
import com.profectusweb.ecommerce.entities.CartItemsEntity;
import com.profectusweb.ecommerce.entities.ProductEntity;
import com.profectusweb.ecommerce.exceptions.ResourceNotFoundException;
import com.profectusweb.ecommerce.repositories.CartItemRepository;
import com.profectusweb.ecommerce.repositories.ProductsRepository;
import com.profectusweb.ecommerce.requests.CartItemRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service("CartItemService")
public class CartItemService implements CustomServiceInterface<CartItemsEntity, CartItemRequestBody> {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductsRepository productsRepository;

    private List<BigInteger> extractProductIds(List<CartItemRequestBody> cartItemRequestBodies) {
        return cartItemRequestBodies
                .stream()
                .map(cartItemRequestBody -> cartItemRequestBody.productId)
                .collect(Collectors.toList());
    }

    private ProductEntity getProductFromList(
            List<ProductEntity> productEntities,
            CartItemRequestBody cartItem
    ) {
        try {
            return productEntities
                    .stream()
                    .filter(product -> product.getId().equals(cartItem.productId))
                    .collect(Collectors.toList())
                    .get(0);

        } catch (Exception e) {
            throw new ResourceNotFoundException("Product", cartItem.productId);
        }

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Iterable<CartItemsEntity> batchCreate(
            List<CartItemRequestBody> cartItemRequestBodies,
            CartEntity cartEntity
    ) {

        List<BigInteger> productIds = extractProductIds(cartItemRequestBodies);

        List<ProductEntity> products = (List<ProductEntity>) productsRepository
                .findAllById(productIds);


        List<CartItemsEntity> cartItemEntities = cartItemRequestBodies
                .stream()
                .map(cartItem -> {

                    ProductEntity productEntity = getProductFromList(products, cartItem);

                    CartItemsEntity cartItemsEntity = new CartItemsEntity();
                    try {
                        cartItemsEntity.setProductId(cartItem.productId)
                                .setCartId(cartEntity.getId())
                                .setQuantity(cartItem.quantity)
                                .setProductSnapshot(productEntity.toJsonString())
                                .setValue(productEntity.getValue() * cartItem.quantity)
                        ;
                        return cartItemsEntity;
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                    return null;
                })
                .collect(Collectors.toList());

        return this.cartItemRepository
                .saveAll(cartItemEntities);
    }

    @Override
    public CartItemsEntity create(CartItemRequestBody data) {
        return null;
    }

    @Override
    public CartItemsEntity update(CartItemRequestBody data) {
        return null;
    }

    @Override
    public boolean remove(BigInteger id) {
        return false;
    }
}
