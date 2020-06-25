package com.payday.loanservice.dal;

import com.payday.loanservice.model.IncomeInformation;
import com.payday.loanservice.model.KycInformation;
import com.payday.loanservice.model.LoanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public class LoanRequestDALImpl implements LoanRequestDAL{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<LoanRequest> getAllLoanRequests() {
        return mongoTemplate.findAll(LoanRequest.class);
    }

    @Override
    public LoanRequest createLoanRequest(LoanRequest loanRequest) {
        return mongoTemplate.save(loanRequest);
    }

    @Override
    public List<LoanRequest> getLoanRequestsByNationalId(String nationalId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("nationalId").is(nationalId));
        List<LoanRequest> loanRequestList = mongoTemplate.find(query, LoanRequest.class);
        return loanRequestList;
    }

    @Override
    public LoanRequest getLoanRequestByLoanId(String loanId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(loanId));
        return mongoTemplate.findOne(query, LoanRequest.class);
    }

    @Override
    public LoanRequest updateLoanRequestByLoanId(String loanId, Boolean isGranted, String updatedUser) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is("loanId"));

        LoanRequest loanRequest = mongoTemplate.findOne(query, LoanRequest.class);

        loanRequest.setGranted(isGranted);
        loanRequest.setUpdatedDate(new Date());
        loanRequest.setUpdatedUser(updatedUser);
        mongoTemplate.save(loanRequest);

        LoanRequest updatedLoanRequest = mongoTemplate.findOne(query, LoanRequest.class);

        return updatedLoanRequest;
    }

    @Override
    public Double getIncome(String nationalId) {
        final String uri = "http://incomeservice:8104/incomeinformation/nationalId/"+nationalId;
        RestTemplate restTemplate = new RestTemplate();
        IncomeInformation result = restTemplate.getForObject(uri, IncomeInformation.class);
        return result.getMonthlyIncome();
    }

    @Override
    public Date getLastEmployerStartDate(String nationalId) {
        final String uri = "http://kycservice:8103/kycinformation/"+nationalId;
        RestTemplate restTemplate = new RestTemplate();
        KycInformation result = restTemplate.getForObject(uri, KycInformation.class);
        return result.getStartDate();
    }

    @Override
    public String sendLoanRequestResultEmail(String message) {
        final String uri = "http://localhost:9000/kafka/sendmail/"+message;
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }


}
