package com.profectusweb.ecommerce.services;

import com.profectusweb.ecommerce.entities.CartEntity;
import com.profectusweb.ecommerce.entities.CartItemsEntity;
import com.profectusweb.ecommerce.entities.ProductEntity;
import com.profectusweb.ecommerce.exceptions.ResourceNotFoundException;
import com.profectusweb.ecommerce.repositories.CartItemRepository;
import com.profectusweb.ecommerce.repositories.CartsRepository;
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
    private CartsRepository cartRepository;

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
                    return cartItemsEntity.setProductId(cartItem.productId)
                            .setCartId(cartEntity.getId())
                            .setQuantity(cartItem.quantity)
                            .setProductSnapshot(productEntity.toJsonString())
                            .setValue(productEntity.getValue() * cartItem.quantity);
                })
                .collect(Collectors.toList());

        return this.cartItemRepository
                .saveAll(cartItemEntities);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public CartItemsEntity create(CartItemRequestBody data) {
        return null;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public CartEntity addItemsToCart(BigInteger cartId, List<CartItemRequestBody> data) {

        data.stream()
                .forEach((cartItemRequestBody) -> cartItemRequestBody.cartId = cartId);

        CartEntity cartEntity = this.cartRepository
                .findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", cartId));

        List<BigInteger> productIds = extractProductIds(data);

        List<ProductEntity> products = (List<ProductEntity>) productsRepository
                .findAllById(productIds);


        List<CartItemsEntity> cartItemsEntities = data.stream()
                .map((cartItemRequestBody) -> {
                    boolean cartHasProduct = cartHasProduct(cartEntity, cartItemRequestBody);
                    ProductEntity productEntity = getProductFromList(products, cartItemRequestBody);
                    CartItemsEntity cartItemsEntity = new CartItemsEntity();

                    if (cartHasProduct) {
                        cartItemsEntity = getCartItemByProduct(cartEntity, productEntity);
                    }

                    return cartItemsEntity.setCartId(cartId)
                            .setProductId(productEntity.getId())
                            .setQuantity(cartItemRequestBody.quantity)
                            .setProductSnapshot(productEntity.toJsonString())
                            .setValue(cartItemRequestBody.quantity * productEntity.getValue());

                })
                .collect(Collectors.toList());

        cartEntity.setItems(cartItemsEntities);

        this.cartItemRepository.saveAll(cartItemsEntities);

        Float total = this.calcTotal(cartEntity.getItems());
        cartEntity.setTotal(total);

        return this.cartRepository.save(cartEntity);
    }

    private Float calcTotal(List<CartItemsEntity> cartItemsEntityList) {
        return cartItemsEntityList
                .stream()
                .map(cartItemsEntity -> cartItemsEntity.getValue())
                .reduce(0f, (subTotal, nextValue) -> subTotal + nextValue);
    }

    private CartItemsEntity getCartItemByProduct(CartEntity cartEntity, ProductEntity productEntity) {
        try {

            List<CartItemsEntity> cartItemsEntities = cartEntity.getItems();

            return cartItemsEntities.stream()
                    .filter(cartItemsEntity -> cartItemsEntity.getProductId().equals(productEntity.getId()))
                    .collect(Collectors.toList())
                    .get(0);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Product", productEntity.getId());
        }
    }

    private boolean cartHasProduct(CartEntity cartEntity, CartItemRequestBody cartItemRequestBody) {
        List<CartItemsEntity> cartItemsEntities = cartEntity.getItems();

        if (cartItemsEntities.size() <= 0) {
            return false;
        }

        return cartItemsEntities.stream()
                .filter(cartItemsEntity -> cartItemsEntity.getProductId().equals(cartItemRequestBody.productId))
                .collect(Collectors.toList())
                .size() > 0;
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
