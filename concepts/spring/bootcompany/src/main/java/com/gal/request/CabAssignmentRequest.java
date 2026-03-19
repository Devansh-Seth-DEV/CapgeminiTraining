package com.gal.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CabAssignmentRequest {
    private int empId;
    private int cabId;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    // Standard Getters and Setters are REQUIRED for Jackson to work
    public int getEmpId() { return empId; }
    public void setEmpId(int empId) { this.empId = empId; }

    public int getCabId() { return cabId; }
    public void setCabId(int cabId) { this.cabId = cabId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}