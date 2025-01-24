package com.pos.service;

import com.pos.dto.AccreditationDTO;
import com.pos.model.Accreditation;
import com.pos.model.PointOfSale;
import com.pos.repository.AccreditationRepository;
import com.pos.repository.PointOfSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccreditationService {

    @Autowired
    private final AccreditationRepository accreditationRepository;
    private final PointOfSaleRepository pointOfSaleRepository;

    public AccreditationService(AccreditationRepository accreditationRepository, PointOfSaleRepository pointOfSaleRepository) {
        this.accreditationRepository = accreditationRepository;
        this.pointOfSaleRepository = pointOfSaleRepository;
    }

    public List<Accreditation> getAllAccreditations() {
        return accreditationRepository.findAll();
    }

    public void saveAccreditation(AccreditationDTO accreditationDTO) {
        String pointOfSaleName = pointOfSaleRepository.findById(accreditationDTO.getPointOfSaleId())
                .map(PointOfSale::getName)
                .orElseThrow(() -> new IllegalArgumentException("Point of sale not found for ID: " + accreditationDTO.getPointOfSaleId()));

        Accreditation accreditation = new Accreditation();
        accreditation.setAmount(accreditationDTO.getAmount());
        accreditation.setPointOfSaleId(accreditationDTO.getPointOfSaleId());
        accreditation.setReceptionDate(LocalDate.from(LocalDateTime.now()));
        accreditation.setPointOfSaleName(pointOfSaleName);

        accreditationRepository.save(accreditation);


    }
}
