package com.profectusweb.ecommerce.services;

import com.profectusweb.ecommerce.entities.CartEntity;
import com.profectusweb.ecommerce.entities.CartItemEntity;
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
public class CartItemService implements CustomServiceInterface<CartItemEntity, CartItemRequestBody> {

    @Autowired
    private CartsRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private CartService cartService;

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
    public Iterable<CartItemEntity> batchCreate(
            List<CartItemRequestBody> cartItemRequestBodies,
            CartEntity cartEntity
    ) {

        List<BigInteger> productIds = extractProductIds(cartItemRequestBodies);

        List<ProductEntity> products = (List<ProductEntity>) productsRepository
                .findAllById(productIds);


        List<CartItemEntity> cartItemEntities = cartItemRequestBodies
                .stream()
                .map(cartItem -> {

                    ProductEntity productEntity = getProductFromList(products, cartItem);

                    CartItemEntity cartItemEntity = new CartItemEntity();
                    return cartItemEntity.setProductId(cartItem.productId)
                            .setCartId(cartEntity.getId())
                            .setQuantity(cartItem.quantity)
                            .setProductSnapshot(productEntity.toJsonString())
                            .setValue(productEntity.getValue() * cartItem.quantity);
                })
                .collect(Collectors.toList());

        return this.cartItemRepository
                .saveAll(cartItemEntities);
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


        List<CartItemEntity> cartItemsEntities = data.stream()
                .map((cartItemRequestBody) -> {
                    boolean cartHasProduct = cartHasProduct(cartEntity, cartItemRequestBody);
                    ProductEntity productEntity = getProductFromList(products, cartItemRequestBody);
                    CartItemEntity cartItemEntity = new CartItemEntity();

                    if (cartHasProduct) {
                        cartItemEntity = getCartItemByProduct(cartEntity, productEntity);
                    }

                    return cartItemEntity.setCartId(cartId)
                            .setProductId(productEntity.getId())
                            .setQuantity(cartItemRequestBody.quantity)
                            .setProductSnapshot(productEntity.toJsonString())
                            .setValue(cartItemRequestBody.quantity * productEntity.getValue());

                })
                .collect(Collectors.toList());

        cartEntity.setItems(cartItemsEntities);

        this.cartItemRepository.saveAll(cartItemsEntities);

        Float total = cartService.calcTotal(cartEntity.getItems());
        cartEntity.setTotal(total);

        this.cartRepository.save(cartEntity);
        return this.cartRepository
                .findById(cartEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart", cartEntity.getId()));
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean removeItemFromCart(BigInteger cartId, BigInteger itemId) {

        CartItemEntity cartItemEntity = this.cartItemRepository
                .findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem", itemId));

        cartItemEntity.preRemove();

        this.cartItemRepository.save(cartItemEntity);

        CartEntity cartEntity = this.cartRepository
                .findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", cartId));

        Float total = cartService.calcTotal(cartEntity.getItems());

        cartEntity.setTotal(total);

        this.cartRepository.save(cartEntity);

        return true;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public CartItemEntity updateItemOnCart(CartItemRequestBody data) {

        CartItemEntity cartItemEntity = this.cartItemRepository
                .findById(data.id)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem", data.id));

        ProductEntity productEntity = this.productsRepository
                .findById(data.productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", data.productId));


        cartItemEntity.setProductId(data.productId)
                .setValue(productEntity.getValue() * data.quantity)
                .setProductSnapshot(productEntity.toJsonString())
                .setQuantity(data.quantity)
        ;

        this.cartItemRepository.save(cartItemEntity);

        CartEntity cartEntity = this.cartRepository
                .findById(data.cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", data.cartId));

        Float total = cartService.calcTotal(cartEntity.getItems());

        cartEntity.setTotal(total);

        this.cartRepository.save(cartEntity);

        return cartItemEntity;
    }


    private Float calcTotal(List<CartItemEntity> cartItemEntityList) {
        return cartItemEntityList
                .stream()
                .filter((cartItemEntity) -> cartItemEntity.getDeletedAt() == null)
                .map(cartItemsEntity -> cartItemsEntity.getValue())
                .reduce(0f, (subTotal, nextValue) -> subTotal + nextValue);
    }

    private CartItemEntity getCartItemByProduct(CartEntity cartEntity, ProductEntity productEntity) {
        try {

            List<CartItemEntity> cartItemsEntities = cartEntity.getItems();

            return cartItemsEntities.stream()
                    .filter(cartItemsEntity -> cartItemsEntity.getProductId().equals(productEntity.getId()))
                    .collect(Collectors.toList())
                    .get(0);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Product", productEntity.getId());
        }
    }

    private boolean cartHasProduct(CartEntity cartEntity, CartItemRequestBody cartItemRequestBody) {
        List<CartItemEntity> cartItemsEntities = cartEntity.getItems();

        if (cartItemsEntities.size() <= 0) {
            return false;
        }

        return cartItemsEntities.stream()
                .filter(cartItemsEntity -> cartItemsEntity.getProductId().equals(cartItemRequestBody.productId))
                .collect(Collectors.toList())
                .size() > 0;
    }


    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public CartItemEntity create(CartItemRequestBody data) {
        CartEntity cartEntity = this.cartRepository
                .findById(data.cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", data.cartId));

        ProductEntity productEntity = this.productsRepository
                .findById(data.productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", data.productId));

        CartItemEntity cartItemEntity = new CartItemEntity();

        if (this.cartHasProduct(cartEntity, data)) {
            cartItemEntity = this.getCartItemByProduct(cartEntity, productEntity);
        }

        cartItemEntity.setQuantity(data.quantity)
                .setProductSnapshot(productEntity.toJsonString())
                .setValue(productEntity.getValue() * data.quantity)
                .setProductId(data.productId)
                .setCartId(cartEntity.getId())
        ;

        this.cartItemRepository
                .save(cartItemEntity);

        Float total = cartService.calcTotal(cartEntity.getItems());
        cartEntity.setTotal(total);

        this.cartRepository.save(cartEntity);

        return cartItemEntity;
    }

    @Override
    public CartItemEntity update(CartItemRequestBody data) {
        return this.create(data);
    }

    @Override
    public boolean remove(BigInteger id) {
        CartItemEntity cartItem = this.cartItemRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem", id));

        cartItem.preRemove();
        this.cartItemRepository.save(cartItem);

        CartEntity cartEntity = this.cartRepository
                .findById(cartItem.getCartId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart", cartItem.getId()));

        Float total = cartService.calcTotal(cartEntity.getItems());
        cartEntity.setTotal(total);

        return true;
    }
}
