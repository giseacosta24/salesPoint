package com.salespoint.service;

import com.salespoint.dto.CostDTO;
import com.salespoint.dto.MinimunRouteDTO;
import com.salespoint.model.Cost;
import com.salespoint.repository.CostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class CostService {


    private final CostRepository repository;

    private final GraphService graphService;


    public CostService(CostRepository repository, GraphService graphService) {
        this.repository = repository;
        this.graphService = graphService;
    }

    public void addCost(CostDTO costdto) {
            if (costdto.getCost() == null || costdto.getCost() < 0) {
                throw new IllegalArgumentException("the cost must be greater than 0.");
        }
        Cost costEntry = new Cost();
        costEntry.setPointAId(costdto.getPointAId());
        costEntry.setPointBId(costdto.getPointBId());
        costEntry.setCost(costdto.getCost());
        repository.save(costEntry);
    }

    public void removeCost(Long pointAId, Long pointBId) {
        List<Cost> costs = repository.findByPointAIdOrPointBId(pointAId, pointBId);
        costs.stream()
                .filter(c -> (c.getPointAId().equals(pointAId) && c.getPointBId().equals(pointBId)) ||
                        (c.getPointAId().equals(pointBId) && c.getPointBId().equals(pointAId)))
                .findFirst()
                .ifPresent(repository::delete);
    }

    public List<Cost> getPointsA(Long pointAId) {
        return repository.findByPointAId(pointAId);
    }

    public ResponseEntity<MinimunRouteDTO> getMinimumCost(
            @RequestParam String origen,
            @RequestParam String destino) {
        MinimunRouteDTO rutaMinima = graphService.calcularRutaMinima(origen, destino);
        if (rutaMinima == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(rutaMinima);
    }

}
