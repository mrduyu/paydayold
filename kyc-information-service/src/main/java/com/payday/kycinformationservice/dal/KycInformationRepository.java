package com.payday.kycinformationservice.dal;


import com.payday.kycinformationservice.model.KycInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KycInformationRepository extends MongoRepository<KycInformation,String> {
}
