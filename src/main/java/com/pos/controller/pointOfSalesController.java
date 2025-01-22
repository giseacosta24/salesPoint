package com.pos.controller;

import com.pos.model.pointOfSales;
import com.pos.service.pointOfSalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pos")
public class pointOfSalesController {
    @Autowired
    private pointOfSalesService service;

    @GetMapping
    public List<pointOfSales> getPOS() {
        return service.getPOS();
    }

    @PostMapping
    public void addPOS(@RequestBody pointOfSales pos) {
        service.addPOS(pos);
    }

/*    @PutMapping
    public void updatePOS(@RequestBody pointOfSales pos) {
        return posRepository.save(pos);
    }

    @DeleteMapping
    public pointOfSales agregarPOS(@RequestBody pointOfSales pos) {
        return posRepository.delete(pos);
    }*/
}
