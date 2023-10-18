package com.nfc.manager.nfc_manager.entity.DTO;

import com.nfc.manager.nfc_manager.entity.NFC;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;

public class OrdersDTO {
    private String orderName;

    private String productType;

    private String orderDescription;

    private String orderCode;

    private String isDeleted;

    private LocalDateTime creationDate;

    private String previewImageURL;

    private List<NFC_DTO> nfc;

    public String getOrderName() {
        return orderName;
    }

    public OrdersDTO setOrderName(String orderName) {
        this.orderName = orderName;
        return this;
    }

    public String getProductType() {
        return productType;
    }

    public OrdersDTO setProductType(String productType) {
        this.productType = productType;
        return this;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public OrdersDTO setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
        return this;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public OrdersDTO setOrderCode(String orderCode) {
        this.orderCode = orderCode;
        return this;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public OrdersDTO setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public OrdersDTO setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getPreviewImageURL() {
        return previewImageURL;
    }

    public OrdersDTO setPreviewImageURL(String previewImageURL) {
        this.previewImageURL = previewImageURL;
        return this;
    }

    public List<NFC_DTO> getNfc() {
        return nfc;
    }

    public OrdersDTO setNfc(List<NFC_DTO> nfc) {
        this.nfc = nfc;
        return this;
    }
}
