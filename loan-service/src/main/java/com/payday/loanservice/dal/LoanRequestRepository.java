package com.payday.loanservice.dal;

import com.payday.loanservice.model.LoanRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRequestRepository extends  MongoRepository<LoanRequest,String>{
}
