package com.nfc.manager.nfc_manager.entity.views;

import com.nfc.manager.nfc_manager.utils.validator.ValidImage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class NFC_View {
    private Long id;

    private String nfcCode;

    private String imagePreviewURL;

    private String nfcTitle;

    private String nfcDescription;

    private Integer numberOfNFCs;

    private String staticNFC_URL;

    private String dynamicNFC_URL;

    private Long numberOfViews;

    private String description;

    private Boolean deleted;

    private Boolean disabled;

    private LocalDateTime createdDateTime;

    public String getNfcCode() {
        return nfcCode;
    }

    public NFC_View setNfcCode(String nfcCode) {
        this.nfcCode = nfcCode;
        return this;
    }

    public String getImagePreviewURL() {
        return imagePreviewURL;
    }

    public NFC_View setImagePreviewURL(String imagePreviewURL) {
        this.imagePreviewURL = imagePreviewURL;
        return this;
    }

    public String getNfcTitle() {
        return nfcTitle;
    }

    public NFC_View setNfcTitle(String nfcTitle) {
        this.nfcTitle = nfcTitle;
        return this;
    }

    public String getNfcDescription() {
        return nfcDescription;
    }

    public NFC_View setNfcDescription(String nfcDescription) {
        this.nfcDescription = nfcDescription;
        return this;
    }

    public String getStaticNFC_URL() {
        return staticNFC_URL;
    }

    public NFC_View setStaticNFC_URL(String staticNFC_URL) {
        this.staticNFC_URL = staticNFC_URL;
        return this;
    }

    public String getDynamicNFC_URL() {
        return dynamicNFC_URL;
    }

    public NFC_View setDynamicNFC_URL(String dynamicNFC_URL) {
        this.dynamicNFC_URL = dynamicNFC_URL;
        return this;
    }

    public Long getNumberOfViews() {
        return numberOfViews;
    }

    public NFC_View setNumberOfViews(Long numberOfViews) {
        this.numberOfViews = numberOfViews;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public NFC_View setDescription(String description) {
        this.description = description;
        return this;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public NFC_View setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public NFC_View setDisabled(Boolean disabled) {
        this.disabled = disabled;
        return this;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public NFC_View setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }

    public Long getId() {
        return id;
    }

    public NFC_View setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getNumberOfNFCs() {
        return numberOfNFCs;
    }

    public NFC_View setNumberOfNFCs(Integer numberOfNFCs) {
        this.numberOfNFCs = numberOfNFCs;
        return this;
    }
}
