package com.pos.controller;

import com.pos.dto.PointOfSaleDTO;
import com.pos.service.PointOfSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pos")
public class PointOfSaleController {
    @Autowired
    private PointOfSaleService service;

    @GetMapping
    public List<PointOfSaleDTO> getPOS() {
        return service.getPOS();
    }

    @PostMapping
    public ResponseEntity<String> addPOS(@RequestBody PointOfSaleDTO dto) {
        service.addPOS(dto);
        return ResponseEntity.ok("Point of Sales added");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String>  updatePOS(@PathVariable Long id, @RequestBody PointOfSaleDTO posDTO) {
        service.updatePOS(id, posDTO.getName());
        return ResponseEntity.ok("Point of Sales updated.");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePOS(@PathVariable Long id) {
        service.deletePOS(id);
        return ResponseEntity.ok("Deleted Point of Sales successfully.");    }
}
