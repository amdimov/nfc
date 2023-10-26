package com.nfc.manager.nfc_manager.entity.views;

public class NFCsingleStatsView {
    private Integer nfcQuantity;

    private Long totalViews;

    private Double percentOfOpenedNFCVsQuantity;

    public Integer getNfcQuantity() {
        return nfcQuantity;
    }

    public NFCsingleStatsView setNfcQuantity(Integer nfcQuantity) {
        this.nfcQuantity = nfcQuantity;
        return this;
    }

    public Long getTotalViews() {
        return totalViews;
    }

    public NFCsingleStatsView setTotalViews(Long totalViews) {
        this.totalViews = totalViews;
        return this;
    }

    public Double getPercentOfOpenedNFCVsQuantity() {
        return percentOfOpenedNFCVsQuantity;
    }

    public NFCsingleStatsView setPercentOfOpenedNFCVsQuantity(Double percentOfOpenedNFCVsQuantity) {
        this.percentOfOpenedNFCVsQuantity = percentOfOpenedNFCVsQuantity;
        return this;
    }
}
