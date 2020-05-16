package com.profectusweb.ecommerce.entities.database;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.profectusweb.ecommerce.entities.elasticsearch.ElasticsearchEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchase_order_history")
public class PurchaseOrderHistoryEntity implements Serializable, DatabaseEntity {

    private static final long serialVersionUID = 1L;

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "purchase_order_id", nullable = false)
    private BigInteger purchaseOrderId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME")
    private LocalDateTime deletedAt;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(BigInteger purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    private void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    private void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    @PrePersist
    private void prePersist() {
        this.setCreatedAt(LocalDateTime.now());
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
