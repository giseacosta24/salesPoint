package com.salespoint.model;

import jakarta.persistence.*;


@Entity
@Table(name = "costs")
public class Cost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "point_a_id", nullable = false)
    private Long pointAId;

    @Column(name = "point_b_id", nullable = false)
    private Long pointBId;

    @Column(nullable = false)
    private Double cost;

    public Cost() {
    }

    public Cost(Long pointAId, Long pointBId, Double cost) {
        this.pointAId = pointAId;
        this.pointBId = pointBId;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public Long getPointAId() {
        return pointAId;
    }

    public Long getPointBId() {
        return pointBId;
    }

    public Double getCost() {
        return cost;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPointAId(Long pointAId) {
        this.pointAId = pointAId;
    }

    public void setPointBId(Long pointBId) {
        this.pointBId = pointBId;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
