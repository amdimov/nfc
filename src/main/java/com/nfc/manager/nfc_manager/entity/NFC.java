package com.nfc.manager.nfc_manager.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class NFC extends BaseEntity{
    @Column(nullable = false, unique = true)
    private String nfcCode;

    @Column(nullable = false)
    private String nfcTitle;

    @Column(columnDefinition = "TEXT")
    private String nfcDescription;

    @Column(unique = true, nullable = false)
    private String staticNFC_URL;

    @Column(nullable = false)
    private String dynamicNFC_URL;

    @Column
    private Long numberOfViews;

    @Column(nullable = false)
    private Boolean deleted;

    @Column(nullable = false)
    private Boolean disabled;

    private String imagePreviewURL;

    @Column(nullable = false)
    private LocalDateTime createdDateTime;

    public String getStaticNFC_URL() {
        return staticNFC_URL;
    }

    public NFC setStaticNFC_URL(String staticNFC_URL) {
        this.staticNFC_URL = staticNFC_URL;
        return this;
    }

    public String getDynamicNFC_URL() {
        return dynamicNFC_URL;
    }

    public NFC setDynamicNFC_URL(String dynamicNFC_URL) {
        this.dynamicNFC_URL = dynamicNFC_URL;
        return this;
    }

    public Long getNumberOfViews() {
        return numberOfViews;
    }

    public NFC setNumberOfViews(Long numberOfViews) {
        this.numberOfViews = numberOfViews;
        return this;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public NFC setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public NFC setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public NFC setDisabled(Boolean disabled) {
        this.disabled = disabled;
        return this;
    }

    public String getNfcTitle() {
        return nfcTitle;
    }

    public NFC setNfcTitle(String nfcTitle) {
        this.nfcTitle = nfcTitle;
        return this;
    }

    public String getNfcDescription() {
        return nfcDescription;
    }

    public NFC setNfcDescription(String nfcDescription) {
        this.nfcDescription = nfcDescription;
        return this;
    }


    public String getImagePreviewURL() {
        return imagePreviewURL;
    }

    public NFC setImagePreviewURL(String imagePreviewURL) {
        this.imagePreviewURL = imagePreviewURL;
        return this;
    }

    public String getNfcCode() {
        return nfcCode;
    }

    public NFC setNfcCode(String nfcCode) {
        this.nfcCode = nfcCode;
        return this;
    }
}
