package com.nfc.manager.nfc_manager.entity.DTO;

import com.nfc.manager.nfc_manager.utils.validator.ValidImage;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public class NFC_Edit_DTO {
    private Long id;
    @NotBlank(message = "Should contain ID Number")
    private String nfcCode;

    @ValidImage
    private MultipartFile filePreview;

    private String imagePreviewURL;

    @NotBlank(message = "Should contain NFC Title")
    private String nfcTitle;

    private String nfcDescription;

    @Min(value = 1, message = "Register at least 1 NFC")
    private Integer numberOfNFCs;

    @Size(min = 10, message = "Static URL must be at least 10 chars long")
    private String staticNFC_URL;

    @URL(message = "It should be URL/Link")
    @NotBlank(message = "Should contain NFC Default URL/Link")
    private String dynamicNFC_URL;

    private Long numberOfViews;

    private Boolean deleted;

    private Boolean disabled;

    private LocalDateTime createdDateTime;

    public MultipartFile getFilePreview() {
        return filePreview;
    }

    public NFC_Edit_DTO setFilePreview(MultipartFile filePreview) {
        this.filePreview = filePreview;
        return this;
    }

    public String getNfcCode() {
        return nfcCode;
    }

    public NFC_Edit_DTO setNfcCode(String nfcCode) {
        this.nfcCode = nfcCode;
        return this;
    }

    public Long getId() {
        return id;
    }

    public NFC_Edit_DTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getImagePreviewURL() {
        return imagePreviewURL;
    }

    public NFC_Edit_DTO setImagePreviewURL(String imagePreviewURL) {
        this.imagePreviewURL = imagePreviewURL;
        return this;
    }

    public String getNfcTitle() {
        return nfcTitle;
    }

    public NFC_Edit_DTO setNfcTitle(String nfcTitle) {
        this.nfcTitle = nfcTitle;
        return this;
    }

    public String getNfcDescription() {
        return nfcDescription;
    }

    public NFC_Edit_DTO setNfcDescription(String nfcDescription) {
        this.nfcDescription = nfcDescription;
        return this;
    }

    public Integer getNumberOfNFCs() {
        return numberOfNFCs;
    }

    public NFC_Edit_DTO setNumberOfNFCs(Integer numberOfNFCs) {
        this.numberOfNFCs = numberOfNFCs;
        return this;
    }

    public String getStaticNFC_URL() {
        return staticNFC_URL;
    }

    public NFC_Edit_DTO setStaticNFC_URL(String staticNFC_URL) {
        this.staticNFC_URL = staticNFC_URL;
        return this;
    }

    public String getDynamicNFC_URL() {
        return dynamicNFC_URL;
    }

    public NFC_Edit_DTO setDynamicNFC_URL(String dynamicNFC_URL) {
        this.dynamicNFC_URL = dynamicNFC_URL;
        return this;
    }

    public Long getNumberOfViews() {
        return numberOfViews;
    }

    public NFC_Edit_DTO setNumberOfViews(Long numberOfViews) {
        this.numberOfViews = numberOfViews;
        return this;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public NFC_Edit_DTO setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public NFC_Edit_DTO setDisabled(Boolean disabled) {
        this.disabled = disabled;
        return this;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public NFC_Edit_DTO setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
        return this;
    }
}
