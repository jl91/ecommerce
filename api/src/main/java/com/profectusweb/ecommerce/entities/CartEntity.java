package com.profectusweb.ecommerce.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class CartEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Column(name = "user_id", nullable = false)
    private BigInteger userId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "total", nullable = false)
    private Float total;


    @OneToMany(
            mappedBy = "cartId",
            fetch = FetchType.LAZY
    )
    private List<CartItemsEntity> items = new ArrayList<>();

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "DATETIME")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME")
    private LocalDateTime deletedAt;

    public BigInteger getId() {
        return id;
    }

    public CartEntity setId(BigInteger id) {
        this.id = id;
        return this;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public CartEntity setUserId(BigInteger userId) {
        this.userId = userId;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public CartEntity setStatus(String status) {
        this.status = status;
        return this;
    }

    public Float getTotal() {
        return total;
    }

    public CartEntity setTotal(Float total) {
        this.total = total;
        return this;
    }

    public List<CartItemsEntity> getItems() {
        return items;
    }

    public CartEntity setItems(List<CartItemsEntity> items) {
        this.items = items;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    private CartEntity setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    private CartEntity setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    private CartEntity setDeletedAt(LocalDateTime deletedAt) {
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
