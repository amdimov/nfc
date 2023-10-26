package com.nfc.manager.nfc_manager.entity.DTO;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class NFC_Single_Edit_DTO {
    @NotBlank(message = "Should contain NFC Default URL/Link")
    private String dynamicNFC_URL;


    public String getDynamicNFC_URL() {
        return dynamicNFC_URL;
    }

    public NFC_Single_Edit_DTO setDynamicNFC_URL(String dynamicNFC_URL) {
        this.dynamicNFC_URL = dynamicNFC_URL;
        return this;
    }
}
