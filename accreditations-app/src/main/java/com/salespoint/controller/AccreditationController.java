package com.salespoint.controller;

import com.salespoint.dto.AccreditationDTO;
import com.salespoint.model.Accreditation;
import com.salespoint.service.AccreditationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/accreditations")
public class AccreditationController {

    private final AccreditationService accreditationService;

    public AccreditationController(AccreditationService accreditationService) {
        this.accreditationService = accreditationService;
    }

    @GetMapping
    public ResponseEntity<List<Accreditation>> getAllAccreditations() {
        List<Accreditation> accreditations = accreditationService.getAllAccreditations();
        return ResponseEntity.ok(accreditations);
    }

    @PostMapping
    public ResponseEntity<String> createAccreditation(@RequestBody AccreditationDTO accreditationDTO) {
        accreditationService.saveAccreditation(accreditationDTO);
        return ResponseEntity.ok("Accreditation added");    }
}