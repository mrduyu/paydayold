package com.payday.loanservice.model;

import java.util.Date;

public class KycInformation {
    private String id;
    private String nationalId;
    private String address;
    private String lastEmployerName;
    private Date startDate;
    private String lastEmployer;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastEmployerName() {
        return lastEmployerName;
    }

    public void setLastEmployerName(String lastEmployerName) {
        this.lastEmployerName = lastEmployerName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getLastEmployer() {
        return lastEmployer;
    }

    public void setLastEmployer(String lastEmployer) {
        this.lastEmployer = lastEmployer;
    }
}
