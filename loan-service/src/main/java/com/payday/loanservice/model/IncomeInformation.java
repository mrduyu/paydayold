package com.payday.loanservice.model;

import java.util.List;

public class IncomeInformation {
    private String id;
    private String nationalId;
    private Double monthlyIncome;
    private List<Deduction> deductions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public List<Deduction> getDeductions() {
        return deductions;
    }
}
