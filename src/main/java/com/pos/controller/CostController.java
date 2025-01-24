package com.pos.controller;

import com.pos.dto.CostDTO;
import com.pos.model.Cost;
import com.pos.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/costs")
public class CostController {
    @Autowired
    private final CostService service;

    public CostController(CostService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<String> addCost(@RequestBody CostDTO costDTO) {
        if (costDTO.getCost() == null || costDTO.getCost() < 0) {
            return ResponseEntity.badRequest().body(null);
        }
        service.addCost(costDTO);
        return ResponseEntity.ok("Cost added");

    }

    @DeleteMapping
    public ResponseEntity<Void> removeCost(@RequestParam Long pointAId, @RequestParam Long pointBId) {
        service.removeCost(pointAId, pointBId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{pointAId}")
    public ResponseEntity<List<Cost>> getPointsA(@PathVariable Long pointAId) {
        return ResponseEntity.ok(service.getPointsA(pointAId));
    }

    @GetMapping("/minimumCost")
    public ResponseEntity<?> getMinimumCost(@RequestParam String pointOfSaleA, @RequestParam String pointOfSaleB) {
        return ResponseEntity.ok(service.getMinimumCost(pointOfSaleA,pointOfSaleB));
    }
}
