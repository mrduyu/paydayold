package com.payday.loanservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class LoanRequest {

    @Id
    private String id;
    private String nationalId;
    private Boolean isGranted;
    private Double amount;
    private Integer periodInMonths;
    private Date requestedDate = new Date();
    private Date updatedDate = new Date();
    private String createdUser;
    private String updatedUser;

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

    public Boolean getGranted() {
        return isGranted;
    }

    public void setGranted(Boolean granted) {
        isGranted = granted;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public String getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    public Integer getPeriodInMonths() {
        return periodInMonths;
    }

    public void setPeriodInMonths(Integer periodInMonths) {
        this.periodInMonths = periodInMonths;
    }
}


