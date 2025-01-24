package com.pos.service;

import com.pos.dto.CostDTO;
import com.pos.model.Cost;
import com.pos.repository.CostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostService {

    @Autowired
    private final CostRepository repository;

    public CostService(CostRepository repository) {
        this.repository = repository;
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

}
