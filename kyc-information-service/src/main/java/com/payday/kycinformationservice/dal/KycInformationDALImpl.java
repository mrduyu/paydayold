package com.payday.kycinformationservice.dal;

import com.payday.kycinformationservice.model.KycInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class KycInformationDALImpl implements KycInformationDAL{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public KycInformation getKycInformationByNationalId(String nationalId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("nationalId").is(nationalId));
        KycInformation kycInformation = mongoTemplate.findOne(query, KycInformation.class);
        return kycInformation;
    }

    @Override
    public KycInformation createKycInformation(KycInformation kycInformation) {
        return mongoTemplate.save(kycInformation);
    }

    @Override
    public KycInformation updateLoanRequestByLoanId(KycInformation updatedKycInformation) {
        Query query = new Query();
        query.addCriteria(Criteria.where("nationalId").is(updatedKycInformation.getNationalId()));

        KycInformation kycInformation = mongoTemplate.findOne(query, KycInformation.class);

        kycInformation.setAddress(updatedKycInformation.getAddress());
        kycInformation.setLastEmployer(updatedKycInformation.getLastEmployer());
        kycInformation.setStartDate(updatedKycInformation.getStartDate());
        kycInformation.setEmailAddress(updatedKycInformation.getEmailAddress());
        kycInformation.setLastEmployerName(updatedKycInformation.getLastEmployerName());
        mongoTemplate.save(kycInformation);

        KycInformation updatedData = mongoTemplate.findOne(query, KycInformation.class);

        return updatedData;
    }

}
