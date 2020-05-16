package com.profectusweb.ecommerce.entities.database;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.profectusweb.ecommerce.entities.elasticsearch.ElasticsearchEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchase_order")
public class PurchaseOrderEntity implements Serializable, DatabaseEntity {

    private static final long serialVersionUID = 1L;

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "cart_id", nullable = false)
    private BigInteger cartId;

    @Column(name = "user_id", nullable = false)
    private BigInteger userId;

    @Column(name = "value", nullable = false)
    private Float value;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "DATETIME")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME")
    private LocalDateTime deletedAt;

    public BigInteger getId() {
        return id;
    }

    public PurchaseOrderEntity setId(BigInteger id) {
        this.id = id;
        return this;
    }

    public BigInteger getCartId() {
        return cartId;
    }

    public PurchaseOrderEntity setCartId(BigInteger cartId) {
        this.cartId = cartId;
        return this;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public PurchaseOrderEntity setUserId(BigInteger userId) {
        this.userId = userId;
        return this;
    }

    public Float getValue() {
        return value;
    }

    public PurchaseOrderEntity setValue(Float value) {
        this.value = value;
        return this;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public PurchaseOrderEntity setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    private PurchaseOrderEntity setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    private PurchaseOrderEntity setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    private PurchaseOrderEntity setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    @PrePersist
    private void prePersist() {
        if (this.createdAt == null) {
            this.setCreatedAt(LocalDateTime.now());
        }

        if (this.updatedAt == null) {
            this.setUpdatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    private void preUpdate() {
        this.setUpdatedAt(LocalDateTime.now());
    }

    @PreRemove
    public void preRemove() {
        this.setDeletedAt(LocalDateTime.now());
    }

    @Override
    public ElasticsearchEntity toElasticEntity() {
        return null;
    }
}
