package com.salespoint.repository;

import com.salespoint.model.PointOfSale;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PointOfSaleRepository extends JpaRepository<PointOfSale, Long> {
    Optional <PointOfSale>  findByName(String posName);

    @Query("SELECT p.name FROM PointOfSale p WHERE p.id = :id")
    String findNameById(@Param("id") Long id);
}


