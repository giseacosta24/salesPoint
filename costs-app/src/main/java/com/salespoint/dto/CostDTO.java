package com.salespoint.dto;


import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class CostDTO {

    private Long pointAId;

    private Long pointBId;

    private Double cost;

    public CostDTO(Long pointAId, Long pointBId, Double cost) {
        this.pointAId = pointAId;
        this.pointBId = pointBId;
        this.cost = cost;
    }

}
