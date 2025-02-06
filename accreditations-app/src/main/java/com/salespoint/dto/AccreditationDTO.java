package com.salespoint.dto;


public class AccreditationDTO {
    private Double amount;

    private Long pointOfSaleId;

    public AccreditationDTO(Double amount, Long pointOfSaleId) {
        this.amount = amount;
        this.pointOfSaleId = pointOfSaleId;
    }

    public Double getAmount() {
        return amount;
    }

    public Long getPointOfSaleId() {
        return pointOfSaleId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setPointOfSaleId(Long pointOfSaleId) {
        this.pointOfSaleId = pointOfSaleId;
    }
}
