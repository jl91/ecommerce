package com.profectusweb.ecommerce.entities.database;

import com.profectusweb.ecommerce.entities.elasticsearch.UserElasticsearchEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable, DatabaseEntity<UserElasticsearchEntity> {

    private static final long serialVersionUID = 1L;

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "DATETIME")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME")
    private LocalDateTime deletedAt;

    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private RoleEntity role = new RoleEntity();

    public BigInteger getId() {
        return id;
    }

    public UserEntity setId(BigInteger id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserEntity setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    private UserEntity setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    private UserEntity setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    private UserEntity setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public RoleEntity getRole() {
        return role;
    }

    public UserEntity setRole(RoleEntity roleEntity) {
        this.role = roleEntity;
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

        this.password = new BCryptPasswordEncoder(10)
                .encode(this.password);
    }

    @PreUpdate
    private void preUpdate() {
        this.setUpdatedAt(LocalDateTime.now());
    }

    @PreRemove
    public void preRemove() {
        this.setDeletedAt(LocalDateTime.now());
    }

    public UserElasticsearchEntity toElasticEntity() {
        UserElasticsearchEntity elasticSearchEntity = new UserElasticsearchEntity();
        elasticSearchEntity.setId(this.getId().intValue());
        elasticSearchEntity.setName(this.getName());
        elasticSearchEntity.setPassword(this.getPassword());
        elasticSearchEntity.setUsername(this.getUsername());
        elasticSearchEntity.setRole(this.getRole().getName());
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        elasticSearchEntity.setCreatedAt(this.getCreatedAt().format(formatter));
        if (this.updatedAt != null) {
            elasticSearchEntity.setUpdatedAt(this.getUpdatedAt().format(formatter));
        }
        return elasticSearchEntity;
    }

}
