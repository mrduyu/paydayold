package com.payday.incomeinformationservice.dal;

import com.payday.incomeinformationservice.model.IncomeInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeInformationRepository extends MongoRepository<IncomeInformation,String> {
}
