package com.nfc.manager.nfc_manager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class UserURL_TEST extends BaseEntity{

    @Column
    private String url;

    @Column
    private String tempURL;

    public String getUrl() {
        return url;
    }

    public UserURL_TEST setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getTempURL() {
        return tempURL;
    }

    public UserURL_TEST setTempURL(String tempURL) {
        this.tempURL = tempURL;
        return this;
    }
}
