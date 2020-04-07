package com.profectusweb.ecommerce.entities;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "cart_items")
public class CartItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "product_id", nullable = false)
    private BigInteger productId;

    @Column(name = "cart_id", nullable = false)
    private BigInteger cartId;

    @Column(name = "product_snapshot", nullable = false)
    private String productSnapshot;

    @Column(name = "value", nullable = false)
    private Float value;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "DATETIME")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME")
    private LocalDateTime deletedAt;

    public BigInteger getId() {
        return id;
    }

    public CartItemEntity setId(BigInteger id) {
        this.id = id;
        return this;
    }

    public BigInteger getProductId() {
        return productId;
    }

    public CartItemEntity setProductId(BigInteger productId) {
        this.productId = productId;
        return this;
    }

    public BigInteger getCartId() {
        return cartId;
    }

    public CartItemEntity setCartId(BigInteger cartId) {
        this.cartId = cartId;
        return this;
    }

    public String getProductSnapshot() {
        return productSnapshot;
    }

    public CartItemEntity setProductSnapshot(String productSnapshot) {
        this.productSnapshot = productSnapshot;
        return this;
    }

    public Float getValue() {
        return value;
    }

    public CartItemEntity setValue(Float value) {
        this.value = value;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public CartItemEntity setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    private CartItemEntity setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    private CartItemEntity setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    private CartItemEntity setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    @PrePersist
    private void prePersist() {
        this.setCreatedAt(LocalDateTime.now());
    }

    @PreUpdate
    private void preUpdate() {
        this.setUpdatedAt(LocalDateTime.now());
    }

    @PreRemove
    public void preRemove() {
        this.setDeletedAt(LocalDateTime.now());
    }
}
