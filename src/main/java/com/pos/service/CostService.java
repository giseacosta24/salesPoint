package com.pos.service;

import com.pos.dto.CostDTO;
import com.pos.model.Cost;
import com.pos.model.PointOfSale;
import com.pos.repository.CostRepository;
import com.pos.repository.PointOfSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CostService {

    @Autowired
    private final CostRepository repository;
    private final PointOfSaleRepository pointOfSaleRepository;

    public CostService(CostRepository repository, PointOfSaleRepository pointOfSaleRepository) {
        this.repository = repository;
        this.pointOfSaleRepository = pointOfSaleRepository;
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

    public Cost getMinimumCost(String pointOfSaleA, String pointOfSaleB) {
        PointOfSale pointOfSale1= new PointOfSale();
        PointOfSale pointOfSale2= new PointOfSale();
        pointOfSale1=pointOfSaleRepository.findByName(pointOfSaleA);
        pointOfSale2=pointOfSaleRepository.findByName(pointOfSaleB);

        List<Cost> costs = repository.findByPointAIdOrPointBId(pointOfSale1.getId(), pointOfSale2.getId());
        PointOfSale finalPointOfSale = pointOfSale1;
        PointOfSale finalPointOfSale1 = pointOfSale2;
        return costs.stream()
                .filter(cost ->
                        (cost.getPointAId().equals(finalPointOfSale.getId()) && cost.getPointBId().equals(finalPointOfSale1.getId())) ||
                                (cost.getPointAId().equals(finalPointOfSale1.getId()) && cost.getPointBId().equals(finalPointOfSale.getId()))
                )
                .min(Comparator.comparingDouble(Cost::getCost))
                .orElse(null);
    }

}
