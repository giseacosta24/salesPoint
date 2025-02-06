package com.salespoint.service;

import com.salespoint.dto.PointOfSaleDTO;
import com.salespoint.model.PointOfSale;
import com.salespoint.repository.PointOfSaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class PointOfSaleService {


    private final PointOfSaleRepository posRepository;

    public PointOfSaleService(PointOfSaleRepository posRepository) {

        this.posRepository = posRepository;
    }

    public List<PointOfSaleDTO> getPOS() {
        return posRepository.findAll().stream()
                .map(this::convertToDTO).toList();
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

    public Optional<PointOfSale> getPOS(String posName) {
        return posRepository.findByName(posName) ;
    }
}
