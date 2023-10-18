package com.nfc.manager.nfc_manager.entity;

import jakarta.persistence.*;

@Entity
public class NFC extends BaseEntity{
    @Column(unique = true)
    private String staticNFC_URL;

    @Column
    private String dynamicNFC_URL;

    @Column
    private Long numberOfViews;

    @Column
    private Boolean enabled;

    @Column(columnDefinition = "TEXT")
    private String description;

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

    public Boolean getEnabled() {
        return enabled;
    }

    public NFC setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NFC setDescription(String description) {
        this.description = description;
        return this;
    }
}
