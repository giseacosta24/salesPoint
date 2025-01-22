package com.pos.service;

import com.pos.model.pointOfSales;
import com.pos.repository.pointOfSalesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class pointOfSalesService {


    private pointOfSalesRepository posRepository;

    public pointOfSalesService(pointOfSalesRepository posRepository) {

        this.posRepository = posRepository;
    }

    public List<pointOfSales> getPOS() {

        return posRepository.findAll();
    }

    public void addPOS(pointOfSales pos) {
        posRepository.save(pos);

    }
}
