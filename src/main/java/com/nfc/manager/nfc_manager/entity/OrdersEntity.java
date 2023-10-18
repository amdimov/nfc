package com.nfc.manager.nfc_manager.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrdersEntity extends BaseEntity{
    @Column
    private String orderName;

    @Column
    private String productType;

    @Column(columnDefinition = "TEXT")
    private String orderDescription;

    @Column(unique = true, nullable = false)
    private String orderCode;

    @Column(nullable = false)
    private String isDeleted;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Column
    private String previewImageURL;

    @OneToMany
    private List<NFC> nfc;

    public OrdersEntity() {
    }

    public String getOrderName() {
        return orderName;
    }

    public OrdersEntity setOrderName(String orderName) {
        this.orderName = orderName;
        return this;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public OrdersEntity setOrderCode(String orderCode) {
        this.orderCode = orderCode;
        return this;
    }

    public String getProductType() {
        return productType;
    }

    public OrdersEntity setProductType(String productType) {
        this.productType = productType;
        return this;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public OrdersEntity setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public OrdersEntity setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getPreviewImageURL() {
        return previewImageURL;
    }

    public OrdersEntity setPreviewImageURL(String previewImageURL) {
        this.previewImageURL = previewImageURL;
        return this;
    }


    public List<NFC> getNfc() {
        return nfc;
    }

    public OrdersEntity setNfc(List<NFC> nfc) {
        this.nfc = nfc;
        return this;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public OrdersEntity setOrderDescription(String productDescription) {
        this.orderDescription = productDescription;
        return this;
    }
}
