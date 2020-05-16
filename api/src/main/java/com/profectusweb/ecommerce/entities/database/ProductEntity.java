package com.profectusweb.ecommerce.entities.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.profectusweb.ecommerce.entities.elasticsearch.ProductElasticsearchEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "products")
public class ProductEntity implements Serializable, DatabaseEntity<ProductElasticsearchEntity> {

    private static final long serialVersionUID = 1L;

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public String toJsonString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    @Override
    public ProductElasticsearchEntity toElasticEntity() {
        ProductElasticsearchEntity productElasticsearchEntity = new ProductElasticsearchEntity();
        productElasticsearchEntity.setId(this.getId().intValue());
        productElasticsearchEntity.setSku(this.getSku());
        productElasticsearchEntity.setName(this.getName());
        productElasticsearchEntity.setValue(this.getValue());
        productElasticsearchEntity.setDescription(this.getDescription());
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        productElasticsearchEntity.setCreatedAt(this.getCreatedAt().format(formatter));
        if (this.updatedAt != null) {
            productElasticsearchEntity.setUpdatedAt(this.getUpdatedAt().format(formatter));
        }
        return productElasticsearchEntity;
    }
}
