package com.pos.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "accreditations")
public class Accreditation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double amount;

    @Column(name = "point_of_sale_id", nullable = false)
    private Long pointOfSaleId;

    @Column(name = "reception_date", nullable = false)
    private LocalDate receptionDate;

    @Column(name = "point_of_sale_name", nullable = false, length = 100)
    private String pointOfSaleName;

    public Accreditation() {
    }

    public Accreditation(Double amount, Long pointOfSaleId, LocalDate receptionDate, String pointOfSaleName) {
        this.amount = amount;
        this.pointOfSaleId = pointOfSaleId;
        this.receptionDate = receptionDate;
        this.pointOfSaleName = pointOfSaleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDate getReceptionDate() {
        return receptionDate;
    }

    public void setReceptionDate(LocalDate receptionDate) {
        this.receptionDate = receptionDate;
    }

    public String getPointOfSaleName() {
        return pointOfSaleName;
    }

    public void setPointOfSaleName(String pointOfSaleName) {
        this.pointOfSaleName = pointOfSaleName;
    }
}