package com.gal.data;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.gal.dao.CabAssignmentRepository;
import com.gal.dao.CabRepository;
import com.gal.dao.EmployeeRepository;
import com.gal.model.Cab;
import com.gal.model.CabAssignment;
import com.gal.model.Employee;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepo;
    
    @Autowired
    private CabRepository cabRepo;
    
    @Autowired
    private CabAssignmentRepository assignmentRepo;

    @Override
    public void run(String... args) throws Exception {
        Employee emp = employeeRepo.findById(100).orElse(null);
        emp = null;
        
        Cab savedCab = cabRepo.findById(1).orElse(null);
        
        if (emp != null) {
            CabAssignment assignment = new CabAssignment();
            assignment.setEmployee(emp);
            assignment.setCab(savedCab);
            assignment.setAssignedDate(LocalDate.now().plusDays(5)); 
            
            assignmentRepo.save(assignment);
            System.out.println("Success: Cab assigned to " + emp.getFirstName());
        }
    }
}