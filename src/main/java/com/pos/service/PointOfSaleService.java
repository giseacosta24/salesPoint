package com.pos.service;

import com.pos.dto.PointOfSaleDTO;
import com.pos.model.PointOfSale;
import com.pos.repository.PointOfSaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PointOfSaleService {


    @Autowired
    private PointOfSaleRepository posRepository;

    public PointOfSaleService(PointOfSaleRepository posRepository) {

        this.posRepository = posRepository;
    }

    public List<PointOfSaleDTO> getPOS() {
        return posRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private PointOfSaleDTO convertToDTO(PointOfSale pos) {
        PointOfSaleDTO dto = new PointOfSaleDTO();
        dto.setId(pos.getId());
        dto.setName(pos.getName());
        return dto;
    }

    public void addPOS(PointOfSaleDTO posDTO) {
        PointOfSale posModel = new PointOfSale();
        posModel.setName(posDTO.getName());
        posRepository.save(posModel);
    }

    @Transactional
    public void updatePOS(Long id, String posDTOName) {
        PointOfSale posModel = posRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Punto de venta no encontrado con ID: " + id));

        posModel.setName(posDTOName);

        posRepository.save(posModel);
    }
  
    public void deletePOS(Long id) {
        PointOfSale posModel = posRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Punto de venta no encontrado con ID: " + id));
        posRepository.delete(posModel);
    }

    public PointOfSale getPOS(String posName) {
        return posRepository.findByName(posName) ;
    }
}
