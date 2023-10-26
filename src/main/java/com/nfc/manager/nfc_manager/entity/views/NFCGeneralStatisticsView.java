package com.nfc.manager.nfc_manager.entity.views;

public class NFCGeneralStatisticsView {
    private Integer totalNumberOfNFCs;

    private Integer numberOfCampaigns;

    private Long totalNumberOfViews;

    public Integer getTotalNumberOfNFCs() {
        return totalNumberOfNFCs;
    }

    public NFCGeneralStatisticsView setTotalNumberOfNFCs(Integer totalNumberOfNFCs) {
        this.totalNumberOfNFCs = totalNumberOfNFCs;
        return this;
    }

    public Integer getNumberOfCampaigns() {
        return numberOfCampaigns;
    }

    public NFCGeneralStatisticsView setNumberOfCampaigns(Integer numberOfCampaigns) {
        this.numberOfCampaigns = numberOfCampaigns;
        return this;
    }

    public Long getTotalNumberOfViews() {
        return totalNumberOfViews;
    }

    public NFCGeneralStatisticsView setTotalNumberOfViews(Long totalNumberOfViews) {
        this.totalNumberOfViews = totalNumberOfViews;
        return this;
    }
}
