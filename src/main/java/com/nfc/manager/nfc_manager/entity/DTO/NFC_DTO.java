package com.nfc.manager.nfc_manager.entity.DTO;

import jakarta.persistence.Column;

public class NFC_DTO {
    private String staticNFC_URL;

    private String dynamicNFC_URL;

    private Long numberOfViews;

    private Boolean enabled;

    private String description;

    public String getStaticNFC_URL() {
        return staticNFC_URL;
    }

    public NFC_DTO setStaticNFC_URL(String staticNFC_URL) {
        this.staticNFC_URL = staticNFC_URL;
        return this;
    }

    public String getDynamicNFC_URL() {
        return dynamicNFC_URL;
    }

    public NFC_DTO setDynamicNFC_URL(String dynamicNFC_URL) {
        this.dynamicNFC_URL = dynamicNFC_URL;
        return this;
    }

    public Long getNumberOfViews() {
        return numberOfViews;
    }

    public NFC_DTO setNumberOfViews(Long numberOfViews) {
        this.numberOfViews = numberOfViews;
        return this;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public NFC_DTO setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NFC_DTO setDescription(String description) {
        this.description = description;
        return this;
    }
}
