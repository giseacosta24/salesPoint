package com.salespoint.repository;

import com.salespoint.model.Cost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CostRepository extends JpaRepository<Cost, Long> {
    List<Cost> findByPointAId(Long pointAId);
    List<Cost> findByPointAIdOrPointBId(Long pointAId, Long pointBId);

}