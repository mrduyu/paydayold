package com.payday.incomeinformationservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class CreditComplex {

    @Id
    private String id;
    private String bcnr;
    public String getBcnr() { return bcnr; }
    public void setBcnr(String bcnr) { this.bcnr = bcnr; }

    private String relationshipName;
    public String getRelationshipName() { return relationshipName; }
    public void setRelationshipName(String relationshipName) { this.relationshipName = relationshipName; }

    private List<Deduction> borrowers;
    public List<Deduction> getBorrowers() { return borrowers; }
    public void setBorrowers(List<Deduction> borrowers) { this.borrowers = borrowers; }

}
