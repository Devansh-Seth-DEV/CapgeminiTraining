package com.gal.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gal.dao.CabAssignmentRepository;
import com.gal.model.CabAssignment;
import com.gal.model.EmployeeTransportDTO;

@Service
public class CabService {

    @Autowired
    private CabAssignmentRepository assignmentRepo;

    public List<EmployeeTransportDTO> getTodayPassengersByCab(int cabId) {
        LocalDate today = LocalDate.now();
        
        List<CabAssignment> assignments = assignmentRepo
        		.findByCab_CabIdAndAssignedDate(cabId, today);
        
        return assignments.stream()
                .map(asign -> new EmployeeTransportDTO(asign.getEmployee(), asign))
                .collect(Collectors.toList());
    }
}