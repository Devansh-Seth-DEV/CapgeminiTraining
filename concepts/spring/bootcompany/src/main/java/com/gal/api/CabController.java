package com.gal.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gal.model.EmployeeTransportDTO;
import com.gal.service.CabService;

@RestController
@RequestMapping("/cab")
public class CabController {

    @Autowired
    private CabService cabService;

    @GetMapping("/passengers")
    public ResponseEntity<List<EmployeeTransportDTO>> getCabPassengers(
    		@RequestParam("cabId") int cabId
    ) {
        List<EmployeeTransportDTO> passengers = cabService.getTodayPassengersByCab(cabId);
        
        if (passengers.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        
        return ResponseEntity.ok(passengers);
    }
}