package com.profectusweb.ecommerceapi.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "purchase_order_history")
public class PurchaseOrderHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    @Id
    @Column(name = "purchase_order_id", insertable = false, nullable = false)
    private Integer purchaseOrderId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "created_at", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime createdAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME")
    private LocalDateTime deletedAt;

}
