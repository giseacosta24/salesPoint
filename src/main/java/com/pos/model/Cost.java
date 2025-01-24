package com.pos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "costs")
public class Cost { // Clase renombrada a singular y con mayúscula inicial
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "point_a_id", nullable = false)
    private Long pointAId;

    @Column(name = "point_b_id", nullable = false)
    private Long pointBId;

    @Column(nullable = false)
    private Double cost;

    // Constructor vacío requerido por JPA
    public Cost() {
    }

    // Constructor opcional con todos los campos
    public Cost(Long pointAId, Long pointBId, Double cost) {
        this.pointAId = pointAId;
        this.pointBId = pointBId;
        this.cost = cost;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPointAId() {
        return pointAId;
    }

    public void setPointAId(Long pointAId) {
        this.pointAId = pointAId;
    }

    public Long getPointBId() {
        return pointBId;
    }

    public void setPointBId(Long pointBId) {
        this.pointBId = pointBId;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
