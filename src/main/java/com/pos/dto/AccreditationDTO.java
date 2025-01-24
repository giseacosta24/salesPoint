package com.pos.dto;


public class AccreditationDTO {
    private Double amount;

    private Long pointOfSaleId;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getPointOfSaleId() {
        return pointOfSaleId;
    }

    public void setPointOfSaleId(Long pointOfSaleId) {
        this.pointOfSaleId = pointOfSaleId;
    }
}
