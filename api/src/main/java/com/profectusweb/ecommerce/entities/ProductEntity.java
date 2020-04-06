package com.profectusweb.ecommerce.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class ProductEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Column(name = "sku", nullable = false)
    private String sku;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "value", nullable = false)
    private Float value;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "DATETIME")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME")
    private LocalDateTime deletedAt;

    public BigInteger getId() {
        return id;
    }

    public ProductEntity setId(BigInteger id) {
        this.id = id;
        return this;
    }

    public String getSku() {
        return sku;
    }

    public ProductEntity setSku(String sku) {
        this.sku = sku;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Float getValue() {
        return value;
    }

    public ProductEntity setValue(Float value) {
        this.value = value;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    private ProductEntity setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    private ProductEntity setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    private ProductEntity setDeletedAt(LocalDateTime deletedAt) {
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

    public String toJsonString() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String ISO8861Format = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
            objectMapper.setDateFormat(new SimpleDateFormat(ISO8861Format));
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
