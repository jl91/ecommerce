package com.profectusweb.ecommerce.entities.database;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.profectusweb.ecommerce.entities.elasticsearch.ElasticsearchEntity;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "roles")
public class RoleEntity implements Serializable, GrantedAuthority, DatabaseEntity {

    private static final long serialVersionUID = 1L;

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "DATETIME")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME")
    private LocalDateTime deletedAt;

    public BigInteger getId() {
        return id;
    }

    public RoleEntity setId(BigInteger id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RoleEntity setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    private RoleEntity setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    private RoleEntity setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    private RoleEntity setDeletedAt(LocalDateTime deletedAt) {
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
    public String getAuthority() {
        return this.getName();
    }

    @Override
    public ElasticsearchEntity toElasticEntity() {
        return null;
    }
}
